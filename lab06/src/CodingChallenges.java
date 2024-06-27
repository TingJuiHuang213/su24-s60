import java.util.HashMap;
import java.util.Map;

public class CodingChallenges {

    /**
     * 返回一個長度為 N 的數組中缺失的數字。該數組包含從 0 到 N 的所有值，但缺少其中一個值。
     *
     * @param values 包含從 0 到 N 的所有值的數組，缺少其中一個值
     * @return 缺失的數字
     */
    public static int missingNumber(int[] values) {
        // 計算預期的總和
        int expectedSum = calculateExpectedSum(values.length);
        int actualSum = calculateActualSum(values);

        // 返回缺失的數字
        return expectedSum - actualSum;
    }

    /**
     * 計算從 0 到 n 的所有整數的總和
     *
     * @param n 數組的長度
     * @return 從 0 到 n 的所有整數的總和
     */
    private static int calculateExpectedSum(int n) {
        return n * (n + 1) / 2;
    }

    /**
     * 計算數組中所有值的總和
     *
     * @param values 數組
     * @return 數組中所有值的總和
     */
    private static int calculateActualSum(int[] values) {
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        return sum;
    }

    /**
     * 判斷字符串 s1 是否為字符串 s2 的排列。s1 是 s2 的排列當且僅當它包含與 s2 相同數量的每個字符。
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 如果 s1 是 s2 的排列，返回 true；否則返回 false
     */
    public static boolean isPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }

        Map<Character, Integer> charCountMap = new HashMap<>();
        populateCharCountMap(charCountMap, s1);

        return validatePermutation(charCountMap, s2);
    }

    /**
     * 填充字符計數映射，計算字符串中每個字符的出現次數
     *
     * @param charCountMap 字符計數映射
     * @param s 字符串
     */
    private static void populateCharCountMap(Map<Character, Integer> charCountMap, String s) {
        for (char c : s.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }
    }

    /**
     * 驗證第二個字符串是否能匹配第一個字符串的字符計數映射
     *
     * @param charCountMap 字符計數映射
     * @param s 字符串
     * @return 如果能匹配返回 true，否則返回 false
     */
    private static boolean validatePermutation(Map<Character, Integer> charCountMap, String s) {
        for (char c : s.toCharArray()) {
            if (!charCountMap.containsKey(c)) {
                return false;
            }
            charCountMap.put(c, charCountMap.get(c) - 1);
            if (charCountMap.get(c) < 0) {
                return false;
            }
        }
        return true;
    }
}
