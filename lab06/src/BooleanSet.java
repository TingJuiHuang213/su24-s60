/**
 * Represent a set of nonnegative ints from 0 to maxElement (inclusive) for some initially
 * specified maxElement.
 */
public class BooleanSet implements SimpleSet {

    private boolean[] contains;
    private int size;

    /** Initializes a set of ints from 0 to maxElement. */
    public BooleanSet(int maxElement) {
        contains = new boolean[maxElement + 1];
        size = 0;
    }

    /**
     * Adds k to the set if it is not already present.
     *
     * @param k the element to add
     */
    @Override
    public void add(int k) {
        if (!contains[k]) {
            contains[k] = true;
            size++;
        }
    }

    /**
     * Removes k from the set if it is present.
     *
     * @param k the element to remove
     */
    @Override
    public void remove(int k) {
        if (contains[k]) {
            contains[k] = false;
            size--;
        }
    }

    /**
     * Returns true if k is in this set, false otherwise.
     *
     * @param k the element to check
     * @return true if k is present, false otherwise
     */
    @Override
    public boolean contains(int k) {
        return contains[k];
    }

    /**
     * Returns true if this set is empty, false otherwise.
     *
     * @return true if the set is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the set.
     *
     * @return the size of the set
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an array containing all of the elements in this collection.
     *
     * @return an array containing all elements in the set
     */
    @Override
    public int[] toIntArray() {
        int[] array = new int[size];
        int index = 0;
        for (int i = 0; i < contains.length; i++) {
            if (contains[i]) {
                array[index++] = i;
            }
        }
        return array;
    }
}
