import string
from A2 import accuracy, precision, recall, f1
from typing import List, Tuple, Union
from numpy import ndarray
from sklearn.feature_extraction.text import ENGLISH_STOP_WORDS, TfidfVectorizer
from sklearn.naive_bayes import MultinomialNB
from sklearn.metrics import precision_score, recall_score, f1_score, accuracy_score


def load_data(path: str) -> Tuple[List[str], List[int]]:
    """Loads data from file. Each except first (header) is a datapoint
    containing ID, Label, Email (content) separated by "\t". Lables should be
    changed into integers with 1 for "spam" and 0 for "ham".

    Args:
        path: Path to file from which to load data

    Returns:
        List of email contents and a list of lobels coresponding to each email.
    """
    # TODO
    emails = []
    labels = []

    with open(path, 'r', encoding='utf-8') as file:
        next(file)
        for line in file:
            parts = line.strip().split('\t')
            if len(parts) == 3:
                if parts[1].strip().lower() == 'spam':
                    label = 1
                else:
                    label = 0

                email_content = parts[2]
                if email_content.startswith('"') and email_content.endswith('"'):
                    email_content = email_content[1:-1]  # Eliminar comillas dobles exteriores

                email_content = email_content.replace('""', '"')

                labels.append(label)
                emails.append(email_content)
    
    return emails, labels


def preprocess(doc: str) -> str:
    """Preprocesses text to prepare it for feature extraction.

    Args:
        doc: String comprising the unprocessed contents of some email file.

    Returns:
        String comprising the corresponding preprocessed text.
    """
    # TODO
    doc = doc.lower()
    doc = doc.translate(str.maketrans('', '', string.punctuation))
    words = doc.split()

    filtered_words = []
    for word in words:
        if word not in ENGLISH_STOP_WORDS:
            filtered_words.append(word)

    return ' '.join(filtered_words)


def preprocess_multiple(docs: List[str]) -> List[str]:
    """Preprocesses multiple texts to prepare them for feature extraction.

    Args:
        docs: List of strings, each consisting of the unprocessed contents
            of some email file.

    Returns:
        List of strings, each comprising the corresponding preprocessed
            text.
    """
    # TODO
    result = []
    for doc in docs:
        result.append(preprocess(doc))
    
    return result


def extract_features(
    train_dataset: List[str], test_dataset: List[str]
) -> Union[Tuple[ndarray, ndarray], Tuple[List[float], List[float]]]:
    """Extracts feature vectors from a preprocessed train and test datasets.

    Args:
        train_dataset: List of strings, each consisting of the preprocessed
            email content.
        test_dataset: List of strings, each consisting of the preprocessed
            email content.

    Returns:
        A tuple of of two lists. The lists contain extracted features for
          training and testing dataset respectively.
    """
    # TODO
    vectorizer = TfidfVectorizer()
    X_train = vectorizer.fit_transform(train_dataset)
    X_test = vectorizer.transform(test_dataset)
    return X_train, X_test


def train(X: ndarray, y: List[int]) -> object:
    """Trains a classifier on extracted feature vectors.

    Args:
        X: Numerical array-like object (2D) representing the instances.
        y: Numerical array-like object (1D) representing the labels.

    Returns:
        A trained model object capable of predicting over unseen sets of
            instances.
    """
    # TODO
    model = MultinomialNB()
    model.fit(X, y)
    return model


def evaluate(y: List[int], y_pred: List[int]) -> Tuple[float, float, float, float]:
    """Evaluates a model's predictive performance with respect to a labeled
    dataset.

    Args:
        y: Numerical array-like object (1D) representing the true labels.
        y_pred: Numerical array-like object (1D) representing the predicted
            labels.

    Returns:
        A tuple of four values: recall, precision, F_1, and accuracy.
    """
    # TODO
    # I will use the functions done in the A2.py
    precision_score = precision(y, y_pred)
    recall_score = recall(y, y_pred)
    f1_score = f1(y, y_pred)
    accuracy_score = accuracy(y, y_pred)
    return recall_score, precision_score, f1_score, accuracy_score

if __name__ == "__main__":
    print("Loading data...")
    train_data_raw, train_labels = load_data("data/train.tsv")
    test_data_raw, test_labels = load_data("data/test.tsv")

    print("Processing data...")
    train_data = preprocess_multiple(train_data_raw)
    test_data = preprocess_multiple(test_data_raw)

    print("Extracting features...")
    train_feature_vectors, test_feature_vectors = extract_features(
        train_data, test_data
    )

    print("Training...")
    classifier = train(train_feature_vectors, train_labels)

    print("Applying model on test data...")
    predicted_labels = classifier.predict(test_feature_vectors)

    print("Evaluating...")
    recall, precision, f1, accuracy = evaluate(test_labels, predicted_labels)

    print(f"Recall:\t{recall}")
    print(f"Precision:\t{precision}")
    print(f"F1:\t{f1}")
    print(f"Accuracy:\t{accuracy}")
