import deque.Deque61B;
import deque.LinkedListDeque61B;
import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;

public class LinkedListDeque61BTest {

    @Test
    public void addFirstTestBasic() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        deque.addFirst("back");
        assertThat(deque.toList()).containsExactly("back").inOrder();
        deque.addFirst("middle");
        assertThat(deque.toList()).containsExactly("middle", "back").inOrder();
        deque.addFirst("front");
        assertThat(deque.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void addLastTestBasic() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");
        assertThat(deque.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void isEmptyTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        assertThat(deque.isEmpty()).isTrue();
        deque.addFirst("front");
        assertThat(deque.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        assertThat(deque.size()).isEqualTo(0);
        deque.addFirst("front");
        assertThat(deque.size()).isEqualTo(1);
        deque.addLast("back");
        assertThat(deque.size()).isEqualTo(2);
    }

    @Test
    public void getTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");
        assertThat(deque.get(0)).isEqualTo("front");
        assertThat(deque.get(1)).isEqualTo("middle");
        assertThat(deque.get(2)).isEqualTo("back");
        assertThat(deque.get(3)).isNull();
        assertThat(deque.get(-1)).isNull();
    }

    @Test
    public void getRecursiveTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");
        assertThat(deque.getRecursive(0)).isEqualTo("front");
        assertThat(deque.getRecursive(1)).isEqualTo("middle");
        assertThat(deque.getRecursive(2)).isEqualTo("back");
        assertThat(deque.getRecursive(3)).isNull();
        assertThat(deque.getRecursive(-1)).isNull();
    }

    @Test
    public void removeFirstTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");
        assertThat(deque.removeFirst()).isEqualTo("front");
        assertThat(deque.toList()).containsExactly("middle", "back").inOrder();
        assertThat(deque.removeFirst()).isEqualTo("middle");
        assertThat(deque.toList()).containsExactly("back").inOrder();
        assertThat(deque.removeFirst()).isEqualTo("back");
        assertThat(deque.toList()).isEmpty();
    }

    @Test
    public void removeLastTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");
        assertThat(deque.removeLast()).isEqualTo("back");
        assertThat(deque.toList()).containsExactly("front", "middle").inOrder();
        assertThat(deque.removeLast()).isEqualTo("middle");
        assertThat(deque.toList()).containsExactly("front").inOrder();
        assertThat(deque.removeLast()).isEqualTo("front");
        assertThat(deque.toList()).isEmpty();
    }

    @Test
    public void addAfterRemoveTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");
        assertThat(deque.removeFirst()).isEqualTo("front");
        assertThat(deque.removeFirst()).isEqualTo("middle");
        assertThat(deque.removeFirst()).isEqualTo("back");
        assertThat(deque.isEmpty()).isTrue();

        deque.addFirst("newFront");
        assertThat(deque.toList()).containsExactly("newFront").inOrder();
        deque.addLast("newBack");
        assertThat(deque.toList()).containsExactly("newFront", "newBack").inOrder();
    }

    @Test
    public void addFirstAndLastTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();
        deque.addFirst(10);
        deque.addLast(20);
        deque.addFirst(5);
        deque.addLast(30);
        assertThat(deque.toList()).containsExactly(5, 10, 20, 30).inOrder();
    }

    @Test
    public void mixedOperationsTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        deque.addFirst("first");
        deque.addLast("last");
        assertThat(deque.size()).isEqualTo(2);
        assertThat(deque.removeFirst()).isEqualTo("first");
        assertThat(deque.removeLast()).isEqualTo("last");
        assertThat(deque.size()).isEqualTo(0);
    }

    @Test
    public void multipleRemovalsTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        deque.addFirst("1");
        deque.addFirst("2");
        deque.addFirst("3");
        assertThat(deque.removeFirst()).isEqualTo("3");
        assertThat(deque.removeFirst()).isEqualTo("2");
        assertThat(deque.removeFirst()).isEqualTo("1");
        assertThat(deque.isEmpty()).isTrue();
    }

    @Test
    public void addTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();
        for (int i = 0; i < 20; i++) {
            deque.addLast(i);
        }
        assertThat(deque.size()).isEqualTo(20);
        for (int i = 0; i < 20; i++) {
            assertThat(deque.get(i)).isEqualTo(i);
        }
        for (int i = 0; i < 20; i++) {
            deque.removeFirst();
        }
        for (int i = 0; i < 20; i++) {
            deque.addFirst(i);
        }
        assertThat(deque.size()).isEqualTo(20);
        for (int i = 0; i < 20; i++) {
            assertThat(deque.get(i)).isEqualTo(19 - i);
        }
    }

    @Test
    public void advancedResizeTest() {
        Deque61B<Integer> deque = new LinkedListDeque61B<>();
        for (int i = 0; i < 100; i++) {
            deque.addLast(i);
        }
        assertThat(deque.size()).isEqualTo(100);
        for (int i = 0; i < 100; i++) {
            assertThat(deque.get(i)).isEqualTo(i);
        }
        for (int i = 0; i < 75; i++) {
            deque.removeFirst();
        }
        assertThat(deque.size()).isEqualTo(25);
        for (int i = 0; i < 25; i++) {
            assertThat(deque.get(i)).isEqualTo(i + 75);
        }
        for (int i = 0; i < 25; i++) {
            deque.addFirst(i);
        }
        assertThat(deque.size()).isEqualTo(50);
        for (int i = 0; i < 25; i++) {
            assertThat(deque.get(i)).isEqualTo(24 - i);
        }
        for (int i = 25; i < 50; i++) {
            assertThat(deque.get(i)).isEqualTo(i + 50);
        }
    }

    @Test
    public void addAfterRemoveToEmptyTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("front");
        deque.addLast("middle");
        deque.addLast("back");
        assertThat(deque.removeFirst()).isEqualTo("front");
        assertThat(deque.removeFirst()).isEqualTo("middle");
        assertThat(deque.removeFirst()).isEqualTo("back");
        assertThat(deque.isEmpty()).isTrue();

        deque.addFirst("newFront");
        assertThat(deque.toList()).containsExactly("newFront").inOrder();
        deque.addLast("newBack");
        assertThat(deque.toList()).containsExactly("newFront", "newBack").inOrder();
    }

    @Test
    public void addAfterRemoveComprehensiveTest() {
        Deque61B<String> deque = new LinkedListDeque61B<>();
        deque.addLast("first");
        deque.addLast("second");
        deque.addLast("third");
        assertThat(deque.removeFirst()).isEqualTo("first");
        assertThat(deque.removeFirst()).isEqualTo("second");
        assertThat(deque.removeFirst()).isEqualTo("third");
        assertThat(deque.isEmpty()).isTrue();

        deque.addLast("newFirst");
        assertThat(deque.toList()).containsExactly("newFirst").inOrder();
        deque.addFirst("newSecond");
        assertThat(deque.toList()).containsExactly("newSecond", "newFirst").inOrder();
    }
}
