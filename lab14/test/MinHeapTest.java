import org.junit.Test;
import static com.google.common.truth.Truth.assertWithMessage;
import java.util.NoSuchElementException;

public class MinHeapTest {

    @Test
    public void testInsertAndFindMin() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);

        assertWithMessage("FindMin should return the smallest element")
                .that(heap.findMin()).isEqualTo(1);
    }

    @Test
    public void testRemoveMin() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);

        assertWithMessage("RemoveMin should return the smallest element")
                .that(heap.removeMin()).isEqualTo(1);

        assertWithMessage("FindMin should now return the next smallest element")
                .that(heap.findMin()).isEqualTo(3);
    }

    @Test
    public void testBubbleUp() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);

        assertWithMessage("FindMin should return the smallest element")
                .that(heap.findMin()).isEqualTo(3);
    }

    @Test
    public void testBubbleDown() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);

        heap.removeMin();

        assertWithMessage("FindMin should now return the next smallest element")
                .that(heap.findMin()).isEqualTo(3);
    }

    @Test
    public void testSize() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(5);
        heap.insert(3);

        assertWithMessage("Size should return the number of elements in the heap")
                .that(heap.size()).isEqualTo(2);
    }

    @Test
    public void testContains() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(5);
        heap.insert(3);

        assertWithMessage("Contains should return true for an existing element")
                .that(heap.contains(3)).isTrue();

        assertWithMessage("Contains should return false for a non-existing element")
                .that(heap.contains(4)).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInsertDuplicate() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(5);
        heap.insert(5);  // Should throw an exception
    }

    @Test(expected = NoSuchElementException.class)
    public void testUpdateNonExistingElement() {
        MinHeap<Integer> heap = new MinHeap<>();
        Integer oldElement = 5;
        Integer newElement = 7;
        heap.update(oldElement, newElement);  // Should throw an exception
    }


    @Test
    public void testUpdateExistingElement() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);

        // 更新已存在元素的優先級，這裡假設新的元素是 2
        Integer oldElement = 3;
        Integer updatedElement = 2;

        // 使用正確的方法來更新元素
        heap.update(oldElement, updatedElement);

        assertWithMessage("FindMin should now return the updated smallest element")
                .that(heap.findMin()).isEqualTo(1);
    }
}
