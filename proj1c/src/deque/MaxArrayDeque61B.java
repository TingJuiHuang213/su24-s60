package deque;

import java.util.Comparator;

/**
 * A generic array-based deque implementation with max functionality.
 * @param <T> the type of elements in this deque
 */
public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private Comparator<T> comparator;

    /**
     * Creates a MaxArrayDeque with the given Comparator.
     * @param c the Comparator to determine the maximum element
     */
    public MaxArrayDeque61B(Comparator<T> c) {
        comparator = c;
    }

    /**
     * Returns the maximum element in the deque according to the default Comparator.
     * @return the maximum element, or null if the deque is empty
     */
    public T max() {
        return max(comparator);
    }

    /**
     * Returns the maximum element in the deque according to the given Comparator.
     * @param c the Comparator to determine the maximum element
     * @return the maximum element, or null if the deque is empty
     */
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = get(0);
        for (int i = 1; i < size(); i++) {
            T currentItem = get(i);
            if (c.compare(currentItem, maxItem) > 0) {
                maxItem = currentItem;
            }
        }
        return maxItem;
    }
}
