import java.util.ArrayList;
import java.util.List;

/**
 * Represents a set of ints. A simple implementation of a set using a list.
 */
public class ListSet implements SimpleSet {

    List<Integer> elems;

    public ListSet() {
        elems = new ArrayList<Integer>();
    }

    /**
     * Adds k to the set if it is not already present.
     * @param k the element to add
     */
    @Override
    public void add(int k) {
        if (!contains(k)) {
            elems.add(k);
        }
    }

    /**
     * Removes k from the set if it is present.
     * @param k the element to remove
     */
    @Override
    public void remove(int k) {
        Integer toRemove = k;
        elems.remove(toRemove);
    }

    /**
     * Returns true if k is in this set, false otherwise.
     * @param k the element to check
     * @return true if k is present, false otherwise
     */
    @Override
    public boolean contains(int k) {
        return elems.contains(k);
    }

    /**
     * Returns true if this set is empty, false otherwise.
     * @return true if the set is empty, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return elems.isEmpty();
    }

    /**
     * Returns the number of items in the set.
     * @return the size of the set
     */
    @Override
    public int size() {
        return elems.size();
    }

    /**
     * Returns an array containing all of the elements in this collection.
     * @return an array containing all elements in the set
     */
    @Override
    public int[] toIntArray() {
        int[] array = new int[elems.size()];
        for (int i = 0; i < elems.size(); i++) {
            array[i] = elems.get(i);
        }
        return array;
    }
}
