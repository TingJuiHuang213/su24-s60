public class HeapSort {

    /**
     * 使用堆排序對數組進行排序
     * @param arr 待排序數組
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
     * 建立最大堆
     * @param arr 數組
     * @param n 數組長度
     */
    private static void buildMaxHeap(int[] arr, int n) {
        for (int start = (n - 2) / 2; start >= 0; start--) {
            siftDown(arr, start, n);
        }
    }

    /**
     * 將節點下沉
     * @param arr 數組
     * @param start 起始節點
     * @param end 結束節點
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
