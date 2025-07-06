from typing import List


def get_confusion_matrix(
    actual: List[int], predicted: List[int]
) -> List[List[int]]:
    """Computes confusion matrix from lists of actual or predicted labels.

    Args:
        actual: List of integers (0 or 1) representing the actual classes of
            some instances.
        predicted: List of integers (0 or 1) representing the predicted classes
            of the corresponding instances.

    Returns:
        List of two lists of length 2 each, representing the confusion matrix.
    """
    # TODO
    TP = TN = FP = FN = 0

    for i, act in enumerate(actual):
        if act == 0 and predicted[i] == 0:
            TN += 1
        elif act == 0 and predicted[i] == 1:
            FP += 1
        elif act == 1 and predicted[i] == 0:
            FN += 1
        elif act == 1 and predicted[i] == 1:
            TP += 1
    
    confusion_matrix = [[TN, FP],
                        [FN, TP]]
    
    return confusion_matrix




def accuracy(actual: List[int], predicted: List[int]) -> float:
    """Computes the accuracy from lists of actual or predicted labels.

    Args:
        actual: List of integers (0 or 1) representing the actual classes of
            some instances.
        predicted: List of integers (0 or 1) representing the predicted classes
            of the corresponding instances.

    Returns:
        Accuracy as a float.
    """
    # TODO
    confusion_matrix = get_confusion_matrix(actual, predicted)
    TN, FP = confusion_matrix[0]
    FN, TP = confusion_matrix[1]
    total = TP + TN + FP + FN
    if total == 0:
        return 0.0
    
    return (TN + TP) / total


def precision(actual: List[int], predicted: List[int]) -> float:
    """Computes the precision from lists of actual or predicted labels.

    Args:
        actual: List of integers (0 or 1) representing the actual classes of
            some instances.
        predicted: List of integers (0 or 1) representing the predicted classes
            of the corresponding instances.

    Returns:
        Precision as a float.
    """
    # TODO
    confusion_matrix = get_confusion_matrix(actual, predicted)
    TN, FP = confusion_matrix[0]
    FN, TP = confusion_matrix[1]
    total = TP + FP
    if total == 0:
        return 0.0
    
    return TP / total


def recall(actual: List[int], predicted: List[int]) -> float:
    """Computes the recall from lists of actual or predicted labels.

    Args:
        actual: List of integers (0 or 1) representing the actual classes of
            some instances.
        predicted: List of integers (0 or 1) representing the predicted classes
            of the corresponding instances.

    Returns:
        Recall as a float.
    """
    # TODO
    confusion_matrix = get_confusion_matrix(actual, predicted)
    TN, FP = confusion_matrix[0]
    FN, TP = confusion_matrix[1]
    total = TP + FN
    if total == 0:
        return 0.0
    
    return TP / total


def f1(actual: List[int], predicted: List[int]) -> float:
    """Computes the F1-score from lists of actual or predicted labels.

    Args:
        actual: List of integers (0 or 1) representing the actual classes of
            some instances.
        predicted: List of integers (0 or 1) representing the predicted classes
            of the corresponding instances.

    Returns:
        float of harmonic mean of precision and recall.
    """
    # TODO
    confusion_matrix = get_confusion_matrix(actual, predicted)
    TN, FP = confusion_matrix[0]
    FN, TP = confusion_matrix[1]
    R = recall(actual, predicted)
    P = precision(actual, predicted)

    total = R + P
    if total == 0:
        return 0.0
    
    return (2*R*P)/total


def false_positive_rate(actual: List[int], predicted: List[int]) -> float:
    """Computes the false positive rate from lists of actual or predicted
        labels.

    Args:
        actual: List of integers (0 or 1) representing the actual classes of
            some instances.
        predicted: List of integers (0 or 1) representing the predicted classes
            of the corresponding instances.

    Returns:
        float of number of instances incorrectly classified as positive divided
            by number of actually negative instances.
    """
    # TODO
    confusion_matrix = get_confusion_matrix(actual, predicted)
    TN, FP = confusion_matrix[0]
    FN, TP = confusion_matrix[1]

    if FP + TN == 0:
        return 0.0
    
    return FP / (FP + TN)


def false_negative_rate(actual: List[int], predicted: List[int]) -> float:
    """Computes the false negative rate from lists of actual or predicted
        labels.

    Args:
        actual: List of integers (0 or 1) representing the actual classes of
            some instances.
        predicted: List of integers (0 or 1) representing the predicted classes
            of the corresponding instances.

    Returns:
        float of number of instances incorrectly classified as negative divided
            by number of actually positive instances.
    """
    # TODO
    confusion_matrix = get_confusion_matrix(actual, predicted)
    TN, FP = confusion_matrix[0]
    FN, TP = confusion_matrix[1]

    if FN + TP == 0:
        return 0.0
    
    return FN / (FN + TP)
