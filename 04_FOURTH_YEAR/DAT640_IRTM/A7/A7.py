import json
from collections import defaultdict
import math
from typing import Callable, Dict, List, Set, Tuple

import numpy as np
from elasticsearch import Elasticsearch

FIELDS = ["title", "body"]

INDEX_SETTINGS = {
    "mappings": {
        "properties": {
            "title": {
                "type": "text",
                "term_vector": "yes",
                "analyzer": "english",
            },
            "body": {
                "type": "text",
                "term_vector": "yes",
                "analyzer": "english",
            },
        }
    }
}

FEATURES_QUERY = [
    "query_length",
    "query_sum_idf",
    "query_max_idf",
    "query_avg_idf",
]
FEATURES_DOC = ["doc_length_title", "doc_length_body"]
FEATURES_QUERY_DOC = [
    "unique_query_terms_in_title",
    "sum_TF_title",
    "max_TF_title",
    "avg_TF_title",
    "unique_query_terms_in_body",
    "sum_TF_body",
    "max_TF_body",
    "avg_TF_body",
]


def analyze_query(
    es: Elasticsearch, query: str, field: str, index: str = "toy_index"
) -> List[str]:
    """Analyzes a query with respect to the relevant index.

    Args:
        es: Elasticsearch object instance.
        query: String of query terms.
        field: The field with respect to which the query is analyzed.
        index: Name of the index with respect to which the query is analyzed.

    Returns:
        A list of query terms that exist in the specified field among the
        documents in the index.
    """
    tokens = es.indices.analyze(index=index, body={"text": query})["tokens"]
    query_terms = []
    for t in sorted(tokens, key=lambda x: x["position"]):
        # Use a boolean query to find at least one document that contains the
        # term.
        hits = (
            es.search(
                index=index,
                query={"match": {field: t["token"]}},
                _source=False,
                size=1,
            )
            .get("hits", {})
            .get("hits", {})
        )
        doc_id = hits[0]["_id"] if len(hits) > 0 else None
        if doc_id is None:
            continue
        query_terms.append(t["token"])
    return query_terms


def get_doc_term_freqs(
    es: Elasticsearch, doc_id: str, field: str, index: str = "toy_index"
) -> Dict[str, int]:
    """Gets the term frequencies of a field of an indexed document.

    Args:
        es: Elasticsearch object instance.
        doc_id: Document identifier with which the document is indexed.
        field: Field of document to consider for term frequencies.
        index: Name of the index where document is indexed.

    Returns:
        Dictionary of terms and their respective term frequencies in the field
        and document.
    """
    tv = es.termvectors(
        index=index, id=doc_id, fields=field, term_statistics=True
    )
    if tv["_id"] != doc_id:
        return None
    if field not in tv["term_vectors"]:
        return None
    term_freqs = {}
    for term, term_stat in tv["term_vectors"][field]["terms"].items():
        term_freqs[term] = term_stat["term_freq"]
    return term_freqs


def extract_query_features(
    query_terms: List[str], es: Elasticsearch, index: str = "toy_index"
) -> Dict[str, float]:
    """Extracts features of a query.

    Args:
        query_terms: List of analyzed query terms.
        es: Elasticsearch object instance.
        index: Name of relevant index on the running Elasticsearch service.
    Returns:
        Dictionary with keys 'query_length', 'query_sum_idf',
            'query_max_idf', and 'query_avg_idf'.
    """
    # TODO

    num_docs = es.count(index=index).get('count')
    query_idfs = []

    for term in query_terms:
        # Count the documents that contains the term
        es_query = {"match": {"body": term}}
        num_terms = es.count(index=index, body={'query': es_query}).get('count')

        # Append the idf to the list
        if num_terms != 0:
            query_idfs.append(math.log(num_docs/num_terms))
        else:
            query_idfs.append(0)

    features_dic = {
        'query_length': len(query_terms),
        'query_sum_idf': sum(query_idfs),
        'query_max_idf': max(query_idfs),
        'query_avg_idf': sum(query_idfs)/len(query_idfs)
    }

    return features_dic


