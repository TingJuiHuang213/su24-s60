package deque;

import java.util.Comparator;

/**
 * 带有最大值功能的基于数组的通用双端队列实现。
 * @param <T> 双端队列中元素的类型
 */
public class MaxArrayDeque61B<T> extends ArrayDeque61B<T> {
    private final Comparator<T> defaultComparator;

    /**
     * 使用给定的比较器创建一个 MaxArrayDeque。
     * @param c 用于确定最大元素的比较器
     */
    public MaxArrayDeque61B(Comparator<T> c) {
        defaultComparator = c;
    }

    /**
     * 根据默认比较器返回双端队列中的最大元素。
     * @return 最大元素，如果双端队列为空则返回 null
     */
    public T max() {
        return max(defaultComparator);
    }

    /**
     * 根据给定的比较器返回双端队列中的最大元素。
     * @param c 用于确定最大元素的比较器
     * @return 最大元素，如果双端队列为空则返回 null
     */
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = get(0);
        for (int i = 1; i < size(); i++) {
            T currentItem = get(i);
            if (c.compare(currentItem, maxItem) > 0) {
                maxItem = currentItem;
            }
        }
        return maxItem;
    }
}
