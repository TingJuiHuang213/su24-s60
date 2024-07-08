public class BinarySearchTree<T extends Comparable<T>> extends BinaryTree<T> {

    public BinarySearchTree() {
        super();
    }

    public BinarySearchTree(TreeNode<T> root) {
        super(root);
    }

    // 判斷 BST 中是否包含給定的鍵
    public boolean contains(T key) {
        return searchForKey(root, key);
    }

    // 搜索鍵的輔助方法，使用自定義邏輯
    private boolean searchForKey(TreeNode<T> node, T key) {
        if (node == null) {
            return false;
        } else if (key.compareTo(node.item) < 0) {
            return searchForKey(node.left, key);
        } else if (key.compareTo(node.item) > 0) {
            return searchForKey(node.right, key);
        } else {
            return true;
        }
    }

    // 添加鍵到 BST 中
    public void add(T key) {
        root = insertKey(root, key);
    }

    // 插入鍵的輔助方法，使用自定義邏輯
    private TreeNode<T> insertKey(TreeNode<T> node, T key) {
        if (node == null) {
            return new TreeNode<>(key);
        } else if (key.compareTo(node.item) < 0) {
            node.left = insertKey(node.left, key);
        } else if (key.compareTo(node.item) > 0) {
            node.right = insertKey(node.right, key);
        }
        return node;
    }

    // 刪除方法已經提供，不需要修改
    public T delete(T key) {
        TreeNode<T> parent = null;
        TreeNode<T> curr = root;
        TreeNode<T> delNode = null;
        TreeNode<T> replacement = null;
        boolean rightSide = false;

        while (curr != null && !curr.item.equals(key)) {
            if (curr.item.compareTo(key) > 0) {
                parent = curr;
                curr = curr.left;
                rightSide = false;
            } else {
                parent = curr;
                curr = curr.right;
                rightSide = true;
            }
        }
        delNode = curr;
        if (curr == null) {
            return null;
        }

        if (delNode.right == null) {
            if (root == delNode) {
                root = root.left;
            } else {
                if (rightSide) {
                    parent.right = delNode.left;
                } else {
                    parent.left = delNode.left;
                }
            }
        } else {
            curr = delNode.right;
            replacement = curr.left;
            if (replacement == null) {
                replacement = curr;
            } else {
                while (replacement.left != null) {
                    curr = replacement;
                    replacement = replacement.left;
                }
                curr.left = replacement.right;
                replacement.right = delNode.right;
            }
            replacement.left = delNode.left;
            if (root == delNode) {
                root = replacement;
            } else {
                if (rightSide) {
                    parent.right = replacement;
                } else {
                    parent.left = replacement;
                }
            }
        }
        return delNode.item;
    }
}
