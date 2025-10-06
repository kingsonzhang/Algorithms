public class MergeSort{
    public void mergeSort(int[] array){
        if (array != null && array.length > 0){
            int temp[] = new int[array.length];
            this.mergeSortHelper(array, 0, array.length - 1, temp);
        }
    }

    private void mergeSortHelper(int[] array, int low, int high, int[] temp){
        if (low < high){
            int middle = low + (high - low) / 2;
            this.mergeSortHelper(array, low, middle, temp);
            this.mergeSortHelper(array, middle + 1, high, temp);
            merge(array, low, middle, high, temp);
        }
    }

    private void merge(int[] array, int low, int middle, int high, int[] temp){
        for (int i = low; i <= high; i++)
            temp[i] = array[i];

        int i = low;
        int j = middle + 1;
        int k = low;

        while (i <= middle && j <= high){
            if (temp[i] <= temp[j]){
                array[k] = temp[i];
                i++;
            }
            else{
                array[k] = temp[j];
                j++;
            }
            k++;
        }

        while (i <= middle){
            array[k] = temp[i];
            i++;
            k++;
        }

        while (j <= high){
            array[k] = temp[j];
            j++;
            k++;
        }
    }
}