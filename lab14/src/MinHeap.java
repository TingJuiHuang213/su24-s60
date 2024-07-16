import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class MinHeap<E extends Comparable<E>> {

    private ArrayList<E> contents;
    private HashMap<E, Integer> indexMap;
    private int size;

    public MinHeap() {
        contents = new ArrayList<>();
        contents.add(null);
        indexMap = new HashMap<>();
        size = 0;
    }

    private E getElement(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    private void setElement(int index, E element) {
        while (index >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, element);
        indexMap.put(element, index);
    }

    private void swap(int index1, int index2) {
        E element1 = getElement(index1);
        E element2 = getElement(index2);
        setElement(index2, element1);
        setElement(index1, element2);
    }

    @Override
    public String toString() {
        return toStringHelper(1, "");
    }

    private String toStringHelper(int index, String soFar) {
        if (getElement(index) == null) {
            return "";
        } else {
            String toReturn = "";
            int rightChild = getRightOf(index);
            toReturn += toStringHelper(rightChild, "        " + soFar);
            if (getElement(rightChild) != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getElement(index) + "\n";
            int leftChild = getLeftOf(index);
            if (getElement(leftChild) != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper(leftChild, "        " + soFar);
            return toReturn;
        }
    }

    private int getLeftOf(int index) {
        return 2 * index;
    }

    private int getRightOf(int index) {
        return 2 * index + 1;
    }

    private int getParentOf(int index) {
        return index / 2;
    }

    private int min(int index1, int index2) {
        E element1 = getElement(index1);
        E element2 = getElement(index2);
        if (element1 == null) return index2;
        if (element2 == null) return index1;
        return element1.compareTo(element2) <= 0 ? index1 : index2;
    }

    public E findMin() {
        return getElement(1);
    }

    private void bubbleUp(int index) {
        while (index > 1 && getElement(index).compareTo(getElement(getParentOf(index))) < 0) {
            swap(index, getParentOf(index));
            index = getParentOf(index);
        }
    }

    private void bubbleDown(int index) {
        while (getLeftOf(index) < contents.size()) {
            int left = getLeftOf(index);
            int right = getRightOf(index);
            int smallest = left;
            if (right < contents.size() && getElement(right).compareTo(getElement(left)) < 0) {
                smallest = right;
            }
            if (getElement(index).compareTo(getElement(smallest)) <= 0) {
                break;
            }
            swap(index, smallest);
            index = smallest;
        }
    }

    public int size() {
        return size;
    }

    public void insert(E element) {
        if (contains(element)) {
            throw new IllegalArgumentException("Element already exists in the heap.");
        }
        contents.add(element);
        int index = contents.size() - 1;
        indexMap.put(element, index);
        size++;
        bubbleUp(index);
    }

    public E removeMin() {
        if (size == 0) {
            return null;
        }
        E min = findMin();
        swap(1, contents.size() - 1);
        contents.remove(contents.size() - 1);
        indexMap.remove(min);
        size--;
        bubbleDown(1);
        return min;
    }

    public void update(E oldElement, E newElement) {
        if (!contains(oldElement)) {
            throw new NoSuchElementException();
        }
        int index = indexMap.get(oldElement);
        contents.set(index, newElement);
        indexMap.remove(oldElement);
        indexMap.put(newElement, index);
        bubbleUp(index);
        bubbleDown(index);
    }

    public boolean contains(E element) {
        return indexMap.containsKey(element);
    }

    // 添加新方法，用於返回 contents
    protected ArrayList<E> getContents() {
        return contents;
    }
}