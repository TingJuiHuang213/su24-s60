package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A generic array-based deque implementation.
 * @param <T> the type of elements in this deque
 */
public class ArrayDeque61B<T> implements Deque61B<T>, Iterable<T> {
    private T[] items;       // The array to store elements
    private int size;        // Current number of elements
    private int front;       // Index of the front element
    private int rear;        // Index of the rear element
    private static final int INITIAL_CAPACITY = 8;

    @SuppressWarnings("unchecked")
    public ArrayDeque61B() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
        rear = 0;
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        front = decrementIndex(front);
        items[front] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[rear] = item;
        rear = incrementIndex(rear);
        size++;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(items[(front + i) % items.length]);
        }
        return list;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(front + index) % items.length];
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(front, index);
    }

    private T getRecursiveHelper(int currentIndex, int index) {
        if (index == 0) {
            return items[currentIndex % items.length];
        }
        return getRecursiveHelper(incrementIndex(currentIndex), index - 1);
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = items[front];
        items[front] = null; // avoid loitering
        front = incrementIndex(front);
        size--;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        rear = decrementIndex(rear);
        T item = items[rear];
        items[rear] = null; // avoid loitering
        size--;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int pos = 0;

            @Override
            public boolean hasNext() {
                return pos < size;
            }

            @Override
            public T next() {
                T item = items[(front + pos) % items.length];
                pos++;
                return item;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque61B)) {
            return false;
        }
        Deque61B<?> that = (Deque61B<?>) o;
        if (size != that.size()) {
            return false;
        }
        Iterator<T> iter1 = this.iterator();
        Iterator<?> iter2 = that.iterator();
        while (iter1.hasNext() && iter2.hasNext()) {
            if (!iter1.next().equals(iter2.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(items[(front + i) % items.length]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Resize the underlying array to the target capacity.
     * @param capacity the new capacity of the array
     */
    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[i] = items[(front + i) % items.length];
        }
        items = newArray;
        front = 0;
        rear = size;
    }

    /**
     * Increment an index, wrapping around the array if necessary.
     * @param index the index to increment
     * @return the incremented index
     */
    private int incrementIndex(int index) {
        return (index + 1) % items.length;
    }

    /**
     * Decrement an index, wrapping around the array if necessary.
     * @param index the index to decrement
     * @return the decremented index
     */
    private int decrementIndex(int index) {
        return (index - 1 + items.length) % items.length;
    }
}
