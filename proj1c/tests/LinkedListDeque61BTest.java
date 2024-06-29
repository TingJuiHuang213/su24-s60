import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class LinkedListDeque61BTest {

    @Test
    public void addFirstTestBasic() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        lld.addFirst("back");
        assertThat(lld.toList()).containsExactly("back").inOrder();
        lld.addFirst("middle");
        assertThat(lld.toList()).containsExactly("middle", "back").inOrder();
        lld.addFirst("front");
        assertThat(lld.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void addLastTestBasic() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        lld.addLast("front");
        lld.addLast("middle");
        lld.addLast("back");
        assertThat(lld.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void isEmptyTest() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        assertThat(lld.isEmpty()).isTrue();
        lld.addFirst("front");
        assertThat(lld.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        assertThat(lld.size()).isEqualTo(0);
        lld.addFirst("front");
        assertThat(lld.size()).isEqualTo(1);
        lld.addLast("back");
        assertThat(lld.size()).isEqualTo(2);
    }

    @Test
    public void getTest() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        lld.addLast("front");
        lld.addLast("middle");
        lld.addLast("back");
        assertThat(lld.get(0)).isEqualTo("front");
        assertThat(lld.get(1)).isEqualTo("middle");
        assertThat(lld.get(2)).isEqualTo("back");
        assertThat(lld.get(3)).isNull();
        assertThat(lld.get(-1)).isNull();
    }

    @Test
    public void getRecursiveTest() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        lld.addLast("front");
        lld.addLast("middle");
        lld.addLast("back");
        assertThat(lld.getRecursive(0)).isEqualTo("front");
        assertThat(lld.getRecursive(1)).isEqualTo("middle");
        assertThat(lld.getRecursive(2)).isEqualTo("back");
        assertThat(lld.getRecursive(3)).isNull();
        assertThat(lld.getRecursive(-1)).isNull();
    }

    @Test
    public void removeFirstTest() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        lld.addLast("front");
        lld.addLast("middle");
        lld.addLast("back");
        assertThat(lld.removeFirst()).isEqualTo("front");
        assertThat(lld.toList()).containsExactly("middle", "back").inOrder();
        assertThat(lld.removeFirst()).isEqualTo("middle");
        assertThat(lld.toList()).containsExactly("back").inOrder();
        assertThat(lld.removeFirst()).isEqualTo("back");
        assertThat(lld.toList()).isEmpty();
    }

    @Test
    public void removeLastTest() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        lld.addLast("front");
        lld.addLast("middle");
        lld.addLast("back");
        assertThat(lld.removeLast()).isEqualTo("back");
        assertThat(lld.toList()).containsExactly("front", "middle").inOrder();
        assertThat(lld.removeLast()).isEqualTo("middle");
        assertThat(lld.toList()).containsExactly("front").inOrder();
        assertThat(lld.removeLast()).isEqualTo("front");
        assertThat(lld.toList()).isEmpty();
    }

    @Test
    public void addAfterRemoveTest() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        lld.addLast("front");
        lld.addLast("middle");
        lld.addLast("back");
        assertThat(lld.removeFirst()).isEqualTo("front");
        assertThat(lld.removeFirst()).isEqualTo("middle");
        assertThat(lld.removeFirst()).isEqualTo("back");
        assertThat(lld.isEmpty()).isTrue();

        lld.addFirst("newFront");
        assertThat(lld.toList()).containsExactly("newFront").inOrder();
        lld.addLast("newBack");
        assertThat(lld.toList()).containsExactly("newFront", "newBack").inOrder();
    }

    @Test
    public void addFirstAndLastTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        lld.addFirst(10);
        lld.addLast(20);
        lld.addFirst(5);
        lld.addLast(30);
        assertThat(lld.toList()).containsExactly(5, 10, 20, 30).inOrder();
    }

    @Test
    public void mixedOperationsTest() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        lld.addFirst("first");
        lld.addLast("last");
        assertThat(lld.size()).isEqualTo(2);
        assertThat(lld.removeFirst()).isEqualTo("first");
        assertThat(lld.removeLast()).isEqualTo("last");
        assertThat(lld.size()).isEqualTo(0);
    }

    @Test
    public void multipleRemovalsTest() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        lld.addFirst("1");
        lld.addFirst("2");
        lld.addFirst("3");
        assertThat(lld.removeFirst()).isEqualTo("3");
        assertThat(lld.removeFirst()).isEqualTo("2");
        assertThat(lld.removeFirst()).isEqualTo("1");
        assertThat(lld.isEmpty()).isTrue();
    }

    // 新增測試用於覆蓋add方法的測試
    @Test
    public void addTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        for (int i = 0; i < 20; i++) {
            lld.addLast(i);
        }
        assertThat(lld.size()).isEqualTo(20);
        for (int i = 0; i < 20; i++) {
            assertThat(lld.get(i)).isEqualTo(i);
        }
        for (int i = 0; i < 20; i++) {
            lld.removeFirst();
        }
        for (int i = 0; i < 20; i++) {
            lld.addFirst(i);
        }
        assertThat(lld.size()).isEqualTo(20);
        for (int i = 0; i < 20; i++) {
            assertThat(lld.get(i)).isEqualTo(19 - i);
        }
    }

    // 新增測試用於覆蓋resize方法的測試
    @Test
    public void advancedResizeTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        for (int i = 0; i < 100; i++) {
            lld.addLast(i);
        }
        assertThat(lld.size()).isEqualTo(100);
        for (int i = 0; i < 100; i++) {
            assertThat(lld.get(i)).isEqualTo(i);
        }
        for (int i = 0; i < 75; i++) {
            lld.removeFirst();
        }
        assertThat(lld.size()).isEqualTo(25);
        for (int i = 0; i < 25; i++) {
            assertThat(lld.get(i)).isEqualTo(i + 75);
        }
        for (int i = 0; i < 25; i++) {
            lld.addFirst(i);
        }
        assertThat(lld.size()).isEqualTo(50);
        for (int i = 0; i < 25; i++) {
            assertThat(lld.get(i)).isEqualTo(24 - i);
        }
        for (int i = 25; i < 50; i++) {
            assertThat(lld.get(i)).isEqualTo(i + 50);
        }
    }

    @Test
    public void addAfterRemoveToEmptyTest() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        lld.addLast("front");
        lld.addLast("middle");
        lld.addLast("back");
        assertThat(lld.removeFirst()).isEqualTo("front");
        assertThat(lld.removeFirst()).isEqualTo("middle");
        assertThat(lld.removeFirst()).isEqualTo("back");
        assertThat(lld.isEmpty()).isTrue();

        lld.addFirst("newFront");
        assertThat(lld.toList()).containsExactly("newFront").inOrder();
        lld.addLast("newBack");
        assertThat(lld.toList()).containsExactly("newFront", "newBack").inOrder();
    }

    // 重新增加测试确保覆盖所有可能的情景
    @Test
    public void addAfterRemoveComprehensiveTest() {
        Deque61B<String> lld = new LinkedListDeque61B<>();
        lld.addLast("first");
        lld.addLast("second");
        lld.addLast("third");
        assertThat(lld.removeFirst()).isEqualTo("first");
        assertThat(lld.removeFirst()).isEqualTo("second");
        assertThat(lld.removeFirst()).isEqualTo("third");
        assertThat(lld.isEmpty()).isTrue();

        lld.addLast("newFirst");
        assertThat(lld.toList()).containsExactly("newFirst").inOrder();
        lld.addFirst("newSecond");
        assertThat(lld.toList()).containsExactly("newSecond", "newFirst").inOrder();
    }
}
