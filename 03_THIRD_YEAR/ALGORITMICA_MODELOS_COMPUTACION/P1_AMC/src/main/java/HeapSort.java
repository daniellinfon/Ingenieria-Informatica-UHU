
class HeapSort {
    
    
    public static void sortX(Punto arr[]) {
        int N = arr.length;

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--) {
            heapify(arr, N, i, true);
        }

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            Punto temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0, true);
        }
    }

    public static void sortY(Punto arr[]) {
        int N = arr.length;

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--) {
            heapify(arr, N, i, false);
        }

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            Punto temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0, false);
        }
    }

    // To heapify a subtree rooted with node i which is
    // an index in arr[]. n is size of heap
    public static void heapify(Punto arr[], int N, int i, boolean x) {
        if (x) {
            int largest = i; // Initialize largest as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is larger than root
            if (l < N && arr[l].getx() > arr[largest].getx()) {
                largest = l;
            }

            // If right child is larger than largest so far
            if (r < N && arr[r].getx() > arr[largest].getx()) {
                largest = r;
            }

            // If largest is not root
            if (largest != i) {
                Punto swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                // Recursively heapify the affected sub-tree
                heapify(arr, N, largest, true);
            }
        } else {

            int largest = i; // Initialize largest as root
            int l = 2 * i + 1; // left = 2*i + 1
            int r = 2 * i + 2; // right = 2*i + 2

            // If left child is larger than root
            if (l < N && arr[l].gety() > arr[largest].gety()) {
                largest = l;
            }

            // If right child is larger than largest so far
            if (r < N && arr[r].gety() > arr[largest].gety()) {
                largest = r;
            }

            // If largest is not root
            if (largest != i) {
                Punto swap = arr[i];
                arr[i] = arr[largest];
                arr[largest] = swap;

                // Recursively heapify the affected sub-tree
                heapify(arr, N, largest, false);

            }

        }
    }
}
