import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class BinaryTreeTest {
    @Test
    public void sampleHeightTest() {
        BinaryTree<String> emptyTree = new BinaryTree<>();
        assertThat(emptyTree.height()).isEqualTo(0);

        BinaryTree<String> sample1 = BinaryTree.sampleTree1();
        assertThat(sample1.height()).isEqualTo(2);
    }

    // TODO: Add more tests below.
}