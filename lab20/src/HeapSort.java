public class HeapSort {

    /**
     * @param arr
     *
     * Sort the array arr using heap sort.
     * The heap sort algorithm is as follows:
     * 1. Turn the array into a heap using heapify.
     * 2. Repeatedly swap the root of the heap with the last element of the heap,
     *   then bubble down the new root in the unsorted part of the array.
     *
     * This is an in-place sort, so you shouldn't create any additional arrays.
     *
     * We've provided you with a slightly modified implementation of bubbleDown that you can use.
     * Read through it carefully and try to understand how it works.
     */
    public static void sort(int[] arr) {
        int n = arr.length;

        // 建立最大堆
        heapify(arr, n);

        // 一個一個地提取元素
        for (int i = n - 1; i > 0; i--) {
            // 將當前根（最大）移到末尾
            swap(arr, 0, i);

            // 呼叫 bubbleDown 以重新在縮小的堆上
            bubbleDown(arr, 0, i);
        }
    }

    /**
     * @param arr
     * @param n
     *
     * Turn the array into a heap. Repeatedly calls bubble down on each element,
     * starting from the end of the array.
     *
     * Suggested helper method that will make it easier for you to implement heap sort.
     */
    private static void heapify(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            bubbleDown(arr, i, n);
        }
    }

    /**
     * @param arr
     * @param i
     * @param limit
     *
     * Bubble down the element at index i in the array arr, stopping at index limit.
     * This allows you to bubble down only in the unsorted part of the array.
     * Any elements at indices >= limit will remain untouched.
     */
    private static void bubbleDown(int[] arr, int i, int limit) {
        int left = getLeftChild(i);
        int right = getRightChild(i);
        int maxChild = max(arr, left, right, limit);
        if (left >= limit) {
            return;
        }
        if (arr[i] < arr[maxChild]) {
            swap(arr, i, maxChild);
            bubbleDown(arr, maxChild, limit);
        }
    }

    /**
     * @param arr
     * @param i
     * @param j
     *
     * Swap the elements at indices i and j in the array arr.
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * @param arr
     * @param i
     * @param j
     * @param limit
     * @return the index of the maximum element between the elements at
     *         indices i and j in the array arr, stopping at index limit.
     *
     */
    private static int max(int[] arr, int i, int j, int limit) {
        if (j >= limit || arr[i] > arr[j]) {
            return i;
        }
        return j;
    }

    /**
     * @param i
     * @return the index of the left child of the node at index i
     *
     * Note that this is different from what we covered in the Heaps lab, as the root
     * of the heap is at index 0, not 1.
     */
    private static int getLeftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * @param i
     * @return the index of the right child of the node at index i
     *
     * Note that this is different from what we covered in the Heaps lab, as the root
     * of the heap is at index 0, not 1.
     */
    private static int getRightChild(int i) {
        return 2 * i + 2;
    }
}
