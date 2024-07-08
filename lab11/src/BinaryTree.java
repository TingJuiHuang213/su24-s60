import java.util.ArrayList;

public class BinaryTree<T> {

    // 不要修改 TreeNode 類。
    static class TreeNode<T> {
        T item;
        TreeNode<T> left;
        TreeNode<T> right;

        public TreeNode(T item) {
            this.item = item;
            left = right = null;
        }

        public TreeNode(T item, TreeNode<T> left, TreeNode<T> right) {
            this.item = item;
            this.left = left;
            this.right = right;
        }

        public T getItem() {
            return item;
        }

        public TreeNode<T> getLeft() {
            return left;
        }

        public TreeNode<T> getRight() {
            return right;
        }
    }

    // protected 設定讓子類別可以存取這個實例變數，但其他類別不能。
    protected TreeNode<T> root;

    // 建構一個空的二元樹。
    public BinaryTree() {
        root = null;
    }

    // 建構一個具有給定根的二元樹。
    public BinaryTree(TreeNode<T> t) {
        root = t;
    }

    // 返回根節點。
    public TreeNode<T> getRoot() {
        return root;
    }

    // 選擇性構造函數，參見實驗中的選擇性練習（或上週的理論實驗）。
    public BinaryTree(ArrayList<T> pre, ArrayList<T> in) {
        // TODO: 可選的構造函數
    }

    /* 前序打印樹中的值。 */
    public void printPreorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            printPreorderHelper(root);
            System.out.println();
        }
    }

    private void printPreorderHelper(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        System.out.print(node.item + " ");
        printPreorderHelper(node.left);
        printPreorderHelper(node.right);
    }

    /* 中序打印樹中的值。 */
    public void printInorder() {
        if (root == null) {
            System.out.println("(empty tree)");
        } else {
            printInorderHelper(root);
            System.out.println();
        }
    }

    private void printInorderHelper(TreeNode<T> node) {
        if (node == null) {
            return;
        }
        printInorderHelper(node.left);
        System.out.print(node.item + " ");
        printInorderHelper(node.right);
    }

    /* 返回樹的高度。 */
    public int height() {
        return calculateHeight(root);
    }

    // 計算樹的高度，使用自定義邏輯和輔助方法
    private int calculateHeight(TreeNode<T> node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = calculateHeight(node.left);
            int rightHeight = calculateHeight(node.right);
            return 1 + (leftHeight > rightHeight ? leftHeight : rightHeight);
        }
    }

    // 判斷樹是否完全平衡
    public boolean isCompletelyBalanced() {
        return checkBalance(root);
    }

    // 檢查樹的平衡性，使用自定義邏輯和輔助方法
    private boolean checkBalance(TreeNode<T> node) {
        if (node == null) {
            return true;
        } else {
            int leftHeight = calculateHeight(node.left);
            int rightHeight = calculateHeight(node.right);
            return leftHeight == rightHeight && checkBalance(node.left) && checkBalance(node.right);
        }
    }

    // 創建 Fibonacci 樹
    public static BinaryTree<Integer> fibTree(int N) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.root = buildFibTree(N);
        return tree;
    }

    // 構建 Fibonacci 樹的輔助方法
    private static TreeNode<Integer> buildFibTree(int N) {
        if (N == 0) {
            return new TreeNode<>(0);
        } else if (N == 1) {
            return new TreeNode<>(1);
        } else {
            TreeNode<Integer> left = buildFibTree(N - 1);
            TreeNode<Integer> right = buildFibTree(N - 2);
            return new TreeNode<>(left.item + right.item, left, right);
        }
    }

    /* Fills this BinaryTree with values a, b, and c. DO NOT MODIFY. */
    public static BinaryTree<String> sampleTree1() {
        TreeNode<String> root = new TreeNode<>("a",
                new TreeNode<>("b"),
                new TreeNode<>("c"));
        return new BinaryTree<>(root);
    }

    /* Fills this BinaryTree with values a, b, and c, d, e, f. DO NOT MODIFY. */
    public static BinaryTree<String> sampleTree2() {
        TreeNode<String> root = new TreeNode<>("a",
                new TreeNode<>("b",
                        new TreeNode<>("d",
                                new TreeNode<>("e"),
                                new TreeNode<>("f")),
                        null),
                new TreeNode<>("c"));
        return new BinaryTree<>(root);
    }

    /* Fills this BinaryTree with the values a, b, c, d, e, f. DO NOT MODIFY. */
    public static BinaryTree<String> sampleTree3() {
        TreeNode<String> root = new TreeNode<>("a",
                new TreeNode<>("b"),
                new TreeNode<>("c",
                        new TreeNode<>("d",
                                new TreeNode<>("e"),
                                new TreeNode<>("f")),
                        null));
        return new BinaryTree<>(root);
    }

    /* Fills this BinaryTree with the same leaf TreeNode. DO NOT MODIFY. */
    public static BinaryTree<String> sampleTree4() {
        TreeNode<String> leafNode = new TreeNode<>("c");
        TreeNode<String> root = new TreeNode<>("a", new TreeNode<>("b", leafNode, leafNode),
                new TreeNode<>("d", leafNode, leafNode));
        return new BinaryTree<>(root);
    }

    /* Creates two BinaryTrees and prints them out in inorder. */
    public static void main(String[] args) {
        BinaryTree<String> t = new BinaryTree<>();
        print(t, "the empty tree");
        t = BinaryTree.sampleTree1();
        print(t, "sample tree 1");
        t = BinaryTree.sampleTree2();
        print(t, "sample tree 2");
        t = BinaryTree.sampleTree3();
        print(t, "sample tree 3");
        t = BinaryTree.sampleTree4();
        print(t, "sample tree 4");
    }

    /* Prints out the contents of a BinaryTree with a description in both
       preorder and inorder. */
    static void print(BinaryTree t, String description) {
        System.out.println(description + " in preorder");
        t.printPreorder();
        System.out.println(description + " in inorder");
        t.printInorder();
        System.out.println();
    }
}
