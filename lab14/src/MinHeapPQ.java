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
        return (heap.size() == 0) ? null : heap.findMin().item();
    }

    /* Inserts ITEM with the priority value PRIORITYVALUE into the MinHeapPQ. If
       ITEM is already in the MinHeapPQ, throw an IllegalArgumentException. */
    public void insert(T item, double priorityValue) {
        if (contains(item)) {
            throw new IllegalArgumentException("Item already exists in the MinHeapPQ.");
        }
        PriorityItem newItem = new PriorityItem(item, priorityValue);
        heap.insert(newItem);
    }

    /* Returns the item with the highest priority (smallest priority value), and
       removes it from the MinHeapPQ. If there is nothing in the queue, return null.*/
    public T poll() {
        if (heap.size() == 0) {
            return null;
        }
        return heap.removeMin().item();
    }

    /* Changes the PriorityItem with item ITEM to have priority value
       PRIORITYVALUE. Assume the items in the MinHeapPQ are all unique. If ITEM
       is not in the MinHeapPQ, throw a NoSuchElementException. */
    public void changePriority(T item, double priorityValue) {
        if (!contains(item)) {
            throw new NoSuchElementException("Item not found in the MinHeapPQ.");
        }
        PriorityItem oldItem = findItem(item);
        PriorityItem newItem = new PriorityItem(item, priorityValue);
        heap.update(oldItem, newItem);
    }

    /* Returns the number of items in the MinHeapPQ. */
    public int size() {
        return heap.size();
    }

    /* Returns true if ITEM is stored in our MinHeapPQ. */
    public boolean contains(T item) {
        return heapContainsItem(item);
    }

    private boolean heapContainsItem(T item) {
        for (PriorityItem i : heap.getContents()) {
            if (i != null && i.item.equals(item)) {
                return true;
            }
        }
        return false;
    }

    private PriorityItem findItem(T item) {
        for (PriorityItem i : heap.getContents()) {
            if (i != null && i.item.equals(item)) {
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

       Note: This class has a natural ordering that is inconsistent with
       equals. */
    public class PriorityItem implements Comparable<PriorityItem> {
        private T item;
        private double priorityValue;

        private PriorityItem(T item, double priorityValue) {
            this.item = item;
            this.priorityValue = priorityValue;
        }

        public T item() {
            return this.item;
        }

        public double priorityValue() {
            return this.priorityValue;
        }

        @Override
        public String toString() {
            return "(PriorityItem: " + this.item.toString() + ", "
                    + this.priorityValue + ")";
        }

        @Override
        public int compareTo(PriorityItem o) {
            return Double.compare(this.priorityValue, o.priorityValue);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            PriorityItem that = (PriorityItem) o;
            return item.equals(that.item);
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}