from typing import List, Union
from collections import Counter

def get_unique_elements(
    lst: List[Union[str, int]], n: int = 1
) -> List[Union[str, int]]:
    """Given a list of elements returns those that repeat at least n times. The
    output list should contain all unique elements and they should be returned
    in the same order as they first appear in the input list.

    Args:
        lst: Input list
        n (optional): Minimum number of times an element should be repeated to
            be returned. Defaults to 1.

    Returns:
        List of unique items
    """
    
    element_counts = Counter(lst)
    result = []
    seen = set()  

    for element in lst:
       if element_counts[element] >= n and element not in seen:
           result.append(element)
           seen.add(element)
   
    return result

    
    
