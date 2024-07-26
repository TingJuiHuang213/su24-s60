/**
 * 并查集（Union-Find）数据结构，用于管理连通性。
 */
public class UnionFind {
    private int[] parent; // 父节点数组
    private int[] rank; // 秩数组

    /**
     * 构造方法，初始化并查集。
     */
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * 查找元素 p 所在的集合的根。
     */
    public int find(int p) {
        if (parent[p] != p) {
            parent[p] = find(parent[p]); // 路径压缩
        }
        return parent[p];
    }

    /**
     * 合并 p 和 q 所在的集合。
     */
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            // 按秩合并
            if (rank[rootP] > rank[rootQ]) {
                parent[rootQ] = rootP;
            } else if (rank[rootP] < rank[rootQ]) {
                parent[rootP] = rootQ;
            } else {
                parent[rootQ] = rootP;
                rank[rootP]++;
            }
        }
    }
}
