public class UnionFind {
    private int[] parent;
    private int[] size;

    public UnionFind(int N) {
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int sizeOf(int v) {
        int root = find(v);
        return size[root];
    }

    public int parent(int v) {
        if (parent[v] == v) {
            return -size[v];
        } else {
            return parent[v];
        }
    }

    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    public int find(int v) {
        if (v < 0 || v >= parent.length) {
            throw new IllegalArgumentException("Invalid index");
        }
        if (parent[v] != v) {
            parent[v] = find(parent[v]); // Path compression
        }
        return parent[v];
    }

    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);

        if (root1 != root2) {
            if (size[root1] < size[root2]) {
                parent[root1] = root2;
                size[root2] += size[root1];
            } else if (size[root1] > size[root2]) {
                parent[root2] = root1;
                size[root1] += size[root2];
            } else {
                parent[root2] = root1;
                size[root1] += size[root2];
            }
        }
    }
}
