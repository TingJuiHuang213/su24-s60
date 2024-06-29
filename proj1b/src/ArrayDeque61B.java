import java.util.ArrayList;
import java.util.List;

/**
 * 使用環形數組作為底層數據結構實現 Deque 介面。
 * @param <T> 此雙端隊列中持有的元素類型
 */
public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int front;
    private int rear;
    private static final int INITIAL_CAPACITY = 8;

    /**
     * 構造一個初始容量為 8 的空雙端隊列。
     */
    @SuppressWarnings("unchecked")
    public ArrayDeque61B() {
        items = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
        front = 0;
        rear = 0;
    }

    /**
     * 將一個類型為 T 的項目添加到雙端隊列的前面。
     * @param item 要添加到前面的項目
     */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        front = (front - 1 + items.length) % items.length;
        items[front] = item;
        size++;
    }

    /**
     * 將一個類型為 T 的項目添加到雙端隊列的後面。
     * @param item 要添加到後面的項目
     */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[rear] = item;
        rear = (rear + 1) % items.length;
        size++;
    }

    /**
     * 檢查雙端隊列是否為空。
     * @return 如果雙端隊列為空，則返回 true，否則返回 false
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 返回雙端隊列中的項目數。
     * @return 雙端隊列中的項目數
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * 將雙端隊列轉換為列表。
     * @return 包含雙端隊列中所有元素的列表，順序保持不變
     */
    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(items[(front + i) % items.length]);
        }
        return list;
    }

    /**
     * 獲取雙端隊列中給定索引處的項目。
     * @param index 要返回的項目的索引
     * @return 給定索引處的項目，如果索引超出範圍則返回 null
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(front + index) % items.length];
    }

    /**
     * 遞歸獲取雙端隊列中給定索引處的項目。
     * @param index 要返回的項目的索引
     * @return 給定索引處的項目，如果索引超出範圍則返回 null
     */
    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(front, index);
    }

    /**
     * 遞歸輔助方法，用於獲取雙端隊列中給定索引處的項目。
     * @param currentIndex 遞歸中的當前索引
     * @param index 要返回的項目的索引
     * @return 給定索引處的項目
     */
    private T getRecursiveHelper(int currentIndex, int index) {
        if (index == 0) {
            return items[currentIndex % items.length];
        }
        return getRecursiveHelper((currentIndex + 1) % items.length, index - 1);
    }

    /**
     * 移除並返回雙端隊列前面的項目。
     * @return 從前面移除的項目，如果雙端隊列為空則返回 null
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T item = items[front];
        items[front] = null; // 避免懈怠
        front = (front + 1) % items.length;
        size--;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    /**
     * 移除並返回雙端隊列後面的項目。
     * @return 從後面移除的項目，如果雙端隊列為空則返回 null
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        rear = (rear - 1 + items.length) % items.length;
        T item = items[rear];
        items[rear] = null; // 避免懈怠
        size--;
        if (size > 0 && size == items.length / 4) {
            resize(items.length / 2);
        }
        return item;
    }

    /**
     * 將雙端隊列的大小調整為給定的容量。
     * @param capacity 新的容量
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
}
