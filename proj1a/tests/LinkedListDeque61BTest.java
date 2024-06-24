import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

    @Test
    /** In this test, we有三 different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("back");
        assertThat(lld1.toList()).containsExactly("back").inOrder();
        lld1.addFirst("middle");
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();
        lld1.addFirst("front");
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void isEmptyTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.isEmpty()).isTrue();
        lld1.addFirst("front");
        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.size()).isEqualTo(0);
        lld1.addFirst("front");
        assertThat(lld1.size()).isEqualTo(1);
        lld1.addLast("back");
        assertThat(lld1.size()).isEqualTo(2);
    }

    @Test
    public void getTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        assertThat(lld1.get(0)).isEqualTo("front");
        assertThat(lld1.get(1)).isEqualTo("middle");
        assertThat(lld1.get(2)).isEqualTo("back");
        assertThat(lld1.get(3)).isNull();
    }

    @Test
    public void getRecursiveTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        assertThat(lld1.getRecursive(0)).isEqualTo("front");
        assertThat(lld1.getRecursive(1)).isEqualTo("middle");
        assertThat(lld1.getRecursive(2)).isEqualTo("back");
        assertThat(lld1.getRecursive(3)).isNull();
    }

    @Test
    public void removeFirstTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        assertThat(lld1.removeFirst()).isEqualTo("front");
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();
        assertThat(lld1.removeFirst()).isEqualTo("middle");
        assertThat(lld1.toList()).containsExactly("back").inOrder();
        assertThat(lld1.removeFirst()).isEqualTo("back");
        assertThat(lld1.toList()).isEmpty();
    }

    @Test
    public void removeLastTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");
        assertThat(lld1.removeLast()).isEqualTo("back");
        assertThat(lld1.toList()).containsExactly("front", "middle").inOrder();
        assertThat(lld1.removeLast()).isEqualTo("middle");
        assertThat(lld1.toList()).containsExactly("front").inOrder();
        assertThat(lld1.removeLast()).isEqualTo("front");
        assertThat(lld1.toList()).isEmpty();
    }

    // 新增的测试用例
    @Test
    public void addFirstAndLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst(10);
        lld1.addLast(20);
        lld1.addFirst(5);
        lld1.addLast(30);
        assertThat(lld1.toList()).containsExactly(5, 10, 20, 30).inOrder();
    }

    @Test
    public void mixedOperationsTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        lld1.addFirst("first");
        lld1.addLast("last");
        assertThat(lld1.size()).isEqualTo(2);
        assertThat(lld1.removeFirst()).isEqualTo("first");
        assertThat(lld1.removeLast()).isEqualTo("last");
        assertThat(lld1.size()).isEqualTo(0);
    }

    @Test
    public void multipleRemovalsTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        assertThat(lld1.removeFirst()).isEqualTo(1);
        assertThat(lld1.removeLast()).isEqualTo(4);
        assertThat(lld1.toList()).containsExactly(2, 3).inOrder();
    }
}
