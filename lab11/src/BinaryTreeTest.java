import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class BinaryTreeTest {
    @Test
    public void sampleHeightTest() {
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

    @Test
    public void sampleIsCompletelyBalancedTest() {
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

    @Test
    public void fibTreeTest() {
        BinaryTree<Integer> fib0 = BinaryTree.fibTree(0);
        assertThat(fib0.getRoot().getItem()).isEqualTo(0);

        BinaryTree<Integer> fib1 = BinaryTree.fibTree(1);
        assertThat(fib1.getRoot().getItem()).isEqualTo(1);

        BinaryTree<Integer> fib2 = BinaryTree.fibTree(2);
        assertThat(fib2.getRoot().getItem()).isEqualTo(1);

        BinaryTree<Integer> fib3 = BinaryTree.fibTree(3);
        assertThat(fib3.getRoot().getItem()).isEqualTo(2);

        BinaryTree<Integer> fib4 = BinaryTree.fibTree(4);
        assertThat(fib4.getRoot().getItem()).isEqualTo(3);

        BinaryTree<Integer> fib5 = BinaryTree.fibTree(5);
        assertThat(fib5.getRoot().getItem()).isEqualTo(5);
    }
}
