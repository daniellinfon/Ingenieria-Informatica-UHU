from collections import defaultdict
from typing import Dict, List


def get_word_frequencies(doc: str) -> Dict[str, int]:
    """Extracts word frequencies from a document.

    Args:
        doc: Document content given as a string.

    Returns:
        Dictionary with words as keys and their frequencies as values.
    """
    # TODO

    doc = doc.lower()
    word_freq = defaultdict(int)
    punctuation = ".,!?;:\"'()[]{}"
    no_punctuation = []

    for char in doc:
        if char in punctuation:
            no_punctuation.append(' ')
        else:
            no_punctuation.append(char)
    
    doc = ''.join(no_punctuation)

    for word in doc.split():
        word_freq[word] += 1

    return word_freq


def get_word_feature_vector(
    word_frequencies: Dict[str, int], vocabulary: List[str]
) -> List[int]:
    """Creates a feature vector for a document, comprising word frequencies
        over a vocabulary.

    Args:
        word_frequencies: Dictionary with words as keys and frequencies as
            values.
        vocabulary: List of words.

    Returns:
        List of length `len(vocabulary)` with respective frequencies as values.
    """
    # TODO
    frcuencies = [0] * len(vocabulary)

    for i, word in enumerate(vocabulary):
        if word in word_frequencies:
            frcuencies[i] = word_frequencies[word]

    return frcuencies
