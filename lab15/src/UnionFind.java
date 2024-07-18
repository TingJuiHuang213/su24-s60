public class UnionFind {
    private int[] parent; // parent[i] holds the parent of i. If parent[i] = i, then i is a root node
    private int[] size;   // size[i] holds the size of the set that i is currently in

    /* Creates a UnionFind data structure holding N vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int N) {
        parent = new int[N];
        size = new int[N];

        for (int i = 0; i < N; i++) {
            parent[i] = i;  // Each vertex is in its own set, parent to itself
            size[i] = 1;    // Initially, all sets are of size one
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        return size[find(v)];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        if (parent[v] == v) {
            return -size[v];
        } else {
            return parent[v];
        }
    }

    /* Returns true if nodes V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int v) {
        if (v < 0 || v >= parent.length) {
            throw new IllegalArgumentException("Vertex " + v + " is not valid");
        }

        while (v != parent[v]) {
            parent[v] = find(parent[v]); // Update parent[v] to point directly to the root
            v = parent[v];
        }
        return v;
    }

    /* Connects two elements V1 and V2 together by connecting their respective
       sets. Union-by-size heuristic is used. */
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
                parent[root1] = root2;
                size[root2] += size[root1];
            }
        }
    }
}
