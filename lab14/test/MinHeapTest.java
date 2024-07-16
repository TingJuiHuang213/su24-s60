import org.junit.Test;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;

/**
 * 測試 MinHeap 的各種操作。
 */
public class MinHeapTest {

    @Test
    public void testInsertAndFindMin() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(10);
        heap.insert(4);
        heap.insert(15);
        heap.insert(1);

        assertEquals("FindMin should return the smallest element", (Integer) 1, heap.findMin());
    }

    @Test
    public void testRemoveMin() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(20);
        heap.insert(5);
        heap.insert(30);
        heap.insert(3);

        assertEquals("RemoveMin should return the smallest element", (Integer) 3, heap.removeMin());
        assertEquals("FindMin should now return the next smallest element", (Integer) 5, heap.findMin());
    }

    @Test
    public void testBubbleUp() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(50);
        heap.insert(25);
        heap.insert(75);

        assertEquals("FindMin should return the smallest element", (Integer) 25, heap.findMin());
    }

    @Test
    public void testBubbleDown() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(12);
        heap.insert(8);
        heap.insert(17);
        heap.insert(5);

        heap.removeMin();

        assertEquals("FindMin should now return the next smallest element", (Integer) 8, heap.findMin());
    }

    @Test
    public void testSize() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(9);
        heap.insert(3);

        assertEquals("Size should return the number of elements in the heap", 2, heap.size());
    }

    @Test
    public void testContains() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(18);
        heap.insert(14);

        assertTrue("Contains should return true for an existing element", heap.contains(14));
        assertFalse("Contains should return false for a non-existing element", heap.contains(20));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertDuplicate() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(6);
        heap.insert(6);  // 應該拋出異常
    }

    @Test(expected = NoSuchElementException.class)
    public void testUpdateNonExistingElement() {
        MinHeap<Integer> heap = new MinHeap<>();
        Integer oldElement = 10;
        Integer newElement = 20;
        heap.update(oldElement, newElement);  // 應該拋出異常
    }

    @Test
    public void testUpdateExistingElement() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(7);
        heap.insert(2);
        heap.insert(9);
        heap.insert(4);

        // 更新已存在元素的值
        Integer oldElement = 2;
        Integer updatedElement = 1;

        // 使用正確的方法來更新元素
        heap.update(oldElement, updatedElement);

        assertEquals("FindMin should now return the updated smallest element", (Integer) 1, heap.findMin());
    }
}
