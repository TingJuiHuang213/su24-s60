import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An AList is a list of integers. Like SLList, it also hides the terrible
 * truth of the nakedness within, but uses an array as its base.
 */
public class AList<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    /** Creates an empty AList. */
    public AList() {
        items = createNewArray(8);
        size = 0;
    }

    /** Creates a new array with the given capacity */
    private Item[] createNewArray(int capacity) {
        // The line below gives a warning (Unchecked cast), but you can ignore this.
        return (Item[]) new Object[capacity];
    }

    /** Returns a AList consisting of the given values. */
    public static <Item> AList<Item> of(Item... values) {
        AList<Item> list = new AList<>();
        for (Item value : values) {
            list.addLast(value);
        }
        return list;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Adds item to the back of the list. */
    public void addLast(Item item) {
        ensureCapacity();
        items[size] = item;
        size += 1;
    }

    /** Ensure the array has enough capacity */
    private void ensureCapacity() {
        if (items.length == size) {
            resize();
        }
    }

    /** Resize the array to accommodate more items. */
    private void resize() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    /** Returns the representation of the AList as a String. */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size; i++) {
            result.append(items[i].toString()).append(" ");
        }
        return result.toString().trim();
    }

    /** Returns whether this and the given list or object are equal. */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AList<?> other = (AList<?>) o;
        return size == other.size && Arrays.deepEquals(items, other.items);
    }

    @Override
    public Iterator<Item> iterator() {
        return new AListIterator();
    }

    private class AListIterator implements Iterator<Item> {
        private int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return items[pos++];
        }
    }
}
