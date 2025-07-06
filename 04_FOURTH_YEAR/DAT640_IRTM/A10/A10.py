from typing import List, Optional, Set, Tuple

PREFIX = "##"
UNKNOWN = "<unk>"


def initialize_vocabulary(word_corpus: List[str]) -> Set[str]:
    """Initializes the vocabulary with characters present in the corpus.

    Args:
        word_corpus: Corpus of words.

    Returns:
        Initial vocabulary.
    """
    # TODO
    vocabulary = set()
    
    for word in word_corpus:
        i = 0
        for char in word:
            if i == 0:
                vocabulary.add(char)          # Add the character as a token
                i += 1
            elif i > 0:
                vocabulary.add(PREFIX + char) # Add the version with the prefix ##
    
    return vocabulary


def tokenize(word: str, vocabulary: Set[str]) -> List[str]:
    """Tokenizes a word using the vocabulary.

    The tokenizer splits the word using the longest possible tokens in the
    vocabulary. For example, if the word is "surfing", and the vocabulary
    contains the tokens "sur", "surf", and "ing", then the tokenizer will
    return ["surf", "##ing"].
    Returns <unk> token if the word cannot be fully tokenized.

    Args:
        word: Word to tokenize.
        vocabulary: Vocabulary.

    Returns:
        List of tokens.
    """
    word = word.lower()
    tokens = []
    while word.lstrip(PREFIX):
        for i in range(len(word)):
            if word[: len(word) - i] in vocabulary:
                tokens.append(word[: len(word) - i])
                word = PREFIX + word[len(word) - i :]
                break
        else:
            return [UNKNOWN]
    return tokens


def score(
    pair_freq: int, subword_token1_freq: int, subword_token2_freq: int
) -> float:
    """Calculates the score for merging two subword tokens.

    Args:
        pair_freq: Frequency of the pair.
        subword_token1_freq: Frequency of the first subword token.
        subword_token2_freq: Frequency of the second subword token.

    Returns:
        Score.
    """
    return pair_freq / (subword_token1_freq * subword_token2_freq)


def get_new_subword_token(
    data: List[Tuple[List[str], int]], vocabulary: Set[str]
) -> Tuple[str, float]:
    """Finds the new subword token to add to the vocabulary.

    The new subword token is the pair of tokens that maximizes the score. In
    case of ties, the pair that appears first in the vocabulary is chosen.

    Args:
        data: List of tokenized words and their frequencies.
        vocabulary: Vocabulary.

    Returns:
        New subword token and its score.
    """
    # TODO
    pair_freqs = {}
    subword_freqs = {}

    # Count frequencies of subword pairs and individual subwords
    for tokens, freq in data:
        for i in range(len(tokens) - 1):
            pair = (tokens[i], tokens[i + 1])
            pair_freqs[pair] = pair_freqs.get(pair, 0) + freq
        for token in tokens:
            subword_freqs[token] = subword_freqs.get(token, 0) + freq

    #print(pair_freqs)
    best_pair = None
    best_score = -1

    # Calculate the score for each pair
    for (subword1, subword2), pair_freq in pair_freqs.items():
        score_value = score(pair_freq, subword_freqs[subword1], subword_freqs[subword2])
        if score_value > best_score or (score_value == best_score and subword1 + subword2 in vocabulary):
            best_pair = subword1 + subword2.lstrip(PREFIX)
            best_score = score_value

    return best_pair, best_score


def train(
    word_corpus: List[Tuple[str, int]],
    vocabulary: Set[str],
    num_iterations: Optional[int] = 4,
    max_vocab_size: Optional[int] = None,
) -> Set[str]:
    """Executes the WordPiece training algorithm.

    The algorithm iteratively merges subword tokens to create new ones. It stops
    when the number of iterations is reached or when the vocabulary reaches
    the maximum size.

    Args:
        word_corpus: Corpus of words and their frequencies.
        vocabulary: Vocabulary.
        num_iterations: Number of iterations to train the vocabulary. Defaults
            to 4.
        max_vocab_size: Maximum size of the vocabulary. Defaults to None.

    Returns:
        Vocabulary.
    """
    # TODO
    for _ in range(num_iterations):
        # Tokenize the current corpus using the current vocabulary
        tokenized_corpus = tokenize_corpus(word_corpus, vocabulary)
        
        # Find the best new token to add
        new_token, _ = get_new_subword_token(tokenized_corpus, vocabulary)
        
        # Add the new token to the vocabulary if it's valid
        if new_token and new_token not in vocabulary:
            vocabulary.add(new_token)
        
        # Stop if we reach the maximum vocabulary size
        if max_vocab_size is not None and len(vocabulary) >= max_vocab_size:
            break

    return vocabulary


def tokenize_corpus(
    corpus: List[Tuple[str, int]], vocabulary: Set[str]
) -> List[Tuple[List[str], int]]:
    """Tokenizes the corpus using the vocabulary.

    Args:
        corpus: Corpus of words and their frequencies.
        vocabulary: Vocabulary.

    Returns:
        List of tokenized words and their frequencies.
    """
    # TODO
    tokenized_corpus = []
    
    for word, freq in corpus:
        tokens = tokenize(word, vocabulary)
        tokenized_corpus.append((tokens, freq))
    
    return tokenized_corpus
