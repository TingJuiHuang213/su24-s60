package deque;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        Node newNode = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
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
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        size--;
        return first.item;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        size--;
        return last.item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursiveHelper(node.next, index - 1);
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

    // 添加 iterator() 方法
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
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
        };
    }

    // 添加 equals() 方法
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedListDeque61B)) return false;
        LinkedListDeque61B<?> that = (LinkedListDeque61B<?>) o;
        if (size != that.size) {
            return false;
        }
        Node current1 = this.sentinel.next;
        Node current2 = (Node) that.sentinel.next;
        while (current1 != sentinel) {
            if (!current1.item.equals(current2.item)) {
                return false;
            }
            current1 = current1.next;
            current2 = current2.next;
        }
        return true;
    }


    // 添加 toString() 方法
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = sentinel.next;
        while (current != sentinel) {
            sb.append(current.item);
            if (current.next != sentinel) {
                sb.append(" ");
            }
            current = current.next;
        }
        return sb.toString();
    }
}
