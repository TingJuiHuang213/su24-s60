public class UnionFind {

    /* Instance variables */
    private int[] parent;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        if (N < 0) {
            throw new IllegalArgumentException("Number of elements must be non-negative");
        }
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = -1; // 每个元素初始时都是自己的父节点
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        int root = find(v);
        return -parent[root]; // 取负值表示集合大小
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        if (v < 0 || v >= parent.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        return parent[v];
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v < 0 || v >= parent.length) {
            throw new IllegalArgumentException("Index out of bounds");
        }
        if (parent[v] < 0) {
            return v;
        }
        parent[v] = find(parent[v]); // 路径压缩
        return parent[v];
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing a item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        int rootP = find(v1);
        int rootQ = find(v2);

        if (rootP != rootQ) {
            int sizeP = -parent[rootP];
            int sizeQ = -parent[rootQ];

            if (sizeP < sizeQ) {
                parent[rootP] = rootQ;
                parent[rootQ] -= sizeP;
            } else {
                parent[rootQ] = rootP;
                parent[rootP] -= sizeQ;
            }
        }
    }
}
