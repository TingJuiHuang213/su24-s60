import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;

public class BinaryTreeTest {

    // 測試二元樹的高度計算
    @Test
    public void testHeight() {
        BinaryTree<String> emptyTree = new BinaryTree<>();
        assertThat(emptyTree.height()).isEqualTo(0);

        BinaryTree<String> sample1 = BinaryTree.sampleTree1();
        assertThat(sample1.height()).isEqualTo(2);

        BinaryTree<String> sample2 = BinaryTree.sampleTree2();
        assertThat(sample2.height()).isEqualTo(4);

        BinaryTree<String> sample3 = BinaryTree.sampleTree3();
        assertThat(sample3.height()).isEqualTo(4);

        BinaryTree<String> sample4 = BinaryTree.sampleTree4();
        assertThat(sample4.height()).isEqualTo(3);
    }

    // 測試二元樹的完全平衡性
    @Test
    public void testIsCompletelyBalanced() {
        BinaryTree<String> emptyTree = new BinaryTree<>();
        assertThat(emptyTree.isCompletelyBalanced()).isTrue();

        BinaryTree<String> sample1 = BinaryTree.sampleTree1();
        assertThat(sample1.isCompletelyBalanced()).isTrue();

        BinaryTree<String> sample2 = BinaryTree.sampleTree2();
        assertThat(sample2.isCompletelyBalanced()).isFalse();

        BinaryTree<String> sample3 = BinaryTree.sampleTree3();
        assertThat(sample3.isCompletelyBalanced()).isFalse();

        BinaryTree<String> sample4 = BinaryTree.sampleTree4();
        assertThat(sample4.isCompletelyBalanced()).isTrue();
    }

    // 測試 Fibonacci 樹的生成
    @Test
    public void testFibTree() {
        assertFibTree(0, 0);
        assertFibTree(1, 1);
        assertFibTree(2, 1);
        assertFibTree(3, 2);
        assertFibTree(4, 3);
        assertFibTree(5, 5);
    }

    // 辅助方法用于断言 Fibonacci 树的根节点值
    private void assertFibTree(int n, int expected) {
        BinaryTree<Integer> tree = BinaryTree.fibTree(n);
        assertThat(tree.getRoot().getItem()).isEqualTo(expected);
    }
}
