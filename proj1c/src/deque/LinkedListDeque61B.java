package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T>, Iterable<T> {
    private class Node {
        T item;
        Node next;
        Node prev;

        Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        addBetween(item, sentinel, sentinel.next);
    }

    @Override
    public void addLast(T item) {
        addBetween(item, sentinel.prev, sentinel);
    }

    private void addBetween(T item, Node predecessor, Node successor) {
        Node newNode = new Node(item, successor, predecessor);
        predecessor.next = newNode;
        successor.prev = newNode;
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
        Node current = sentinel.next;
        while (current != sentinel) {
            list.add(current.item);
            current = current.next;
        }
        return list;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node node = getNode(index);
        return node.item;
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = sentinel.next;
        if (index < size / 2) {
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = sentinel.prev;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index).item;
    }

    private Node getRecursiveHelper(Node current, int index) {
        if (index == 0) {
            return current;
        }
        return getRecursiveHelper(current.next, index - 1);
    }

    @Override
    public T removeFirst() {
        return removeNode(sentinel.next);
    }

    @Override
    public T removeLast() {
        return removeNode(sentinel.prev);
    }

    private T removeNode(Node node) {
        if (node == sentinel) {
            return null;
        }
        Node predecessor = node.prev;
        Node successor = node.next;
        predecessor.next = successor;
        successor.prev = predecessor;
        size--;
        return node.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node current = sentinel.next;

        @Override
        public boolean hasNext() {
            return current != sentinel;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
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
        Node current = sentinel.next;
        while (current != sentinel) {
            sb.append(current.item);
            if (current.next != sentinel) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
