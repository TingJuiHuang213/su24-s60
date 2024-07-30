public class QuickSort {

    /**
     * 使用快速排序對數組進行排序
     * @param arr 待排序數組
     * @return 排序後的數組
     */
    public static int[] sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
        return arr;
    }

    /**
     * 快速排序輔助方法
     * @param arr 數組
     * @param low 起始索引
     * @param high 結束索引
     */
    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int[] pi = partition(arr, low, high);

            quickSort(arr, low, pi[0]);
            quickSort(arr, pi[1], high);
        }
    }

    /**
     * 分區方法，使用3掃描分區算法
     * @param arr 數組
     * @param low 起始索引
     * @param high 結束索引
     * @return 分區後的索引
     */
    private static int[] partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int left = low;
        int right = high;

        while (left <= right) {
            while (left <= right && arr[left] < pivot) {
                left++;
            }
            while (left <= right && arr[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(arr, left, right);
                left++;
                right--;
            }
        }
        return new int[]{right, left};
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
}
