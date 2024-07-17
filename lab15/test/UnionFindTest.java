import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;

public class UnionFindTest {

    @Test
    public void testInitialState() {
        UnionFind uf = new UnionFind(5);

        // 初始情況下，所有元素都是獨立的
        for (int i = 0; i < 5; i++) {
            assertThat(uf.connected(i, i)).isTrue();
            assertThat(uf.sizeOf(i)).isEqualTo(1);
            assertThat(uf.parent(i)).isEqualTo(i);
        }
    }

    @Test
    public void testUnionAndConnected() {
        UnionFind uf = new UnionFind(5);

        uf.union(0, 1);
        assertThat(uf.connected(0, 1)).isTrue();
        assertThat(uf.sizeOf(0)).isEqualTo(2);
        assertThat(uf.sizeOf(1)).isEqualTo(2);

        uf.union(1, 2);
        assertThat(uf.connected(0, 2)).isTrue();
        assertThat(uf.sizeOf(0)).isEqualTo(3);
        assertThat(uf.sizeOf(2)).isEqualTo(3);

        uf.union(3, 4);
        assertThat(uf.connected(3, 4)).isTrue();
        assertThat(uf.sizeOf(3)).isEqualTo(2);
        assertThat(uf.sizeOf(4)).isEqualTo(2);

        uf.union(0, 4);
        assertThat(uf.connected(0, 4)).isTrue();
        assertThat(uf.connected(1, 3)).isTrue();
        assertThat(uf.sizeOf(0)).isEqualTo(5);
        assertThat(uf.sizeOf(4)).isEqualTo(5);
    }

    @Test
    public void testFindAndPathCompression() {
        UnionFind uf = new UnionFind(8);

        // Initial unions
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(0, 2);
        uf.union(5, 7);
        uf.union(7, 3);

        // Test find results
        assertThat(uf.find(0)).isEqualTo(uf.find(3));
        assertThat(uf.find(0)).isEqualTo(uf.find(7));
        assertThat(uf.find(1)).isEqualTo(uf.find(2));
        assertThat(uf.find(4)).isEqualTo(uf.find(6));
        assertThat(uf.find(5)).isEqualTo(uf.find(7));

        // Test sizeOf and parent
        assertThat(uf.sizeOf(0)).isEqualTo(8);
        assertThat(uf.sizeOf(1)).isEqualTo(8);
        assertThat(uf.sizeOf(4)).isEqualTo(8);
    }

    @Test
    public void testInvalidInputs() {
        UnionFind uf = new UnionFind(5);

        // Test invalid find
        try {
            uf.find(-1);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageThat().contains("Invalid index");
        }

        try {
            uf.find(5);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageThat().contains("Invalid index");
        }

        // Test invalid union
        try {
            uf.union(-1, 2);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageThat().contains("Invalid index");
        }

        try {
            uf.union(2, 5);
        } catch (IllegalArgumentException e) {
            assertThat(e).hasMessageThat().contains("Invalid index");
        }
    }
}
