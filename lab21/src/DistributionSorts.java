import java.util.Arrays;

public class DistributionSorts {

    /* 使用計數排序對 ARR 進行破壞性排序。假設 ARR 中僅包含 0, 1, ..., 9。 */
    public static void countingSort(int[] arr) {
        // 初始化計數數組並填充每個數字的出現次數
        int[] counts = countOccurrences(arr);

        // 根據計數將數字重新排序
        fillArrayWithSortedValues(arr, counts);
    }

    /* 計算每個數字的出現次數並返回計數數組 */
    private static int[] countOccurrences(int[] arr) {
        int[] counts = new int[10];
        for (int num : arr) {
            counts[num]++;
        }
        return counts;
    }

    /* 根據計數將數字按順序填充回數組 */
    private static void fillArrayWithSortedValues(int[] arr, int[] counts) {
        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                arr[index++] = i;
                counts[i]--;
            }
        }
    }

    /* 使用 LSD 基數排序對 ARR 進行破壞性排序。 */
    public static void lsdRadixSort(int[] arr) {
        int maxDigit = findMaxDigits(arr);
        for (int d = 0; d < maxDigit; d++) {
            sortBasedOnDigit(arr, d);
        }
    }

    /* 根據指定的位數進行排序 */
    private static void sortBasedOnDigit(int[] arr, int digit) {
        int divisor = calculateDivisor(digit);
        int[] counts = new int[10];
        int[] sorted = new int[arr.length];

        // 計算每個數字在指定位上的出現次數
        for (int num : arr) {
            int digitValue = (num / divisor) % 10;
            counts[digitValue]++;
        }

        // 計算累積位置
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        // 放置元素到排序後的數組中，保證穩定性
        for (int i = arr.length - 1; i >= 0; i--) {
            int digitValue = (arr[i] / divisor) % 10;
            sorted[--counts[digitValue]] = arr[i];
        }

        // 將排序結果複製回原數組
        System.arraycopy(sorted, 0, arr, 0, arr.length);
    }

    /* 計算數組中最大數字的位數 */
    private static int findMaxDigits(int[] arr) {
        int maxDigitsSoFar = 0;
        for (int num : arr) {
            int numDigits = (int) (Math.log10(num) + 1);
            if (numDigits > maxDigitsSoFar) {
                maxDigitsSoFar = numDigits;
            }
        }
        return maxDigitsSoFar;
    }

    /* 計算除數以用於提取位數 */
    private static int calculateDivisor(int digit) {
        return (int) Math.pow(10, digit);
    }

    public static void main(String[] args) {
        runCountingSort(20);
        runLSDRadixSort(3);
        runLSDRadixSort(30);
    }

    private static void runCountingSort(int len) {
        int[] arr1 = generateRandomArray(len, 9);
        System.out.println("原始數組: " + Arrays.toString(arr1));
        countingSort(arr1);
        System.out.println("排序後的數組: " + Arrays.toString(arr1));
    }

    private static void runLSDRadixSort(int len) {
        int[] arr2 = generateRandomArray(len, 9999);
        System.out.println("原始數組: " + Arrays.toString(arr2));
        lsdRadixSort(arr2);
        System.out.println("排序後的數組: " + Arrays.toString(arr2));
    }

    private static int[] generateRandomArray(int length, int maxValue) {
        int[] arr = new int[length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }
}
