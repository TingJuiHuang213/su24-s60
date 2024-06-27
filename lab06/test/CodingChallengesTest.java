import org.junit.Test;
import static com.google.common.truth.Truth.assertWithMessage;

public class CodingChallengesTest {

    /**
     * 測試 missingNumber 方法，確保其能正確找到缺失的數字。
     */
    @Test
    public void testMissingNumber() {
        int[] values1 = {0, 1, 2, 4}; // 缺少3
        assertWithMessage("Missing number in [0, 1, 2, 4] should be 3")
                .that(CodingChallenges.missingNumber(values1)).isEqualTo(3);

        int[] values2 = {3, 0, 1}; // 缺少2
        assertWithMessage("Missing number in [3, 0, 1] should be 2")
                .that(CodingChallenges.missingNumber(values2)).isEqualTo(2);

        int[] values3 = {0}; // 缺少1
        assertWithMessage("Missing number in [0] should be 1")
                .that(CodingChallenges.missingNumber(values3)).isEqualTo(1);

        int[] values4 = {1}; // 缺少0
        assertWithMessage("Missing number in [1] should be 0")
                .that(CodingChallenges.missingNumber(values4)).isEqualTo(0);
    }

    /**
     * 測試 isPermutation 方法，確保其能正確判斷兩個字符串是否是對方的排列。
     */
    @Test
    public void testIsPermutation() {
        String s1a = "abc";
        String s1b = "cab";
        assertWithMessage("\"abc\" and \"cab\" should be permutations of each other")
                .that(CodingChallenges.isPermutation(s1a, s1b)).isTrue();

        String s2a = "abcd";
        String s2b = "abdc";
        assertWithMessage("\"abcd\" and \"abdc\" should be permutations of each other")
                .that(CodingChallenges.isPermutation(s2a, s2b)).isTrue();

        String s3a = "abc";
        String s3b = "ab";
        assertWithMessage("\"abc\" and \"ab\" should not be permutations of each other")
                .that(CodingChallenges.isPermutation(s3a, s3b)).isFalse();

        String s4a = "abc";
        String s4b = "abd";
        assertWithMessage("\"abc\" and \"abd\" should not be permutations of each other")
                .that(CodingChallenges.isPermutation(s4a, s4b)).isFalse();
    }
}
