import abc
from collections import Counter
from collections import UserDict as DictClass
from collections import defaultdict
import math
from typing import Dict, List

CollectionType = Dict[str, Dict[str, List[str]]]


class DocumentCollection(DictClass):
    """Document dictionary class with helper functions."""

    def total_field_length(self, field: str) -> int:
        """Total number of terms in a field for all documents."""
        return sum(len(fields[field]) for fields in self.values())

    def avg_field_length(self, field: str) -> float:
        """Average number of terms in a field across all documents."""
        return self.total_field_length(field) / len(self)

    def get_field_documents(self, field: str) -> Dict[str, List[str]]:
        """Dictionary of documents for a single field."""
        return {
            doc_id: doc[field] for (doc_id, doc) in self.items() if field in doc
        }


class Scorer(abc.ABC):
    def __init__(
        self,
        collection: DocumentCollection,
        index: CollectionType,
        field: str = None,
        fields: List[str] = None,
    ):
        """Interface for the scorer class.

        Args:
            collection: Collection of documents. Needed to calculate document
                statistical information.
            index: Index to use for calculating scores.
            field (optional): Single field to use in scoring.. Defaults to None.
            fields (optional): List of fields to use in scoring. Defaults to
                None.

        Raises:
            ValueError: Either field or fields need to be specified.
        """
        self.collection = collection
        self.index = index

        if not (field or fields):
            raise ValueError("Either field or fields have to be defined.")

        self.field = field
        self.fields = fields

        # Score accumulator for the query that is currently being scored.
        self.scores = None

    def score_collection(self, query_terms: List[str]):
        """Scores all documents in the collection using term-at-a-time query
        processing.

        Params:
            query_term: Sequence (list) of query terms.

        Returns:
            Dict with doc_ids as keys and retrieval scores as values.
            (It may be assumed that documents that are not present in this dict
            have a retrival score of 0.)
        """
        self.scores = defaultdict(float)  # Reset scores.
        query_term_freqs = Counter(query_terms)

        for term, query_freq in query_term_freqs.items():
            self.score_term(term, query_freq)

        return self.scores

    @abc.abstractmethod
    def score_term(self, term: str, query_freq: int):
        """Scores one query term and updates the accumulated document retrieval
        scores (`self.scores`).

        Params:
            term: Query term
            query_freq: Frequency (count) of the term in the query.
        """
        raise NotImplementedError


class SimpleScorer(Scorer):
    def score_term(self, term: str, query_freq: int) -> None:
        # TODO

        # Get the documents that contain the specified term in the specified field
        field_documents = self.collection.get_field_documents(self.field)

        for doc_id, term_list in field_documents.items():
            # Count the frequency of the term in the document
            doc_freq = term_list.count(term)

            # Calculate the score
            score_increment = doc_freq * query_freq

            self.scores[doc_id] += score_increment

        return

class ScorerBM25(Scorer):
    def __init__(
        self,
        collection: DocumentCollection,
        index: CollectionType,
        field: str = "body",
        b: float = 0.75,
        k1: float = 1.2,
    ) -> None:
        super(ScorerBM25, self).__init__(collection, index, field)
        self.b = b
        self.k1 = k1

    def score_term(self, term: str, query_freq: int) -> None:
        # TODO
        # Total number of documents in the collection
        num_docs = len(self.collection.items())

        # Number of times the given term appears in the collection
        num_term = len(self.index.get(self.field).get(term))

        # IDF = log(num_docs/num_term)
        idf_t = math.log(num_docs/num_term)

        # Average document length
        avdl = self.collection.avg_field_length(self.field)

        for (doc_id, doc) in self.collection.items():
            
            # $$score(d,q) = \sum_{t \in q} \frac{c_{t,d}\times (1+k_1)}{c_{t,d} + k_1(1-b+b\frac{|d|}{avgdl})} \times idf_t$$
            c_td = doc.get(self.field).count(term)
            denominator = 1 - self.b + self.b*(len(doc.get(self.field))/avdl)
            
            self.scores[doc_id] += (c_td * (1 + self.k1)) / (c_td + self.k1*denominator) * idf_t 

        return


