package game.logic.sorts;

import game.entities.Dragon;
import util.Math;

import java.util.List;

public class SortMethods {

    /**
     * Sorts by age
     * @param list a Dragons ArrayList
     */
    public static void quickSort(List<Dragon> list){
        quickSort(list, 0, list.size()-1);
    }

    private static void quickSort(List<Dragon> list, int low, int high){

        // Check for empty or null array
        if (list == null || list.size() == 0){
            return;
        }
        if (low >= high){
            return;
        }

        // Get the pivot element from the middle of the list
        int middle = low + (high - low) / 2;
        int pivot = list.get(middle).getAge();

        // Make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j)
        {
            //Check until all values on left side array are lower than pivot
            while (list.get(i).getAge() < pivot) {
                i++;
            }
            //Check until all values on left side array are greater than pivot
            while (list.get(j).getAge() > pivot) {
                j--;
            }
            //Now compare values from both side of lists to see if they need swapping
            //After swapping move the iterator on both lists
            if (i <= j)
            {
                swap (list, i, j);
                i++;
                j--;
            }
        }
        //Do same operation as above recursively to sort two sub arrays
        if (low < j){
            quickSort(list, low, j);
        }
        if (high > i){
            quickSort(list, i, high);
        }
    }

    /**
     * Sorts by lives
     * @param list a Dragons ArrayList
     */
    public static void bubbleSort(List<Dragon> list){
        int n = list.size();
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                int x = list.get(j-1).getLives();
                int y = list.get(j).getLives();
                if(x > y){
                    swap(list, j-1, j);
                }
            }
        }
    }

    /**
     * Sorts by age
     * @param list a Dragons ArrayList
     */
    public static void selectionSort (List<Dragon> list) {
        int n = list.size();

        // One by one move boundary of unsorted sub-array
        for (int i = 0; i < n-1; i++)
        {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i+1; j < n; j++)
                if (list.get(j).getAge() < list.get(min_idx).getAge())
                    min_idx = j;

            // Swap the found minimum element with the first element
            swap(list, i, min_idx);
        }
    }

    /**
     * Sorts by fire_rate
     * @param list a Dragons ArrayList
     */
    public static void insertionSort(List<Dragon> list) {
        for (int i = 1; i < list.size() - 1; i++) {
            int j = i;
            while ((j > 0) && (list.get(j).getFire_rate() < list.get(j - 1).getFire_rate())) {
                swap(list, j, j - 1);
                j = j - 1;
            }
        }
    }

    /**
     * Randomize the order of the list
     * @param list a Dragons ArrayList
     */
    public static void unSort(List<Dragon> list){
        for (int i = 0; i < list.size(); i++){
            int rand = Math.random.nextInt(list.size());
            swap(list, i, rand);
        }
    }

    /**
     *Swap the positions of two nodes
     * @param list
     * @param i
     * @param j
     */

    private static void swap(List<Dragon> list, int i, int j){
        Dragon tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
