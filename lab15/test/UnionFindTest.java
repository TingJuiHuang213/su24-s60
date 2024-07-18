import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

public class UnionFindTest {

    @Test
    public void initialStateTest() {
        UnionFind uf = new UnionFind(4);
        assertEquals(1, uf.sizeOf(0));
        assertEquals(1, uf.sizeOf(1));
        assertEquals(1, uf.sizeOf(2));
        assertEquals(1, uf.sizeOf(3));
        assertFalse(uf.connected(0, 1));
        assertFalse(uf.connected(1, 2));
        assertFalse(uf.connected(1, 3));
        assertFalse(uf.connected(2, 3));
    }

    @Test
    public void illegalFindTest() {
        UnionFind uf = new UnionFind(4);
        try {
            uf.find(10); // 使用 find 方法
            fail("不能查找範圍外的頂點！");
        } catch (IllegalArgumentException e) {
            // 預期行為
        }
        try {
            uf.union(1, 10); // 修改為無效的合併
            fail("不能合併範圍外的頂點！");
        } catch (IllegalArgumentException e) {
            // 預期行為
        }
    }

    @Test
    public void basicUnionTest() {
        UnionFind uf = new UnionFind(10);
        uf.union(0, 1);
        assertEquals(1, uf.find(0)); // 使用 find 方法
        uf.union(2, 3);
        assertEquals(3, uf.find(2)); // 使用 find 方法
        uf.union(0, 2);
        assertEquals(3, uf.find(1)); // 使用 find 方法

        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(8, 9);
        uf.union(4, 8);
        uf.union(4, 6);

        assertEquals(9, uf.find(5)); // 使用 find 方法
        assertEquals(9, uf.find(7)); // 使用 find 方法
        assertEquals(9, uf.find(8)); // 使用 find 方法

        uf.union(9, 2);
        assertEquals(9, uf.find(3)); // 使用 find 方法
    }

    @Test
    public void sameUnionTest() {
        UnionFind uf = new UnionFind(4);
        uf.union(1, 1);
        for (int i = 0; i < 4; i++) {
            assertEquals(i, uf.find(i)); // 使用 find 方法
        }
    }

    // 在此處添加更多自定義測試以檢查路徑壓縮和所有方法的正確性
}
