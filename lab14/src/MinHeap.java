import java.util.ArrayList;
import java.util.NoSuchElementException;

/* A MinHeap class of Comparable elements backed by an ArrayList. */
public class MinHeap<E extends Comparable<E>> {

    /* An ArrayList that stores the elements in this MinHeap. */
    private ArrayList<E> contents;
    private int size;

    /* Initializes an empty MinHeap. */
    public MinHeap() {
        contents = new ArrayList<>();
        contents.add(null);
    }

    /* Returns the element at index INDEX, and null if it is out of bounds. */
    private E getElement(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    /* Sets the element at index INDEX to ELEMENT. If the ArrayList is not big
       enough, add elements until it is the right size. */
    private void setElement(int index, E element) {
        while (index >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, element);
    }

    /* Swaps the elements at the two indices. */
    private void swap(int index1, int index2) {
        E element1 = getElement(index1);
        E element2 = getElement(index2);
        setElement(index2, element1);
        setElement(index1, element2);
    }

    /* Prints out the underlying heap sideways. Use for debugging. */
    @Override
    public String toString() {
        return toStringHelper(1, "");
    }

    /* Recursive helper method for toString. */
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

    /* Returns the index of the left child of the element at index INDEX. */
    private int getLeftOf(int index) {
        return 2 * index;
    }

    /* Returns the index of the right child of the element at index INDEX. */
    private int getRightOf(int index) {
        return 2 * index + 1;
    }

    /* Returns the index of the parent of the element at index INDEX. */
    private int getParentOf(int index) {
        return index / 2;
    }

    /* Returns the index of the smaller element. At least one index has a
       non-null element. If the elements are equal, return either index. */
    private int min(int index1, int index2) {
        E element1 = getElement(index1);
        E element2 = getElement(index2);
        if (element1 == null) return index2;
        if (element2 == null) return index1;
        return element1.compareTo(element2) <= 0 ? index1 : index2;
    }

    /* Returns but does not remove the smallest element in the MinHeap. */
    public E findMin() {
        return getElement(1);
    }

    /* Bubbles up the element currently at index INDEX. */
    private void bubbleUp(int index) {
        while (index > 1 && getElement(index).compareTo(getElement(getParentOf(index))) < 0) {
            swap(index, getParentOf(index));
            index = getParentOf(index);
        }
    }

    /* Bubbles down the element currently at index INDEX. */
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

    /* Returns the number of elements in the MinHeap. */
    public int size() {
        return contents.size() - 1;
    }

    /* Inserts ELEMENT into the MinHeap. If ELEMENT is already in the MinHeap,
       throw an IllegalArgumentException. */
    public void insert(E element) {
        if (contains(element)) {
            throw new IllegalArgumentException("Element already exists in the heap.");
        }
        contents.add(element);
        bubbleUp(contents.size() - 1);
    }

    /* Returns and removes the smallest element in the MinHeap, or null if there are none. */
    public E removeMin() {
        if (size() == 0) {
            return null;
        }
        E min = findMin();
        swap(1, contents.size() - 1);
        contents.remove(contents.size() - 1);
        bubbleDown(1);
        return min;
    }

    /* Replaces and updates the position of ELEMENT inside the MinHeap, which
       may have been mutated since the initial insert. If a copy of ELEMENT does
       not exist in the MinHeap, throw a NoSuchElementException. Item equality
       should be checked using .equals(), not ==. */
    public void update(E oldElement, E newElement) {
        int index = -1;
        for (int i = 1; i < contents.size(); i++) {
            if (contents.get(i).equals(oldElement)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new NoSuchElementException();
        }
        contents.set(index, newElement);  // 使用新的優先級元素替換舊元素
        bubbleUp(index);
        bubbleDown(index);
    }



    /* Returns true if ELEMENT is contained in the MinHeap. Item equality should
       be checked using .equals(), not ==. */
    public boolean contains(E element) {
        for (int i = 1; i < contents.size(); i++) {
            if (contents.get(i).equals(element)) {
                return true;
            }
        }
        return false;
    }
}
