public class UnionFind {
    private int[] parentArray; // 每個元素的父節點
    private int[] setSize;     // 每個集合的大小

    public UnionFind(int totalVertices) {
        parentArray = new int[totalVertices];
        setSize = new int[totalVertices];

        for (int i = 0; i < totalVertices; i++) {
            parentArray[i] = i;
            setSize[i] = 1;
        }
    }

    public int sizeOf(int v) {
        return setSize[find(v)];
    }

    public int parent(int v) {
        if (parentArray[v] == v) {
            return -setSize[v];
        } else {
            return parentArray[v];
        }
    }

    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    public int find(int v) {
        if (v < 0 || v >= parentArray.length) {
            throw new IllegalArgumentException("頂點 " + v + " 無效");
        }

        if (v != parentArray[v]) {
            parentArray[v] = find(parentArray[v]); // 完整的路徑壓縮
        }
        return parentArray[v];
    }

    public void union(int v1, int v2) {
        int root1 = find(v1);
        int root2 = find(v2);

        if (root1 != root2) {
            if (setSize[root1] < setSize[root2]) {
                parentArray[root1] = root2;
                setSize[root2] += setSize[root1];
                System.out.println("Union " + v1 + " and " + v2 + ": root1 -> " + root2); // 調試信息
            } else if (setSize[root1] > setSize[root2]) {
                parentArray[root2] = root1;
                setSize[root1] += setSize[root2];
                System.out.println("Union " + v1 + " and " + v2 + ": root2 -> " + root1); // 調試信息
            } else { // 當大小相等時，選擇將 root2 作為根
                parentArray[root1] = root2;
                setSize[root2] += setSize[root1];
                System.out.println("Union " + v1 + " and " + v2 + ": root1 -> " + root2 + " (equal size)"); // 調試信息
            }
        }
    }
}
