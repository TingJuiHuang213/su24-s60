/** A data structure to represent a Linked List of Integers.
 * Each IntList represents one node in the overall Linked List.
 */

public class IntList {

    /** The integer stored by this node. */
    public int item;
    /** The next node in this IntList. */
    public IntList next;

    /** Constructs an IntList storing ITEM and next node NEXT. */
    public IntList(int item, IntList next) {
        this.item = item;
        this.next = next;
    }

    /** Constructs an IntList storing ITEM and no next node. */
    public IntList(int item) {
        this(item, null);
    }

    /** Returns an IntList consisting of the elements in ITEMS.
     * IntList L = IntList.list(1, 2, 3);
     * System.out.println(L.toString()) // Prints 1 2 3 */
    public static IntList of(int... items) {
        if (items.length == 0) {
            return null;
        }
        IntList head = new IntList(items[0]);
        IntList current = head;
        for (int i = 1; i < items.length; i++) {
            current.next = new IntList(items[i]);
            current = current.next;
        }
        return head;
    }

    /**
     * Returns the item at the specified position in this list. Throws IllegalArgumentException
     * if the position is out of bounds.
     *
     * @param position the position of the element to return
     * @return the element at the specified position
     */
    public int get(int position) {
        if (position < 0) {
            throw new IllegalArgumentException("Position must be non-negative");
        }
        IntList current = this;
        for (int i = 0; i < position; i++) {
            if (current.next == null) {
                throw new IllegalArgumentException("Position is out of bounds");
            }
            current = current.next;
        }
        return current.item;
    }

    /**
     * Returns the string representation of the list. For the list (1, 2, 3),
     * returns "1 2 3".
     *
     * @return the string representation of the list
     */
    @Override
    public String toString() {
        return String.join(" ", toStringHelper(this, new StringBuilder())).trim();
    }

    private String toStringHelper(IntList list, StringBuilder sb) {
        if (list == null) {
            return sb.toString();
        }
        sb.append(list.item).append(" ");
        return toStringHelper(list.next, sb);
    }

    /**
     * Compares this list to the specified object. The result is true if and only if
     * the argument is not null and is an IntList object that contains the same integers
     * in the same order as this list.
     *
     * @param obj the object to compare this IntList against
     * @return true if the given object represents an IntList equivalent to this list, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IntList other = (IntList) obj;
        return equalsHelper(this, other);
    }

    private boolean equalsHelper(IntList a, IntList b) {
        if (a == null && b == null) {
            return true;
        }
        if (a == null || b == null || a.item != b.item) {
            return false;
        }
        return equalsHelper(a.next, b.next);
    }

    /**
     * Appends the specified value to the end of this list.
     *
     * @param value the value to be appended to this list
     */
    public void add(int value) {
        if (this.next == null) {
            this.next = new IntList(value);
        } else {
            this.next.add(value);
        }
    }

    /**
     * Returns the smallest element in this list.
     *
     * @return the smallest element in this list
     */
    public int smallest() {
        if (this == null) {
            throw new IllegalArgumentException("Cannot find smallest element of an empty list");
        }
        return smallestHelper(this, this.item);
    }

    private int smallestHelper(IntList list, int min) {
        if (list == null) {
            return min;
        }
        if (list.item < min) {
            min = list.item;
        }
        return smallestHelper(list.next, min);
    }

    /**
     * Returns the sum of the squares of all elements in this list.
     *
     * @return the sum of the squares of all elements in this list
     */
    public int squaredSum() {
        return squaredSumHelper(this, 0);
    }

    private int squaredSumHelper(IntList list, int sum) {
        if (list == null) {
            return sum;
        }
        return squaredSumHelper(list.next, sum + list.item * list.item);
    }

    /**
     * Destructively squares each item of the list.
     *
     * @param L the list to destructively square
     */
    public static void dSquareList(IntList L) {
        if (L == null) {
            return;
        }
        L.item *= L.item;
        dSquareList(L.next);
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     *
     * @param L the list to non-destructively square
     * @return a new list with all elements squared
     */
    public static IntList squareListIterative(IntList L) {
        if (L == null) {
            return null;
        }
        IntList result = new IntList(L.item * L.item, null);
        IntList current = result;
        for (IntList p = L.next; p != null; p = p.next) {
            current.next = new IntList(p.item * p.item, null);
            current = current.next;
        }
        return result;
    }

    /**
     * Returns a list equal to L with all elements squared. Non-destructive.
     *
     * @param L the list to non-destructively square
     * @return a new list with all elements squared
     */
    public static IntList squareListRecursive(IntList L) {
        if (L == null) {
            return null;
        }
        return new IntList(L.item * L.item, squareListRecursive(L.next));
    }

    /**
     * Returns a new IntList consisting of A followed by B, non-destructively.
     *
     * @param A the list to be at the front of the new list
     * @param B the list to be at the back of the new list
     * @return a new list with A followed by B
     */
    public static IntList catenate(IntList A, IntList B) {
        if (A == null) return B;
        IntList result = new IntList(A.item, catenate(A.next, B));
        return result;
    }

    /**
     * Returns a new IntList consisting of A followed by B, destructively.
     *
     * @param A the list to be at the front of the new list
     * @param B the list to be at the back of the new list
     * @return a new list with A followed by B
     */
    public static IntList dcatenate(IntList A, IntList B) {
        if (A == null) return B;
        IntList current = A;
        while (current.next != null) {
            current = current.next;
        }
        current.next = B;
        return A;
    }

    // Additional helper methods for more unique functionality

    /**
     * Reverses the list non-destructively.
     *
     * @return a new list with elements in reverse order
     */
    public IntList reverse() {
        IntList result = null;
        IntList current = this;
        while (current != null) {
            result = new IntList(current.item, result);
            current = current.next;
        }
        return result;
    }

    /**
     * Calculates the product of all elements in the list.
     *
     * @return the product of all elements in the list
     */
    public int product() {
        return productHelper(this, 1);
    }

    private int productHelper(IntList list, int product) {
        if (list == null) {
            return product;
        }
        return productHelper(list.next, product * list.item);
    }
}
