import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class MinHeap<E extends Comparable<E>> {

    private ArrayList<E> contents;
    private HashMap<E, Integer> indexMap;
    private int size;

    public MinHeap() {
        contents = new ArrayList<>();
        contents.add(null); // 占位符，便於計算子節點索引
        indexMap = new HashMap<>();
        size = 0;
    }

    // 獲取元素
    private E getElement(int index) {
        return (index < contents.size()) ? contents.get(index) : null;
    }

    // 設置元素
    private void setElement(int index, E element) {
        while (index >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, element);
        indexMap.put(element, index);
    }

    // 交換兩個索引處的元素
    private void swapElements(int index1, int index2) {
        E temp = contents.get(index1);
        contents.set(index1, contents.get(index2));
        contents.set(index2, temp);
        indexMap.put(contents.get(index1), index1);
        indexMap.put(contents.get(index2), index2);
    }

    @Override
    public String toString() {
        return buildHeapString(1, "");
    }

    private String buildHeapString(int index, String prefix) {
        if (getElement(index) == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int right = getRightChild(index);
        sb.append(buildHeapString(right, "        " + prefix));
        if (getElement(right) != null) {
            sb.append(prefix).append("    /");
        }
        sb.append("\n").append(prefix).append(getElement(index)).append("\n");
        int left = getLeftChild(index);
        if (getElement(left) != null) {
            sb.append(prefix).append("    \\");
        }
        sb.append(buildHeapString(left, "        " + prefix));
        return sb.toString();
    }

    private int getLeftChild(int index) {
        return 2 * index;
    }

    private int getRightChild(int index) {
        return 2 * index + 1;
    }

    private int getParent(int index) {
        return index / 2;
    }

    private int findSmallerChild(int index1, int index2) {
        E elem1 = getElement(index1);
        E elem2 = getElement(index2);
        if (elem1 == null) return index2;
        if (elem2 == null) return index1;
        return elem1.compareTo(elem2) <= 0 ? index1 : index2;
    }

    public E findMin() {
        return getElement(1);
    }

    // 上浮操作
    private void bubbleUp(int index) {
        while (index > 1 && getElement(index).compareTo(getElement(getParent(index))) < 0) {
            swapElements(index, getParent(index));
            index = getParent(index);
        }
    }

    // 下沉操作
    private void bubbleDown(int index) {
        while (getLeftChild(index) < contents.size()) {
            int left = getLeftChild(index);
            int right = getRightChild(index);
            int smallest = findSmallerChild(left, right);
            if (getElement(index).compareTo(getElement(smallest)) <= 0) {
                break;
            }
            swapElements(index, smallest);
            index = smallest;
        }
    }

    public int getSize() {
        return size;
    }

    public void insertElement(E element) {
        if (elementExists(element)) {
            throw new IllegalArgumentException("Element already exists in the heap.");
        }
        contents.add(element);
        int index = contents.size() - 1;
        indexMap.put(element, index);
        size++;
        bubbleUp(index);
    }

    public E removeMinElement() {
        if (size == 0) {
            return null;
        }
        E minElement = findMin();
        swapElements(1, contents.size() - 1);
        contents.remove(contents.size() - 1);
        indexMap.remove(minElement);
        size--;
        bubbleDown(1);
        return minElement;
    }

    public void updateElement(E oldElement, E newElement) {
        if (!elementExists(oldElement)) {
            throw new NoSuchElementException();
        }
        int index = indexMap.get(oldElement);
        contents.set(index, newElement);
        indexMap.remove(oldElement);
        indexMap.put(newElement, index);
        bubbleUp(index);
        bubbleDown(index);
    }

<<<<<<< HEAD
    public boolean elementExists(E element) {
        return indexMap.containsKey(element);
    }

    // 添加新方法，用於返回 contents
    protected ArrayList<E> getHeapContents() {
        return contents;
=======
    /* Returns true if ELEMENT is contained in the MinHeap. Item equality should
       be checked using .equals(), not ==. */
    public boolean contains(E element) {
        // OPTIONAL: OPTIMIZE THE SPEED OF THIS TO MAKE IT CONSTANT
        for (int i = 1; i < contents.size(); i++) {
            if (element.equals(contents.get(i))) {
                return true;
            }
        }
        return false;
>>>>>>> 12b0ea70bf119e42d2c85f563bcef37d7de6de27
    }
}
