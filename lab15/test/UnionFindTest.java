import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;

public class UnionFindTest {

    @Test
    public void testUnionFind() {
        UnionFind uf = new UnionFind(5);

        // 初始情況下，所有元素都應該是獨立的
        for (int i = 0; i < 5; i++) {
            assertThat(uf.connected(i, i)).isTrue();
        }

        // 測試 sizeOf
        assertThat(uf.sizeOf(0)).isEqualTo(1);
        assertThat(uf.sizeOf(1)).isEqualTo(1);

        // 測試 union 和 connected
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

        // 測試 find 和 parent
        assertThat(uf.find(3)).isEqualTo(uf.find(0));
        assertThat(uf.parent(4)).isEqualTo(0); // 4 的父節點應該是 0
    }
}
