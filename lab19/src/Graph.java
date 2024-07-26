import java.util.Set;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 表示一个可变的有限图对象。
 * 边的标签通过 HashMap 存储。
 * 图是无向的，顶点从 0 开始编号。
 */
public class Graph {

    private HashMap<Integer, Set<Integer>> neighbors = new HashMap<>(); // 邻接顶点
    private HashMap<Integer, Set<Edge>> edges = new HashMap<>(); // 邻接边
    private TreeSet<Edge> allEdges = new TreeSet<>(); // 所有边的集合

    /**
     * 返回顶点 V 的所有邻居顶点。
     */
    public TreeSet<Integer> getNeighbors(int v) {
        return new TreeSet<>(neighbors.get(v));
    }

    /**
     * 返回顶点 V 的所有邻接边。
     */
    public TreeSet<Edge> getEdges(int v) {
        return new TreeSet<>(edges.get(v));
    }

    /**
     * 返回所有顶点的有序列表。
     */
    public TreeSet<Integer> getAllVertices() {
        return new TreeSet<>(neighbors.keySet());
    }

    /**
     * 返回所有边的有序列表。
     */
    public TreeSet<Edge> getAllEdges() {
        return new TreeSet<>(allEdges);
    }

    /**
     * 将顶点 V 添加到图中。
     */
    public void addVertex(Integer v) {
        if (!neighbors.containsKey(v)) {
            neighbors.put(v, new HashSet<>());
            edges.put(v, new HashSet<>());
        }
    }

    /**
     * 将边 E 添加到图中。
     */
    public void addEdge(Edge e) {
        addEdgeHelper(e.getSource(), e.getDest(), e.getWeight());
    }

    /**
     * 创建一条无权重的边 V1-V2。
     */
    public void addEdge(int v1, int v2) {
        addEdgeHelper(v1, v2, 0);
    }

    /**
     * 创建一条有权重的边 V1-V2。
     */
    public void addEdge(int v1, int v2, int weight) {
        addEdgeHelper(v1, v2, weight);
    }

    /**
     * 返回 V1 和 V2 是否通过边相连。
     */
    public boolean isNeighbor(int v1, int v2) {
        return neighbors.get(v1).contains(v2) && neighbors.get(v2).contains(v1);
    }

    /**
     * 返回图中是否包含顶点 V。
     */
    public boolean containsVertex(int v) {
        return neighbors.containsKey(v);
    }

    /**
     * 返回图中是否包含边 E。
     */
    public boolean containsEdge(Edge e) {
        return allEdges.contains(e);
    }

    /**
     * 返回该图是否包含所有顶点的生成树。
     */
    public boolean spans(Graph g) {
        TreeSet<Integer> allVertices = getAllVertices();
        if (allVertices.size() != g.getAllVertices().size()) {
            return false;
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> verticesQueue = new ArrayDeque<>();
        verticesQueue.add(allVertices.first());
        while (!verticesQueue.isEmpty()) {
            Integer curr = verticesQueue.poll();
            if (!visited.contains(curr)) {
                visited.add(curr);
                for (int neighbor : getNeighbors(curr)) {
                    verticesQueue.add(neighbor);
                }
            }
        }
        return visited.size() == g.getAllVertices().size();
    }

    /**
     * 重写 equals 方法。
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Graph)) return false;
        Graph other = (Graph) o;
        return neighbors.equals(other.neighbors) && edges.equals(other.edges);
    }

    /**
     * 辅助方法：添加一条新边，从 V1 到 V2，带有权重 WEIGHT。
     */
    private void addEdgeHelper(int v1, int v2, int weight) {
        addVertex(v1);
        addVertex(v2);

        neighbors.get(v1).add(v2);
        neighbors.get(v2).add(v1);

        Edge e1 = new Edge(v1, v2, weight);
        Edge e2 = new Edge(v2, v1, weight);
        edges.get(v1).add(e1);
        edges.get(v2).add(e2);
        allEdges.add(e1);
    }

    /**
     * 实现 Prim 算法以找到最小生成树（MST）。
     */
    public Graph prims(int start) {
        Graph mst = new Graph();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Set<Integer> visited = new HashSet<>();

        visited.add(start);
        pq.addAll(edges.get(start));

        while (!pq.isEmpty() && mst.getAllEdges().size() < neighbors.size() - 1) {
            Edge edge = pq.poll();
            if (visited.contains(edge.getDest())) {
                continue;
            }
            mst.addEdge(edge);
            visited.add(edge.getDest());
            for (Edge nextEdge : edges.get(edge.getDest())) {
                if (!visited.contains(nextEdge.getDest())) {
                    pq.add(nextEdge);
                }
            }
        }

        return mst.getAllEdges().size() == neighbors.size() - 1 ? mst : null;
    }

    /**
     * 实现 Kruskal 算法以找到最小生成树（MST）。
     */
    public Graph kruskals() {
        Graph mst = new Graph();
        UnionFind uf = new UnionFind(neighbors.size());

        List<Edge> sortedEdges = new ArrayList<>(allEdges);
        Collections.sort(sortedEdges);

        for (Edge edge : sortedEdges) {
            int src = edge.getSource();
            int dest = edge.getDest();
            if (uf.find(src) != uf.find(dest)) {
                mst.addEdge(edge);
                uf.union(src, dest);
            }
            if (mst.getAllEdges().size() == neighbors.size() - 1) {
                break;
            }
        }

        return mst.getAllEdges().size() == neighbors.size() - 1 ? mst : null;
    }
}
