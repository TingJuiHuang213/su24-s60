import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

/**
 * 执行一些基本的数组双端队列测试。
 */
public class ArrayDeque61BTest {

    /**
     * 测试 addFirst 方法的基本情况。
     */
    @Test
    public void addFirstTestBasic() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("back");
        assertThat(ad.toList()).containsExactly("back").inOrder();
        ad.addFirst("middle");
        assertThat(ad.toList()).containsExactly("middle", "back").inOrder();
        ad.addFirst("front");
        assertThat(ad.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    /**
     * 测试 addLast 方法的基本情况。
     */
    @Test
    public void addLastTestBasic() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(ad.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    /**
     * 测试 isEmpty 方法。
     */
    @Test
    public void isEmptyTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        assertThat(ad.isEmpty()).isTrue();
        ad.addFirst("front");
        assertThat(ad.isEmpty()).isFalse();
    }

    /**
     * 测试 size 方法。
     */
    @Test
    public void sizeTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        assertThat(ad.size()).isEqualTo(0);
        ad.addFirst("front");
        assertThat(ad.size()).isEqualTo(1);
        ad.addLast("back");
        assertThat(ad.size()).isEqualTo(2);
    }

    /**
     * 测试 get 方法。
     */
    @Test
    public void getTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(ad.get(0)).isEqualTo("front");
        assertThat(ad.get(1)).isEqualTo("middle");
        assertThat(ad.get(2)).isEqualTo("back");
        assertThat(ad.get(3)).isNull();
        assertThat(ad.get(-1)).isNull();
    }

    /**
     * 测试 getRecursive 方法。
     */
    @Test
    public void getRecursiveTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(ad.getRecursive(0)).isEqualTo("front");
        assertThat(ad.getRecursive(1)).isEqualTo("middle");
        assertThat(ad.getRecursive(2)).isEqualTo("back");
        assertThat(ad.getRecursive(3)).isNull();
        assertThat(ad.getRecursive(-1)).isNull();
    }

    /**
     * 测试 removeFirst 方法。
     */
    @Test
    public void removeFirstTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(ad.removeFirst()).isEqualTo("front");
        assertThat(ad.toList()).containsExactly("middle", "back").inOrder();
        assertThat(ad.removeFirst()).isEqualTo("middle");
        assertThat(ad.toList()).containsExactly("back").inOrder();
        assertThat(ad.removeFirst()).isEqualTo("back");
        assertThat(ad.toList()).isEmpty();
    }

    /**
     * 测试 removeLast 方法。
     */
    @Test
    public void removeLastTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(ad.removeLast()).isEqualTo("back");
        assertThat(ad.toList()).containsExactly("front", "middle").inOrder();
        assertThat(ad.removeLast()).isEqualTo("middle");
        assertThat(ad.toList()).containsExactly("front").inOrder();
        assertThat(ad.removeLast()).isEqualTo("front");
        assertThat(ad.toList()).isEmpty();
    }

    /**
     * 测试在移除所有元素后添加新元素。
     */
    @Test
    public void addAfterRemoveTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addLast("front");
        ad.addLast("middle");
        ad.addLast("back");
        assertThat(ad.removeFirst()).isEqualTo("front");
        assertThat(ad.removeFirst()).isEqualTo("middle");
        assertThat(ad.removeFirst()).isEqualTo("back");
        assertThat(ad.isEmpty()).isTrue();

        ad.addFirst("newFront");
        assertThat(ad.toList()).containsExactly("newFront").inOrder();
        ad.addLast("newBack");
        assertThat(ad.toList()).containsExactly("newFront", "newBack").inOrder();
    }

    /**
     * 测试在两端添加元素。
     */
    @Test
    public void addFirstAndLastTest() {
        Deque61B<Integer> ad = new ArrayDeque61B<>();
        ad.addFirst(10);
        ad.addLast(20);
        ad.addFirst(5);
        ad.addLast(30);
        assertThat(ad.toList()).containsExactly(5, 10, 20, 30).inOrder();
    }

    /**
     * 测试混合操作。
     */
    @Test
    public void mixedOperationsTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("first");
        ad.addLast("last");
        assertThat(ad.size()).isEqualTo(2);
        assertThat(ad.removeFirst()).isEqualTo("first");
        assertThat(ad.removeLast()).isEqualTo("last");
        assertThat(ad.size()).isEqualTo(0);
    }

    /**
     * 测试多次移除。
     */
    @Test
    public void multipleRemovalsTest() {
        Deque61B<String> ad = new ArrayDeque61B<>();
        ad.addFirst("1");
        ad.addFirst("2");
        ad.addFirst("3");
        assertThat(ad.removeFirst()).isEqualTo("3");
        assertThat(ad.removeFirst()).isEqualTo("2");
        assertThat(ad.removeFirst()).isEqualTo("1");
        assertThat(ad.isEmpty()).isTrue();
    }
}
