package si2024.daniellinfonyealu.p03;

import java.util.ArrayList;

import core.game.Observation;
import tools.Vector2d;

public class QuickSort {

	public static void quicksort(ArrayList<Double> distances, ArrayList<Vector2d> positions, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(distances, positions, low, high);
            quicksort(distances, positions, low, pivotIndex - 1);
            quicksort(distances, positions, pivotIndex + 1, high);
        }
    }

    public static int partition(ArrayList<Double> distances, ArrayList<Vector2d> positions, int low, int high) {
        double pivot = distances.get(low);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (distances.get(j) < pivot) {
                i++;
                // Intercambiar distances[i] y distances[j]
                double tempDistance = distances.get(i);
                distances.set(i, distances.get(j));
                distances.set(j, tempDistance);
                
                // Intercambiar positions[i] y positions[j] simultáneamente
                Vector2d tempPosition = positions.get(i);
                positions.set(i, positions.get(j));
                positions.set(j, tempPosition);
            }
        }
        // Intercambiar distances[i+1] y distances[high] (o el pivote)
        double tempDistance = distances.get(i + 1);
        distances.set(i + 1, distances.get(high));
        distances.set(high, tempDistance);
        
        // Intercambiar positions[i+1] y positions[high] (o el pivote)
        Vector2d tempPosition = positions.get(i + 1);
        positions.set(i + 1, positions.get(high));
        positions.set(high, tempPosition);

        return i + 1;
    }
}