def extract_doc_features(
    doc_id: str, es: Elasticsearch, index: str = "toy_index"
) -> Dict[str, float]:
    """Extracts features of a document.

    Args:
        doc_id: Document identifier of indexed document.
        es: Elasticsearch object instance.
        index: Name of relevant index on the running Elasticsearch service.

    Returns:
        Dictionary with keys 'doc_length_title', 'doc_length_body'.
    """
    # TODO

    features_dic = {}

    # Get the document from the index
    doc = es.get(index=index, id=doc_id)

    # Check if the field is in the document and calculate the length of the terms:
    for field, feature in zip(FIELDS, FEATURES_DOC):
        if field in doc['_source']:
            terms = doc['_source'][field].split(" ")
            features_dic[feature] = len(terms)
        else:
            features_dic[feature] = 0

    return features_dic

    

def extract_query_doc_features(
    query_terms: List[str],
    doc_id: str,
    es: Elasticsearch,
    index: str = "toy_index",
) -> Dict[str, float]:
    """Extracts features of a query and document pair.

    Args:
        query_terms: List of analyzed query terms.
        doc_id: Document identifier of indexed document.
        es: Elasticsearch object instance.
        index: Name of relevant index on the running Elasticsearch service.

    Returns:
        Dictionary with keys 'unique_query_terms_in_title',
            'unique_query_terms_in_body', 'sum_TF_title', 'sum_TF_body',
            'max_TF_title', 'max_TF_body', 'avg_TF_title', 'avg_TF_body'.
    """
    # TODO
    
    features_dic = {}

    # Get the document from the index
    doc = es.get(index=index, id=doc_id)
    doc_source = doc['_source']

    # Iterate over fields and their corresponding features
    for field, feature_names in zip(FIELDS, zip(*[iter(FEATURES_QUERY_DOC)]*4)):
        if field in doc_source:
            # Get the term vectors for each field of the document
            term_vector = doc_source[field].split(" ")

            # Unique terms features
            unique_terms_count = sum(1 for term in query_terms if term in term_vector)
            features_dic[feature_names[0]] = unique_terms_count

            # Aggregation functions features
            # Pre-compute tf vectors for more efficiency
            tf_vec = [term_vector.count(term) for term in query_terms]

            features_dic[feature_names[1]] = sum(tf_vec)  # sum_TF
            features_dic[feature_names[2]] = max(tf_vec) if tf_vec else 0  # max_TF
            features_dic[feature_names[3]] = sum(tf_vec) / len(tf_vec) if tf_vec else 0  # avg_TF
        else:
            # If the field doesn't exist in the document, set all features to 0
            for feature in feature_names:
                features_dic[feature] = 0

    return features_dic


def extract_features(
    query_terms: List[str],
    doc_id: str,
    es: Elasticsearch,
    index: str = "toy_index",
) -> List[float]:
    """Extracts query features, document features and query-document features
    of a query and document pair.

    Args:
        query_terms: List of analyzed query terms.
        doc_id: Document identifier of indexed document.
        es: Elasticsearch object instance.
        index: Name of relevant index on the running Elasticsearch service.

    Returns:
        List of extracted feature values in a fixed order.
    """
    query_features = extract_query_features(query_terms, es, index=index)
    feature_vect = [query_features[f] for f in FEATURES_QUERY]

    doc_features = extract_doc_features(doc_id, es, index=index)
    feature_vect.extend([doc_features[f] for f in FEATURES_DOC])

    query_doc_features = extract_query_doc_features(
        query_terms, doc_id, es, index=index
    )
    feature_vect.extend([query_doc_features[f] for f in FEATURES_QUERY_DOC])

    return feature_vect


def index_documents(filepath: str, es: Elasticsearch, index: str) -> None:
    """Indexes documents from JSONL file."""
    bulk_data = []
    with open(filepath, "r") as docs:
        for doc in docs:
            doc = json.loads(doc)
            bulk_data.append(
                {"index": {"_index": index, "_id": doc.pop("doc_id")}}
            )
            bulk_data.append(doc)
    es.bulk(index=index, body=bulk_data, refresh=True)


