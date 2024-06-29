package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import deque.Deque61B;


public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int front;
    private int rear;
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
        front = (front - 1 + items.length) % items.length;
        items[front] = item;
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[rear] = item;
        rear = (rear + 1) % items.length;
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

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    private T getRecursiveHelper(int currentIndex, int index) {
        if (index == 0) {
            return items[currentIndex % items.length];
        }
        return getRecursiveHelper((currentIndex + 1) % items.length, index - 1);
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = items[front];
        items[front] = null; // avoid loitering
        front = (front + 1) % items.length;
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
        rear = (rear - 1 + items.length) % items.length;
        T item = items[rear];
        items[rear] = null; // avoid loitering
        size--;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

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
}
