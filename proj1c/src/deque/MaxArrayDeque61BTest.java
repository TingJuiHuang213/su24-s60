package deque;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;

/**
 * Unit tests for the MaxArrayDeque61B class.
 */
public class MaxArrayDeque61BTest {

    /**
     * Tests max() method with the default natural order comparator.
     */
    @Test
    public void testMaxWithDefaultComparator() {
        Comparator<Integer> intComparator = Comparator.naturalOrder();
        MaxArrayDeque61B<Integer> maxDeque = new MaxArrayDeque61B<>(intComparator);

        maxDeque.addLast(1);
        maxDeque.addLast(3);
        maxDeque.addLast(2);

        Integer expected = 3;
        Integer actual = maxDeque.max();

        assertEquals("The max element should be 3.", expected, actual);
    }

    /**
     * Tests max() method with a custom reverse order comparator.
     */
    @Test
    public void testMaxWithCustomComparator() {
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        MaxArrayDeque61B<Integer> maxDeque = new MaxArrayDeque61B<>(reverseComparator);

        maxDeque.addLast(1);
        maxDeque.addLast(3);
        maxDeque.addLast(2);

        Integer expected = 1;
        Integer actual = maxDeque.max();

        assertEquals("The max element should be 1 with reverse order comparator.", expected, actual);
    }

    /**
     * Tests max() method on an empty deque.
     */
    @Test
    public void testMaxWithEmptyDeque() {
        Comparator<Integer> intComparator = Comparator.naturalOrder();
        MaxArrayDeque61B<Integer> maxDeque = new MaxArrayDeque61B<>(intComparator);

        assertNull("The max element should be null for an empty deque.", maxDeque.max());
    }
}
