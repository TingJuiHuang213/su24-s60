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
        // 新增結點到鏈表的開頭
        Node newNode = new Node(item, sentinel.next, sentinel);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {
        // 新增結點到鏈表的尾部
        Node newNode = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public boolean isEmpty() {
        // 檢查雙端隊列是否為空
        return size == 0;
    }

    @Override
    public int size() {
        // 返回雙端隊列的大小
        return size;
    }

    @Override
    public List<T> toList() {
        // 將雙端隊列轉換為列表
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
        // 獲取指定索引處的元素
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
        // 遞歸獲取指定索引處的元素
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node current, int index) {
        // 輔助方法：遞歸獲取指定索引處的元素
        if (index == 0) {
            return current.item;
        }
        return getRecursiveHelper(current.next, index - 1);
    }

    @Override
    public T removeFirst() {
        // 移除並返回雙端隊列的第一個元素
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
        // 移除並返回雙端隊列的最後一個元素
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
    public Iterator<T> iterator() {
        // 返回一個遍歷雙端隊列的迭代器
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

    @Override
    public boolean equals(Object o) {
        // 檢查雙端隊列是否相等
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
        // 返回雙端隊列的字符串表示
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
