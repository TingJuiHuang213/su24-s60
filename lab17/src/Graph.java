import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Stack;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Collections;

public class Graph implements Iterable<Integer> {

    // 使用 LinkedList 存儲邊的鄰接表
    private LinkedList<Edge>[] adjLists;
    private int vertexCount;

    /* 初始化圖，具有 numVertices 個頂點，無邊 */
    public Graph(int numVertices) {
        adjLists = (LinkedList<Edge>[]) new LinkedList[numVertices];
        for (int k = 0; k < numVertices; k++) {
            adjLists[k] = new LinkedList<Edge>();
        }
        vertexCount = numVertices;
    }

    /* 添加有向邊 (v1, v2) */
    public void addEdge(int v1, int v2) {
        addEdge(v1, v2, 0);
    }

    /* 添加無向邊 (v1, v2) */
    public void addUndirectedEdge(int v1, int v2) {
        addUndirectedEdge(v1, v2, 0);
    }

    /* 添加有向邊 (v1, v2) 和權重 */
    public void addEdge(int v1, int v2, int weight) {
        removeExistingEdge(v1, v2); // 移除現有邊
        adjLists[v1].add(new Edge(v1, v2, weight)); // 添加新邊
    }

    /* 移除現有邊的方法 */
    private void removeExistingEdge(int v1, int v2) {
        adjLists[v1].removeIf(edge -> edge.to == v2);
    }

    /* 添加無向邊 (v1, v2) 和權重 */
    public void addUndirectedEdge(int v1, int v2, int weight) {
        addEdge(v1, v2, weight);
        addEdge(v2, v1, weight);
    }

    /* 判斷頂點 FROM 到頂點 TO 是否有邊 */
    public boolean isAdjacent(int from, int to) {
        return adjLists[from].stream().anyMatch(edge -> edge.to == to);
    }

    /* 返回所有與頂點 v 相鄰的頂點列表 */
    public List<Integer> neighbors(int v) {
        List<Integer> result = new ArrayList<>();
        adjLists[v].forEach(edge -> result.add(edge.to));
        return result;
    }

    /* 返回頂點 v 的入度 */
    public int inDegree(int v) {
        int inDegree = 0;
        for (LinkedList<Edge> edges : adjLists) {
            inDegree += edges.stream().filter(edge -> edge.to == v).count();
        }
        return inDegree;
    }

    /* 返回圖的拓撲排序 */
    public Iterator<Integer> iterator() {
        return new TopologicalIterator();
    }

    /* DFS 遍歷的內部類 */
    private class DFSIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private HashSet<Integer> visited;

        public DFSIterator(Integer start) {
            fringe = new Stack<>();
            visited = new HashSet<>();
            fringe.push(start);
        }

        public boolean hasNext() {
            return fringe.stream().anyMatch(v -> !visited.contains(v));
        }

        public Integer next() {
            while (!fringe.isEmpty()) {
                int curr = fringe.pop();
                if (visited.contains(curr)) continue;
                visited.add(curr);
                addNeighborsToFringe(curr);
                return curr;
            }
            return null;
        }

        private void addNeighborsToFringe(int v) {
            neighbors(v).forEach(fringe::push);
        }

