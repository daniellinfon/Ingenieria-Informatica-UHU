from typing import Callable, Dict, List, Set

import ir_datasets


def load_rankings(
    filename: str = "data/system_rankings.tsv",
) -> Dict[str, List[str]]:
    """Load rankings from file. Every row in the file contains query ID and
    document ID separated by a tab ("\t").

        query_id    doc_id
        646	        4496d63c-8cf5-11e3-833c-33098f9e5267
        646	        ee82230c-f130-11e1-adc6-87dfa8eff430
        646	        ac6f0e3c-1e3c-11e3-94a2-6c66b668ea55

    Example return structure:

    {
        query_id_1: [doc_id_1, doc_id_2, ...],
        query_id_2: [doc_id_1, doc_id_2, ...]
    }

    Args:
        filename (optional): Path to file with rankings. Defaults to
            "system_rankings.tsv".

    Returns:
        Dictionary with query IDs as keys and list of documents as values.
    """
    # TODO
    
    ranking = {}

    # Open file and read each line updating the dictionary
    with open(filename, 'r') as f:
        lines = f.readlines()
        for line in lines[1:]:
            query_id, doc_id = line.split("\t") # Split line by tabs

            if query_id not in ranking:
                ranking[query_id] = []

            ranking[query_id].append(doc_id[:-1]) # [:-1] to remove /n 

    return ranking


def load_ground_truth(
    collection: str = "wapo/v2/trec-core-2018",
) -> Dict[str, Set[str]]:
    """Load ground truth from ir_datasets. Qrel is a namedtuple class with
    following properties:

        query_id: str
        doc_id: str
        relevance: int
        iteration: str

    relevance is split into levels with values:

        0	not relevant
        1	relevant
        2	highly relevant

    This function considers documents to be relevant for relevance values
        1 and 2.

    Generic structure of returned dictionary:

    {
        query_id_1: {doc_id_1, doc_id_3, ...},
        query_id_2: {doc_id_1, doc_id_5, ...}
    }

    Args:
        filename (optional): Path to file with rankings. Defaults to
            "system_rankings.tsv".

    Returns:
        Dictionary with query IDs as keys and sets of documents as values.
    """
    ground_truth = {}
    dataset = ir_datasets.load(collection)
    for qrel in dataset.qrels_iter():
        if qrel.relevance != 0:
            if qrel.query_id not in ground_truth:
                ground_truth[qrel.query_id] = set()

            ground_truth[qrel.query_id].add(qrel.doc_id)

    return ground_truth


def get_precision(
    system_ranking: List[str], ground_truth: Set[str], k: int = 100
) -> float:
    """Computes Precision@k.

    Args:
        system_ranking: Ranked list of document IDs.
        ground_truth: Set of relevant document IDs.
        k: Cutoff. Only consider system rankings up to k.

    Returns:
        P@K (float).
    """
    # TODO
    
    top_k = system_ranking[:k]
    relevant_docs = 0  

    for doc in top_k:  
        if doc in ground_truth:  
            relevant_docs += 1

    return relevant_docs / k


def get_average_precision(
    system_ranking: List[str], ground_truth: Set[str]
) -> float:
    """Computes Average Precision (AP).

    Args:
        system_ranking: Ranked list of document IDs.
        ground_truth: Set of relevant document IDs.

    Returns:
        AP (float).
    """
    # TODO

    relevant_docs = 0
    precision_sum = 0.0

    for i, doc in enumerate(system_ranking, 1):  
        if doc in ground_truth:
            relevant_docs += 1
            precision_sum += relevant_docs / i

    return precision_sum / len(ground_truth) if relevant_docs else 0.0


def get_reciprocal_rank(
    system_ranking: List[str], ground_truth: Set[str]
) -> float:
    """Computes Reciprocal Rank (RR).

    Args:
        system_ranking: Ranked list of document IDs.
        ground_truth: Set of relevant document IDs.

    Returns:
        RR (float).
    """
    # TODO
    
    for i in range(len(system_ranking)):
        if system_ranking[i] in ground_truth:
            RR = 1/(i+1)
            break

    return RR


def get_mean_eval_measure(
    system_rankings: Dict[str, List[str]],
    ground_truths: Dict[str, Set[str]],
    eval_function: Callable,
) -> float:
    """Computes a mean of any evaluation measure over a set of queries.

    Args:
        system_rankings: Dict with query ID as key and a ranked list of
            document IDs as value.
        ground_truths: Dict with query ID as key and a set of relevant document
            IDs as value.
        eval_function: Callback function for the evaluation measure that mean
            is computed over.

    Returns:
        Mean evaluation measure (float).
    """
    # TODO

    total_score = 0.0
    
    for query_id, system_ranking in system_rankings.items():
        if query_id in ground_truths:
            ground_truth = ground_truths[query_id]
            total_score += eval_function(system_ranking, ground_truth)
    
    return total_score / len(system_rankings) if len(system_rankings) else 0.0


if __name__ == "__main__":
    system_rankings = load_rankings()
    ground_truths = load_ground_truth()
