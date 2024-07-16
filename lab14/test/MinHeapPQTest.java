import org.junit.Test;

import java.util.NoSuchElementException;

import static com.google.common.truth.Truth.assertWithMessage;

public class MinHeapPQTest {

    @Test
    public void testInsertAndPeek() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("a", 5);
        pq.insert("b", 3);
        pq.insert("c", 8);
        pq.insert("d", 1);

        assertWithMessage("Peek should return the item with the smallest priority")
                .that(pq.peek()).isEqualTo("d");
    }

    @Test
    public void testPoll() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("a", 5);
        pq.insert("b", 3);
        pq.insert("c", 8);
        pq.insert("d", 1);

        assertWithMessage("Poll should return the item with the smallest priority")
                .that(pq.poll()).isEqualTo("d");
        assertWithMessage("After polling, peek should return the next smallest item")
                .that(pq.peek()).isEqualTo("b");
    }

    @Test
    public void testChangePriority() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("a", 5);
        pq.insert("b", 3);
        pq.insert("c", 8);
        pq.insert("d", 1);

        pq.changePriority("a", 0);
        assertWithMessage("After changing priority, peek should return the updated smallest item")
                .that(pq.peek()).isEqualTo("a");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertDuplicate() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("a", 5);
        pq.insert("a", 3);  // Should throw an exception
    }

    @Test
    public void testSize() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("a", 5);
        pq.insert("b", 3);

        assertWithMessage("Size should return the number of elements in the PQ")
                .that(pq.size()).isEqualTo(2);
    }

    @Test
    public void testContains() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("a", 5);
        pq.insert("b", 3);

        assertWithMessage("Contains should return true for an existing item")
                .that(pq.contains("a")).isTrue();
        assertWithMessage("Contains should return false for a non-existing item")
                .that(pq.contains("c")).isFalse();
    }

    @Test(expected = NoSuchElementException.class)
    public void testChangePriorityNonExistingElement() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.changePriority("a", 0);  // Should throw an exception
    }
}
