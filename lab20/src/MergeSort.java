public class MergeSort {

    /**
     * @param arr
     *
     * Sort the array arr using merge sort.
     * The merge sort algorithm is as follows:
     * 1. Split the collection to be sorted in half.
     * 2. Recursively call merge sort on each half.
     * 3. Merge the sorted half-lists.
     *
     */
    public static int[] sort(int[] arr) {
        if (arr.length < 2) {
            return arr;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        for (int i = 0; i < mid; i++) {
            left[i] = arr[i];
        }
        for (int i = mid; i < arr.length; i++) {
            right[i - mid] = arr[i];
        }
        return merge(sort(left), sort(right));
    }

    /**
     * @param a
     * @param b
     *
     * Merge the sorted half-lists.
     *
     * Suggested helper method that will make it easier for you to implement merge sort.
     */
    private static int[] merge(int[] a, int[] b) {
        int[] c = new int[a.length + b.length];
        int i = 0, j = 0, k = 0;
        while (i < a.length && j < b.length) {
            if (a[i] <= b[j]) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }
        while (i < a.length) {
            c[k++] = a[i++];
        }
        while (j < b.length) {
            c[k++] = b[j++];
        }
        return c;
    }
}