def reset_index(es: Elasticsearch, index: str) -> None:
    """Reset Index"""
    if es.indices.exists(index):
        es.indices.delete(index=index)

    es.indices.create(index=index, body=INDEX_SETTINGS)


def load_queries(filepath: str) -> Dict[str, str]:
    """Given a filepath, returns a dictionary with query IDs and corresponding
    query strings.

    This is an example query:

    ```
    <top>
    <num> Number: OHSU1
    <title> 60 year old menopausal woman without hormone replacement therapy
    <desc> Description:
    Are there adverse effects on lipids when progesterone is given with estrogen replacement therapy
    </top>

    ```

    Take as query ID the value (on the same line) after `<num> Number: `,
    and take as the query string the rest of the line after `<title> `. Omit
    newline characters.

    Args:
        filepath: String (constructed using os.path) of the filepath to a
        file with queries.

    Returns:
        A dictionary with query IDs and corresponding query strings.
    """
    queries = {}

    with open(filepath, "r") as f:
        for line in f:
            if line.startswith("<num> Number:"):
                splitline = line.split(" ", 2)
                query_id = splitline[-1].rstrip()
            if line.startswith("<title>"):
                splitline = line.split(" ", 1)
                queries[query_id] = splitline[-1].rstrip()
    return queries


def load_qrels(filepath: str) -> Dict[str, List[str]]:
    """Loads query relevance judgments from a file.
    The qrels file has content with tab-separated values such as the following:

    ```
    MSH1	87056458
    MSH1	87056800
    MSH1	87058606
    MSH2	87049102
    MSH2	87056792
    ```

    Args:
        filepath: String (constructed using os.path) of the filepath to a
            file with queries.

    Returns:
        A dictionary with query IDs and a corresponding list of document IDs
            for documents judged relevant to the query.
    """
    qrels = defaultdict(list)

    with open(filepath, "r") as f:
        for line in f:
            splitline = line.split("\t")
            query_id = splitline[0]
            doc_id = splitline[1].rstrip()
            qrels[query_id].append(doc_id)
    return qrels


def prepare_ltr_training_data(
    query_ids: List[str],
    all_queries: Dict[str, str],
    all_qrels: Dict[str, List[str]],
    es: Elasticsearch,
    index: str,
) -> Tuple[List[List[float]], List[int]]:
    """Prepares feature vectors and labels for query and document pairs found
    in the training data.

        Args:
            query_ids: List of query IDs.
            all_queries: Dictionary containing all queries.
            all_qrels: Dictionary with keys as query ID and values as list of
                relevant documents.
            es: Elasticsearch object instance.
            index: Name of relevant index on the running Elasticsearch service.

        Returns:
            X: List of feature vectors extracted for each pair of query and
                retrieved or relevant document.
            y: List of corresponding labels.
    """
    X = []
    y = []

    for i, query_id in enumerate(query_ids):
        print(
            "Processing query {}/{} ID {}".format(
                i + 1, len(query_ids), query_id
            )
        )
        query = all_queries[query_id]
        query_terms = analyze_query(es, query, "body", index=index)
        if len(query_terms) == 0:
            continue

        # Add documents and relevance labels from ground truth.
        qrels = set(all_qrels[query_id])

        # Generate features for documents in first-pass retrieval.
        hits = es.search(
            index=index, q=" ".join(query_terms), size=100, _source=False
        )["hits"]["hits"]
        all_docs = qrels.union(hit["_id"] for hit in hits)
        for doc_id in all_docs:
            feature_vector = extract_features(
                query_terms, doc_id, es, index=index
            )
            X.append(feature_vector)
            y.append(1 if doc_id in qrels else 0)

    return X, y

if __name__ == "__main__":
    index_name = "trec9_index"
    es = Elasticsearch(timeout=120)

    reset_index(es, index_name)
    index_documents("data/documents.jsonl", es, index_name)
