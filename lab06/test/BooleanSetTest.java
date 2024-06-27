import org.junit.Test;
import static com.google.common.truth.Truth.assertWithMessage;

public class BooleanSetTest {

    /**
     * 測試集合的基本操作，包括添加和移除元素，檢查大小和空狀態。
     */
    @Test
    public void testBasics() {
        BooleanSet aSet = new BooleanSet(100);
        // 檢查初始集合大小和空狀態
        assertWithMessage("Size is not zero upon instantiation").that(aSet.size()).isEqualTo(0);
        assertWithMessage("aSet should be empty upon instantiation").that(aSet.isEmpty()).isTrue();

        // 添加元素並檢查
        for (int i = 0; i < 100; i += 2) {
            aSet.add(i);
            assertWithMessage("aSet should contain " + i).that(aSet.contains(i)).isTrue();
        }

        // 檢查添加後的集合大小和非空狀態
        assertWithMessage("Size is not 50 after 50 calls to add").that(aSet.size()).isEqualTo(50);
        assertWithMessage("aSet should not be empty after adding elements").that(aSet.isEmpty()).isFalse();

        // 移除元素並檢查
        for (int i = 0; i < 100; i += 2) {
            aSet.remove(i);
            assertWithMessage("aSet should not contain " + i).that(aSet.contains(i)).isFalse();
        }

        // 檢查移除後的集合大小和空狀態
        assertWithMessage("aSet is not empty after removing all elements").that(aSet.isEmpty()).isTrue();
        assertWithMessage("Size is not zero after removing all elements").that(aSet.size()).isEqualTo(0);
    }

    /**
     * 測試 toIntArray 方法，確保其能正確返回集合中的所有元素作為數組。
     */
    @Test
    public void testToIntArray() {
        BooleanSet aSet = new BooleanSet(10);
        // 添加元素到集合中
        for (int i = 0; i < 10; i++) {
            aSet.add(i);
        }

        // 預期的數組
        int[] expectedArray = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertWithMessage("Array returned by toIntArray does not match expected array")
                .that(aSet.toIntArray()).isEqualTo(expectedArray);
    }

    /**
     * 測試重複添加元素，確保集合只包含唯一的元素。
     */
    @Test
    public void testAddDuplicates() {
        BooleanSet aSet = new BooleanSet(10);
        aSet.add(1);
        aSet.add(1);
        // 檢查集合大小和內容
        assertWithMessage("aSet should contain only one instance of 1").that(aSet.size()).isEqualTo(1);
        assertWithMessage("aSet should contain 1").that(aSet.contains(1)).isTrue();
    }

    /**
     * 測試移除不存在的元素，不應影響集合。
     */
    @Test
    public void testRemoveNonExistent() {
        BooleanSet aSet = new BooleanSet(10);
        aSet.add(1);
        aSet.remove(2); // 嘗試移除不存在的元素
        // 檢查集合大小和內容
        assertWithMessage("aSet should still contain 1 after trying to remove non-existent 2").that(aSet.contains(1)).isTrue();
        assertWithMessage("Size should still be 1 after trying to remove non-existent 2").that(aSet.size()).isEqualTo(1);
    }
}