class ScorerLM(Scorer):
    def __init__(
        self,
        collection: DocumentCollection,
        index: CollectionType,
        field: str = "body",
        smoothing_param: float = 0.1,
    ):
        super(ScorerLM, self).__init__(collection, index, field)
        self.smoothing_param = smoothing_param

    def score_term(self, term: str, query_freq: int) -> None:
        # TODO

        term_collection_freq = sum(doc.get(self.field).count(term) for doc in self.collection.values())
        c_tp = term_collection_freq / self.collection.total_field_length(self.field)

       
        for doc_id, doc in self.collection.items():
            # Parameters  
            c_td = doc.get(self.field).count(term)
            doc_length = len(doc.get(self.field))
            
            # Calculate query likelihood probability
            smoothed_prob = ((1 - self.smoothing_param) * (c_td / doc_length)) + (self.smoothing_param * c_tp)
            
            if smoothed_prob > 0:
                self.scores[doc_id] += query_freq * math.log(smoothed_prob)


class ScorerBM25F(Scorer):
    def __init__(
        self,
        collection: DocumentCollection,
        index: CollectionType,
        fields: List[str] = ["title", "body"],
        field_weights: List[float] = [0.2, 0.8],
        bi: List[float] = [0.75, 0.75],
        k1: float = 1.2,
    ) -> None:
        super(ScorerBM25F, self).__init__(collection, index, fields=fields)
        self.field_weights = field_weights
        self.bi = bi
        self.k1 = k1

    def score_term(self, term: str, query_freq: int) -> None:
        # TODO
        # Total number of documents in the collection
        num_docs = len(self.collection.items())

        # Number of times the given term appears in the body field
        num_term = len(self.index.get("body").get(term))

        # IDF = log(num_docs/num_term)
        idf_t = math.log(num_docs/num_term)
        # Average document length / field
        avdl = [self.collection.avg_field_length(field) for field in self.fields]

        for (doc_id, doc) in self.collection.items():
            pseudo_c_td = 0
            for i in range(len(self.fields)):
                # Parameters  
                doc_length = len(doc.get(self.fields[i]))
                B = 1 - self.bi[i] + self.bi[i]*(doc_length/avdl[i])
                c_td = doc.get(self.fields[i]).count(term)
                pseudo_c_td += (self.field_weights[i] * c_td) / B

            self.scores[doc_id] += (pseudo_c_td / (pseudo_c_td + self.k1)) * idf_t


class ScorerMLM(Scorer):
    def __init__(
        self,
        collection: DocumentCollection,
        index: CollectionType,
        fields: List[str] = ["title", "body"],
        field_weights: List[float] = [0.2, 0.8],
        smoothing_param: float = 0.1,
    ):
        super(ScorerMLM, self).__init__(collection, index, fields=fields)
        self.field_weights = field_weights
        self.smoothing_param = smoothing_param

    def score_term(self, term: str, query_freq: float) -> None:
        # TODO
        for (doc_id, doc) in self.collection.items():
            smoothed_prob = 0
            for i in range(len(self.fields)):
                # Parameters  
                term_collection_freq = sum(document.get(self.fields[i]).count(term) for document in self.collection.values())
                c_tp = term_collection_freq / self.collection.total_field_length(self.fields[i])
                c_td = doc.get(self.fields[i]).count(term)
                doc_length = len(doc.get(self.fields[i]))

                # Compute query likelihood probability
                smoothed_prob_i = (1-self.smoothing_param)*c_td/doc_length + self.smoothing_param*c_tp
                smoothed_prob += self.field_weights[i]*smoothed_prob_i

            if smoothed_prob > 0:
                self.scores[doc_id] += query_freq * math.log(smoothed_prob)