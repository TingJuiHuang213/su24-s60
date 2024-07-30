public class QuickSort {

    /**
     * @param arr
     *
     * Sort the array arr using quicksort with the 3-scan partition algorithm.
     * The quicksort algorithm is as follows:
     * 1. Select a pivot, partition array in place around the pivot.
     * 2. Recursively call quicksort on each subsection of the modified array.
     */
    public static int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    /**
     * @param arr
     * @param start
     * @param end
     *
     * Helper method for sort: runs quicksort algorithm on array from [start:end)
     */
    private static void quickSort(int[] arr, int start, int end) {
        if (start < end) {
            int[] pi = partition(arr, start, end);

            quickSort(arr, start, pi[0]);
            quickSort(arr, pi[1], end);
        }
    }

    /**
     * @param arr
     * @param start
     * @param end
     *
     * Partition the array in-place following the 3-scan partitioning scheme.
     * You may assume that first item is always selected as the pivot.
     *
     * Returns a length-2 int array of indices:
     * [end index of "less than" section, start index of "greater than" section]
     *
     * Most of the code for quicksort is in this function
     */
    private static int[] partition(int[] arr, int start, int end) {
        int pivot = arr[start];
        int i = start - 1, j = end + 1;

        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);

            do {
                j--;
            } while (arr[j] > pivot);

            if (i >= j) {
                return new int[]{j, j + 1};
            }

            swap(arr, i, j);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
