package game.logic.Sorts;

import game.logic.Lists.intList;

public class sortMethods {
    public static void quickSort(intList list, int low, int high){

        //check for empty or null array
        if (list == null || list.getLarge() == 0){
            return;
        }

        if (low >= high){
            return;
        }

        //Get the pivot element from the middle of the list
        int middle = low + (high - low) / 2;
        int pivot = list.getByIndex(middle).getValue();

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j)
        {
            //Check until all values on left side array are lower than pivot
            while (list.getByIndex(i).getValue() < pivot) {
                i++;
            }
            //Check until all values on left side array are greater than pivot
            while (list.getByIndex(j).getValue() > pivot) {
                j--;
            }
            //Now compare values from both side of lists to see if they need swapping
            //After swapping move the iterator on both lists
            if (i <= j)
            {
                list.swap (i, j, list);
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

    public static void bubbleSort(intList list){
        int n = list.getLarge();
        for(int i=0; i < n; i++){
            for(int j=1; j < (n-i); j++){
                int x = list.getByIndex(j-1).getValue();
                int y = list.getByIndex(j).getValue();
                if(x > y){
                    //swap elements
                    list.swap(j-1, j, list);
                    int a = list.getByIndex(j-1).getValue();
                    int b = list.getByIndex(j).getValue();
                }

            }
        }
    }

}
