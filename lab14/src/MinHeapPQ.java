import java.util.NoSuchElementException;

/* A PriorityQueue class that uses a min heap to maintain ordering. */
public class MinHeapPQ<T> implements PriorityQueue<T> {

    /* The heap backing our MinHeapPQ. */
    private MinHeap<PriorityItem> heap;

    /* Initializes an empty MinHeapPQ. */
    public MinHeapPQ() {
        heap = new MinHeap<PriorityItem>();
    }

    /* Returns the item with the smallest priority value, but does not remove it
       from the MinHeapPQ. */
    public T peek() {
        return heap.getSize() == 0 ? null : heap.findMin().getItem();
    }

    /* Inserts ITEM with the priority value PRIORITYVALUE into the MinHeapPQ. If
       ITEM is already in the MinHeapPQ, throw an IllegalArgumentException. */
    public void insert(T item, double priorityValue) {
        if (itemExists(item)) {
            throw new IllegalArgumentException("Item already exists in the MinHeapPQ.");
        }
        PriorityItem newItem = new PriorityItem(item, priorityValue);
        heap.insertElement(newItem);
    }

    /* Returns the item with the highest priority (smallest priority value), and
       removes it from the MinHeapPQ. If there is nothing in the queue, return null.*/
    public T poll() {
        return heap.getSize() == 0 ? null : heap.removeMinElement().getItem();
    }

    /* Changes the PriorityItem with item ITEM to have priority value
       PRIORITYVALUE. Assume the items in the MinHeapPQ are all unique. If ITEM
       is not in the MinHeapPQ, throw a NoSuchElementException. */
    public void changePriority(T item, double priorityValue) {
        if (!itemExists(item)) {
            throw new NoSuchElementException("Item not found in the MinHeapPQ.");
        }
        PriorityItem oldItem = getItem(item);
        PriorityItem newItem = new PriorityItem(item, priorityValue);
        heap.updateElement(oldItem, newItem);
    }

    /* Returns the number of items in the MinHeapPQ. */
    public int size() {
        return heap.getSize();
    }

    /* Returns true if ITEM is stored in our MinHeapPQ. */
    public boolean contains(T item) {
        return itemExists(item);
    }

    private boolean itemExists(T item) {
        for (PriorityItem i : heap.getHeapContents()) {
            if (i != null && i.getItem().equals(item)) {
                return true;
            }
        }
        return false;
    }

    private PriorityItem getItem(T item) {
        for (PriorityItem i : heap.getHeapContents()) {
            if (i != null && i.getItem().equals(item)) {
                return i;
            }
        }
        throw new NoSuchElementException("Item not found in the MinHeapPQ.");
    }

    @Override
    public String toString() {
        return heap.toString();
    }

    /* A wrapper class that stores items and their associated priorities.
       Note: This class has a natural ordering that is inconsistent with equals. */
    public class PriorityItem implements Comparable<PriorityItem> {
        private T item;
        private double priorityValue;

        private PriorityItem(T item, double priorityValue) {
            this.item = item;
            this.priorityValue = priorityValue;
        }

        public T getItem() {
            return this.item;
        }

        public double getPriorityValue() {
            return this.priorityValue;
        }

        @Override
        public String toString() {
            return "(PriorityItem: " + this.item.toString() + ", " + this.priorityValue + ")";
        }

        @Override
        public int compareTo(PriorityItem other) {
            return Double.compare(this.priorityValue, other.priorityValue);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (other == null || getClass() != other.getClass()) return false;
            PriorityItem that = (PriorityItem) other;
            return item.equals(that.item);
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}
