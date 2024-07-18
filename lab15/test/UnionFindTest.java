import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;

public class UnionFindTest {

    @Test
    public void testConstructor() {
        UnionFind uf = new UnionFind(5);

        // 检查 parent 数组是否初始化正确
        for (int i = 0; i < 5; i++) {
            assertThat(uf.parent(i)).isEqualTo(-1); // 每个元素初始时都是自己的父节点
        }
    }

    @Test
    public void testParent() {
        UnionFind uf = new UnionFind(5);

        // 检查 parent 方法是否正确返回初始状态下的父节点
        for (int i = 0; i < 5; i++) {
            assertThat(uf.parent(i)).isEqualTo(-1);
        }
    }

    @Test
    public void testSizeOf() {
        UnionFind uf = new UnionFind(5);

        // 初始时，每个元素所在集合的大小应为 1
        for (int i = 0; i < 5; i++) {
            assertThat(uf.sizeOf(i)).isEqualTo(1);
        }
    }

    @Test
    public void testConnected() {
        UnionFind uf = new UnionFind(5);

        // 初始时，所有元素不连接
        assertThat(uf.connected(0, 1)).isFalse();
        assertThat(uf.connected(1, 2)).isFalse();
        assertThat(uf.connected(3, 4)).isFalse();

        // 连接一些元素
        uf.union(0, 1);
        assertThat(uf.connected(0, 1)).isTrue();
        assertThat(uf.connected(1, 0)).isTrue();
        assertThat(uf.connected(0, 2)).isFalse();
        assertThat(uf.connected(3, 4)).isFalse();

        uf.union(1, 2);
        assertThat(uf.connected(0, 2)).isTrue();
        assertThat(uf.connected(1, 2)).isTrue();

        uf.union(3, 4);
        assertThat(uf.connected(3, 4)).isTrue();
        assertThat(uf.connected(0, 3)).isFalse();

        uf.union(0, 4);
        assertThat(uf.connected(0, 3)).isTrue();
        assertThat(uf.connected(2, 4)).isTrue();
    }

    @Test
    public void testFind() {
        UnionFind uf = new UnionFind(5);

        // 检查每个元素的初始根节点
        for (int i = 0; i < 5; i++) {
            assertThat(uf.find(i)).isEqualTo(i);
        }

        // 连接一些元素并检查根节点
        uf.union(0, 1);
        assertThat(uf.find(0)).isEqualTo(uf.find(1));

        uf.union(1, 2);
        assertThat(uf.find(0)).isEqualTo(uf.find(2));

        uf.union(3, 4);
        assertThat(uf.find(3)).isEqualTo(uf.find(4));

        uf.union(0, 4);
        assertThat(uf.find(0)).isEqualTo(uf.find(3));
    }

    @Test
    public void testUnion() {
        UnionFind uf = new UnionFind(5);

        // 检查初始状态下的每个元素的根节点
        for (int i = 0; i < 5; i++) {
            assertThat(uf.find(i)).isEqualTo(i);
        }

        // 合并一些元素并检查根节点和大小
        uf.union(0, 1);
        assertThat(uf.find(0)).isEqualTo(uf.find(1));
        assertThat(uf.sizeOf(0)).isEqualTo(2);
        assertThat(uf.sizeOf(1)).isEqualTo(2);

        uf.union(1, 2);
        assertThat(uf.find(0)).isEqualTo(uf.find(2));
        assertThat(uf.sizeOf(0)).isEqualTo(3);
        assertThat(uf.sizeOf(1)).isEqualTo(3);
        assertThat(uf.sizeOf(2)).isEqualTo(3);

        uf.union(3, 4);
        assertThat(uf.find(3)).isEqualTo(uf.find(4));
        assertThat(uf.sizeOf(3)).isEqualTo(2);
        assertThat(uf.sizeOf(4)).isEqualTo(2);

        uf.union(0, 4);
        assertThat(uf.find(0)).isEqualTo(uf.find(3));
        assertThat(uf.sizeOf(0)).isEqualTo(5);
        assertThat(uf.sizeOf(1)).isEqualTo(5);
        assertThat(uf.sizeOf(2)).isEqualTo(5);
        assertThat(uf.sizeOf(3)).isEqualTo(5);
        assertThat(uf.sizeOf(4)).isEqualTo(5);
    }
}
