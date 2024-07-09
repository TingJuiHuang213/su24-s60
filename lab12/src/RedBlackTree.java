public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /** Creates a RBTreeNode with item ITEM and color depending on ISBLACK value. */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /** Creates a RBTreeNode with item ITEM, color depending on ISBLACK value, left child LEFT, and right child RIGHT. */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left, RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

    /** Creates an empty RedBlackTree. */
    public RedBlackTree() {
        root = null;
    }

    /** Creates a RedBlackTree from a given 2-3 TREE. */
    public RedBlackTree(TwoThreeTree<T> tree) {
        Node<T> ttTreeRoot = tree.root;
        root = buildRedBlackTree(ttTreeRoot);
    }

    /** Builds a RedBlackTree that has isometry with given 2-3 tree rooted at given node R, and returns the root node. */
    RBTreeNode<T> buildRedBlackTree(Node<T> r) {
        if (r == null) {
            return null;
        }

        if (r.getItemCount() == 1) {
            // 處理2-node的情況
            T item = r.getItemAt(0);
            RBTreeNode<T> newNode = new RBTreeNode<>(true, item);
            newNode.left = buildRedBlackTree(r.getChildAt(0));
            newNode.right = buildRedBlackTree(r.getChildAt(1));
            return newNode;
        } else {
            // 處理3-node的情況
            T leftItem = r.getItemAt(0);
            T rightItem = r.getItemAt(1);
            RBTreeNode<T> leftNode = new RBTreeNode<>(false, leftItem);
            RBTreeNode<T> parentNode = new RBTreeNode<>(true, rightItem, leftNode, null);
            leftNode.left = buildRedBlackTree(r.getChildAt(0));
            leftNode.right = buildRedBlackTree(r.getChildAt(1));
            parentNode.right = buildRedBlackTree(r.getChildAt(2));
            return parentNode;
        }
    }

    /** Flips the color of NODE and its children. Assume that NODE has both left and right children. */
    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        if (node.left != null) {
            node.left.isBlack = !node.left.isBlack;
        }
        if (node.right != null) {
            node.right.isBlack = !node.right.isBlack;
        }
    }

    /** Rotates the given node NODE to the right. Returns the new root node of this subtree. */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        RBTreeNode<T> x = node.left;
        node.left = x.right;
        x.right = node;
        x.isBlack = node.isBlack;
        node.isBlack = false;
        return x;
    }

    /** Rotates the given node NODE to the left. Returns the new root node of this subtree. */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        RBTreeNode<T> x = node.right;
        node.right = x.left;
        x.left = node;
        x.isBlack = node.isBlack;
        node.isBlack = false;
        return x;
    }

    public void insert(T item) {
        root = insert(root, item);
        root.isBlack = true;
    }

    /** Inserts the given node into this Red Black Tree */
    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        if (node == null) {
            return new RBTreeNode<>(false, item);
        }

        int comp = item.compareTo(node.item);
        if (comp < 0) {
            node.left = insert(node.left, item);
        } else if (comp > 0) {
            node.right = insert(node.right, item);
        } else {
            return node;
        }

        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    /** Returns whether the given node NODE is red. Null nodes (children of leaf nodes are automatically considered black. */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

}
