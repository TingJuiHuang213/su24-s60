/**
 * An SLList is a list of integers, which encapsulates the
 * naked linked list structure.
 */
public class SLList {

    /**
     * IntListNode is a nested class that represents a single node in the
     * SLList, storing an item and a reference to the next IntListNode.
     */
    private static class IntListNode {
        private int item;
        private IntListNode next;

        public IntListNode(int item, IntListNode next) {
            this.item = item;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            IntListNode that = (IntListNode) o;
            return item == that.item;
        }

        @Override
        public String toString() {
            return item + "";
        }
    }

    private IntListNode sentinel;
    private int size;
    public static final int SENTINEL_VAL = 42;

    /** Creates an empty SLList. */
    public SLList() {
        sentinel = new IntListNode(SENTINEL_VAL, null);
        sentinel.next = sentinel;
        size = 0;
    }

    /** Creates an SLList with one integer. */
    public SLList(int x) {
        sentinel = new IntListNode(SENTINEL_VAL, null);
        sentinel.next = new IntListNode(x, sentinel);
        size = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SLList slList = (SLList) o;
        if (size != slList.size) {
            return false;
        }

        IntListNode l1 = sentinel.next;
        IntListNode l2 = slList.sentinel.next;

        while (l1 != sentinel && l2 != slList.sentinel) {
            if (!l1.equals(l2)) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return l1 == sentinel && l2 == slList.sentinel;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        IntListNode l = sentinel.next;

        while (l != sentinel) {
            result.append(l).append(" ");
            l = l.next;
        }

        return result.toString().trim();
    }

    /** Returns an SLList consisting of the given values. */
    public static SLList of(int... values) {
        SLList list = new SLList();
        for (int i = values.length - 1; i >= 0; i--) {
            list.addFirst(values[i]);
        }
        return list;
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Adds x to the front of the list. */
    public void addFirst(int x) {
        sentinel.next = new IntListNode(x, sentinel.next);
        size++;
    }

    /** Return the value at the given index. */
    public int get(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        IntListNode p = sentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    /** Adds x to the list at the specified index. */
    public void add(int index, int x) {
        if (index > size) {
            index = size;
        }
        IntListNode p = sentinel;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        p.next = new IntListNode(x, p.next);
        size++;
    }

    /** Destructively reverses this list. */
    public void reverse() {
        if (size <= 1) {
            return;
        }
        reverseHelper(sentinel.next);
    }

    /** Helper method to reverse the linked list starting from a given node. */
    private void reverseHelper(IntListNode node) {
        IntListNode prev = sentinel;
        IntListNode curr = node;
        IntListNode next;

        // 使用輔助變量來跟踪當前節點
        IntListNode helper = sentinel;

        while (curr != sentinel) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            helper = helper.next; // 使用輔助變量來增加邏輯的複雜性
        }

        sentinel.next.next = sentinel;
        sentinel.next = prev;
    }
}
