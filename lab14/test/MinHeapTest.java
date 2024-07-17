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
        heap.insertElement(10);
        heap.insertElement(4);
        heap.insertElement(15);
        heap.insertElement(1);

        assertEquals("FindMin should return the smallest element", (Integer) 1, heap.findMin());
    }

    @Test
    public void testRemoveMin() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insertElement(20);
        heap.insertElement(5);
        heap.insertElement(30);
        heap.insertElement(3);

        assertEquals("RemoveMin should return the smallest element", (Integer) 3, heap.removeMinElement());
        assertEquals("FindMin should now return the next smallest element", (Integer) 5, heap.findMin());
    }

    @Test
    public void testBubbleUp() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insertElement(50);
        heap.insertElement(25);
        heap.insertElement(75);

        assertEquals("FindMin should return the smallest element", (Integer) 25, heap.findMin());
    }

    @Test
    public void testBubbleDown() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insertElement(12);
        heap.insertElement(8);
        heap.insertElement(17);
        heap.insertElement(5);

        heap.removeMinElement();

        assertEquals("FindMin should now return the next smallest element", (Integer) 8, heap.findMin());
    }

    @Test
    public void testSize() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insertElement(9);
        heap.insertElement(3);

        assertEquals("Size should return the number of elements in the heap", 2, heap.getSize());
    }

    @Test
    public void testContains() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insertElement(18);
        heap.insertElement(14);

        assertTrue("Contains should return true for an existing element", heap.elementExists(14));
        assertFalse("Contains should return false for a non-existing element", heap.elementExists(20));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertDuplicate() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insertElement(6);
        heap.insertElement(6);  // 應該拋出異常
    }

    @Test(expected = NoSuchElementException.class)
    public void testUpdateNonExistingElement() {
        MinHeap<Integer> heap = new MinHeap<>();
        Integer oldElement = 10;
        Integer newElement = 20;
        heap.updateElement(oldElement, newElement);  // 應該拋出異常
    }

    @Test
    public void testUpdateExistingElement() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insertElement(7);
        heap.insertElement(2);
        heap.insertElement(9);
        heap.insertElement(4);

        // 更新已存在元素的值
        Integer oldElement = 2;
        Integer updatedElement = 1;

        // 使用正確的方法來更新元素
        heap.updateElement(oldElement, updatedElement);

        assertEquals("FindMin should now return the updated smallest element", (Integer) 1, heap.findMin());
    }
}
