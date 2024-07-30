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
        buildMaxHeap(arr, n);

        // 一個一個提取元素
        for (int end = n - 1; end > 0; end--) {
            // 將根節點與末尾節點交換
            swap(arr, 0, end);

            // 呼叫 siftDown 重新整理堆
            siftDown(arr, 0, end);
        }
    }

    /**
     * Turn the array into a heap. Repeatedly calls bubble down on each element,
     * starting from the end of the array.
     *
     * Suggested helper method that will make it easier for you to implement heap sort.
     */
    private static void buildMaxHeap(int[] arr, int n) {
        for (int start = (n - 2) / 2; start >= 0; start--) {
            siftDown(arr, start, n);
        }
    }

    /**
     * Bubble down the element at index i in the array arr, stopping at index limit.
     * This allows you to bubble down only in the unsorted part of the array.
     * Any elements at indices >= limit will remain untouched.
     */
    private static void siftDown(int[] arr, int start, int end) {
        int root = start;
        while (leftChild(root) < end) {
            int child = leftChild(root);
            int swap = root;

            if (arr[swap] < arr[child]) {
                swap = child;
            }
            if (child + 1 < end && arr[swap] < arr[child + 1]) {
                swap = child + 1;
            }
            if (swap == root) {
                return;
            } else {
                swap(arr, root, swap);
                root = swap;
            }
        }
    }

    /**
     * 交換數組中的兩個元素
     * @param arr 數組
     * @param i 索引1
     * @param j 索引2
     */
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 獲取最大值的索引
     * @param arr 數組
     * @param i 索引1
     * @param j 索引2
     * @param limit 限制索引
     * @return 最大值的索引
     */
    private static int max(int[] arr, int i, int j, int limit) {
        if (j >= limit || arr[i] > arr[j]) {
            return i;
        }
        return j;
    }

    /**
     * 獲取左子節點索引
     * @param i 當前節點索引
     * @return 左子節點索引
     */
    private static int leftChild(int i) {
        return 2 * i + 1;
    }

    /**
     * 獲取右子節點索引
     * @param i 當前節點索引
     * @return 右子節點索引
     */
    private static int rightChild(int i) {
        return 2 * i + 2;
    }
}
