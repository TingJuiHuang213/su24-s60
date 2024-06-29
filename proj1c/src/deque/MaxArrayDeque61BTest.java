package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;



public class MaxArrayDeque61BTest {

    @Test
    public void testMaxWithDefaultComparator() {
        Comparator<Integer> intComparator = Comparator.naturalOrder();
        MaxArrayDeque61B<Integer> maxDeque = new MaxArrayDeque61B<>(intComparator);

        maxDeque.addLast(1);
        maxDeque.addLast(3);
        maxDeque.addLast(2);

        assertEquals((Integer) 3, maxDeque.max());
    }

    @Test
    public void testMaxWithCustomComparator() {
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        MaxArrayDeque61B<Integer> maxDeque = new MaxArrayDeque61B<>(reverseComparator);

        maxDeque.addLast(1);
        maxDeque.addLast(3);
        maxDeque.addLast(2);

        assertEquals((Integer) 1, maxDeque.max());
    }

    @Test
    public void testMaxWithEmptyDeque() {
        Comparator<Integer> intComparator = Comparator.naturalOrder();
        MaxArrayDeque61B<Integer> maxDeque = new MaxArrayDeque61B<>(intComparator);

        assertNull(maxDeque.max());
    }
}