        public void remove() {
            throw new UnsupportedOperationException("vertex removal not implemented");
        }
    }

    /* 返回從 v 開始的 DFS 遍歷結果 */
    public List<Integer> dfs(int v) {
        List<Integer> result = new ArrayList<>();
        DFSIterator iter = new DFSIterator(v);

        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    /* 判斷從 START 到 STOP 是否存在路徑 */
    public boolean pathExists(int start, int stop) {
        return findPath(start, stop).size() > 0;
    }

    /* 返回從 START 到 STOP 的路徑 */
    public List<Integer> path(int start, int stop) {
        List<Integer> path = findPath(start, stop);
        if (path.size() > 1 && path.get(path.size() - 1) == stop) {
            return path;
        }
        return new ArrayList<>();
    }

    /* 查找從 START 到 STOP 的路徑的輔助方法 */
    private List<Integer> findPath(int start, int stop) {
        Stack<Integer> fringe = new Stack<>();
        HashSet<Integer> visited = new HashSet<>();
        HashMap<Integer, Integer> edgeTo = new HashMap<>();
        List<Integer> path = new ArrayList<>();

        fringe.push(start);
        visited.add(start);

        while (!fringe.isEmpty()) {
            int v = fringe.pop();
            if (v == stop) {
                reconstructPath(path, edgeTo, start, stop);
                return path;
            }
            for (int neighbor : neighbors(v)) {
                if (!visited.contains(neighbor)) {
                    fringe.push(neighbor);
                    visited.add(neighbor);
                    edgeTo.put(neighbor, v);
                }
            }
        }
        return path;
    }

    /* 重構路徑的輔助方法 */
    private void reconstructPath(List<Integer> path, HashMap<Integer, Integer> edgeTo, int start, int stop) {
        for (int x = stop; x != start; x = edgeTo.get(x)) {
            path.add(x);
        }
        path.add(start);
        Collections.reverse(path);
    }

    /* 返回圖的拓撲排序 */
    public List<Integer> topologicalSort() {
        List<Integer> result = new ArrayList<>();
        TopologicalIterator iter = new TopologicalIterator();
        while (iter.hasNext()) {
            result.add(iter.next());
        }
        return result;
    }

    private class TopologicalIterator implements Iterator<Integer> {

        private Stack<Integer> fringe;
        private int[] inDegrees;

        TopologicalIterator() {
            fringe = new Stack<>();
            inDegrees = new int[vertexCount];
            initializeInDegreesAndFringe();
        }

        private void initializeInDegreesAndFringe() {
            for (int v = 0; v < vertexCount; v++) {
                inDegrees[v] = inDegree(v);
                if (inDegrees[v] == 0) {
                    fringe.push(v);
                }
            }
        }

        public boolean hasNext() {
            return !fringe.isEmpty();
        }

        public Integer next() {
            int v = fringe.pop();
            for (int neighbor : neighbors(v)) {
                inDegrees[neighbor]--;
                if (inDegrees[neighbor] == 0) {
                    fringe.push(neighbor);
                }
            }
            return v;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private class Edge {

        private int from;
        private int to;
        private int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public String toString() {
            return "(" + from + ", " + to + ", weight = " + weight + ")";
        }
    }

    private void generateG1() {
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(0, 4);
        addEdge(1, 2);
        addEdge(2, 0);
        addEdge(2, 3);
        addEdge(4, 3);
    }

    private void generateG2() {
        addEdge(0, 1);
        addEdge(0, 2);
        addEdge(0, 4);
        addEdge(1, 2);
        addEdge(2, 3);
        addEdge(4, 3);
    }

    private void generateG3() {
        addUndirectedEdge(0, 2);
        addUndirectedEdge(0, 3);
        addUndirectedEdge(1, 4);
        addUndirectedEdge(1, 5);
        addUndirectedEdge(2, 3);
        addUndirectedEdge(2, 6);
        addUndirectedEdge(4, 5);
    }

    private void generateG4() {
        addEdge(0, 1);
        addEdge(1, 2);
        addEdge(2, 0);
        addEdge(2, 3);
        addEdge(4, 2);
    }

    private void printDFS(int start) {
        System.out.println("DFS traversal starting at " + start);
        List<Integer> result = dfs(start);
        result.forEach(v -> System.out.print(v + " "));
        System.out.println("\n");
    }

    private void printPath(int start, int end) {
        System.out.println("Path from " + start + " to " + end);
        List<Integer> result = path(start, end);
        if (result.isEmpty()) {
            System.out.println("No path from " + start + " to " + end);
        } else {
            result.forEach(v -> System.out.print(v + " "));
        }
        System.out.println("\n");
    }

    private void printTopologicalSort() {
        System.out.println("Topological sort");
        List<Integer> result = topologicalSort();
        result.forEach(v -> System.out.print(v + " "));
    }

    public static void main(String[] args) {
        Graph g1 = new Graph(5);
        g1.generateG1();
        g1.printDFS(0);
        g1.printDFS(2);
        g1.printDFS(3);
        g1.printDFS(4);

        g1.printPath(0, 3);
        g1.printPath(0, 4);
        g1.printPath(1, 3);
        g1.printPath(1, 4);
        g1.printPath(4, 0);

        Graph g2 = new Graph(5);
        g2.generateG2();
        g2.printTopologicalSort();
    }
}
