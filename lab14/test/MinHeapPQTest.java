import org.junit.Test;
import java.util.NoSuchElementException;
import static org.junit.Assert.*;

/**
 * 測試 MinHeapPQ 的各種操作。
 */
public class MinHeapPQTest {

    @Test
    public void testInsertAndPeek() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("apple", 5);
        pq.insert("banana", 3);
        pq.insert("cherry", 8);
        pq.insert("date", 1);

        assertEquals("Peek should return the item with the smallest priority", "date", pq.peek());
    }

    @Test
    public void testPoll() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("alpha", 5);
        pq.insert("beta", 3);
        pq.insert("gamma", 8);
        pq.insert("delta", 1);

        assertEquals("Poll should return the item with the smallest priority", "delta", pq.poll());
        assertEquals("After polling, peek should return the next smallest item", "beta", pq.peek());
    }

    @Test
    public void testChangePriority() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("apple", 5);
        pq.insert("banana", 3);
        pq.insert("cherry", 8);
        pq.insert("date", 1);

        pq.changePriority("apple", 0);
        assertEquals("After changing priority, peek should return the updated smallest item", "apple", pq.peek());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertDuplicate() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("item1", 5);
        pq.insert("item1", 3);  // 應該拋出異常
    }

    @Test
    public void testSize() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("item1", 5);
        pq.insert("item2", 3);

        assertEquals("Size should return the number of elements in the PQ", 2, pq.size());
    }

    @Test
    public void testContains() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.insert("item1", 5);
        pq.insert("item2", 3);

        assertTrue("Contains should return true for an existing item", pq.contains("item1"));
        assertFalse("Contains should return false for a non-existing item", pq.contains("item3"));
    }

    @Test(expected = NoSuchElementException.class)
    public void testChangePriorityNonExistingElement() {
        MinHeapPQ<String> pq = new MinHeapPQ<>();
        pq.changePriority("nonExistentItem", 0);  // 應該拋出異常
    }
}
