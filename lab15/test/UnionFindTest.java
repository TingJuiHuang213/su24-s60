import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;

public class UnionFindTest {

    @Test
    public void testInitialState() {
        UnionFind uf = new UnionFind(5);

        for (int i = 0; i < 5; i++) {
            assertThat(uf.connected(i, i)).isTrue();
            assertThat(uf.sizeOf(i)).isEqualTo(1);
            assertThat(uf.parent(i)).isEqualTo(-1);
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

        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(0, 2);
        uf.union(5, 7);
        uf.union(7, 3);

        assertThat(uf.find(0)).isEqualTo(uf.find(3));
        assertThat(uf.find(0)).isEqualTo(uf.find(7));
        assertThat(uf.find(1)).isEqualTo(uf.find(2));
        assertThat(uf.find(4)).isEqualTo(uf.find(6));
        assertThat(uf.find(5)).isEqualTo(uf.find(7));

        assertThat(uf.sizeOf(0)).isEqualTo(8);
        assertThat(uf.sizeOf(1)).isEqualTo(8);
        assertThat(uf.sizeOf(4)).isEqualTo(8);
    }

    @Test
    public void testInvalidInputs() {
        UnionFind uf = new UnionFind(5);

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

    @Test
    public void testCompleteUnion() {
        UnionFind uf = new UnionFind(10);
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(3, 4);
        uf.union(4, 5);
        uf.union(5, 6);
        uf.union(6, 7);
        uf.union(7, 8);
        uf.union(8, 9);
        assertThat(uf.connected(0, 9)).isTrue();
        assertThat(uf.sizeOf(0)).isEqualTo(10);
        assertThat(uf.find(9)).isEqualTo(uf.find(0));
    }

    @Test
    public void testBasicUnion() {
        UnionFind uf = new UnionFind(10);
        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(8, 9);
        uf.union(0, 2);
        uf.union(4, 6);
        uf.union(0, 4);
        uf.union(8, 0);
        assertThat(uf.connected(0, 9)).isTrue();
        assertThat(uf.sizeOf(0)).isEqualTo(10);
        assertThat(uf.find(9)).isEqualTo(uf.find(0));
    }
}
