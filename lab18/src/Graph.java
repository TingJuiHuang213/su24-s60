import java.util.*;

public class Graph {

    private LinkedList<Edge>[] adjLists;
    private int vertexCount;

    // Initializes a graph with NUMVERTICES vertices and no Edges.
    public Graph(int numVertices) {
        adjLists = (LinkedList<Edge>[]) new LinkedList[numVertices];
        initializeAdjLists(numVertices);
        vertexCount = numVertices;
    }

    // Helper method to initialize adjacency lists
    private void initializeAdjLists(int numVertices) {
        for (int i = 0; i < numVertices; i++) {
            adjLists[i] = new LinkedList<>();
        }
    }

    // Adds a directed Edge (V1, V2) to the graph with weight WEIGHT.
    public void addEdge(int v1, int v2, int weight) {
        Edge edge = findEdge(v1, v2);
        if (edge != null) {
            edge.weight = weight;
        } else {
            adjLists[v1].add(new Edge(v1, v2, weight));
        }
    }

    // Finds an existing edge or returns null if not found
    private Edge findEdge(int from, int to) {
        for (Edge e : adjLists[from]) {
            if (e.to == to) {
                return e;
            }
        }
        return null;
    }

    // Adds an undirected Edge (V1, V2) to the graph with weight WEIGHT.
    public void addUndirectedEdge(int v1, int v2, int weight) {
        addEdge(v1, v2, weight);
        addEdge(v2, v1, weight);
    }

    // Returns true if there exists an Edge from vertex FROM to vertex TO.
    public boolean isAdjacent(int from, int to) {
        return findEdge(from, to) != null;
    }

    // Returns a list of all the vertices u such that the Edge (V, u) exists in the graph.
    public List<Integer> neighbors(int v) {
        List<Integer> neighborList = new ArrayList<>();
        for (Edge e : adjLists[v]) {
            neighborList.add(e.to);
        }
        return neighborList;
    }

    // Returns the number of incoming Edges for vertex V.
    public int inDegree(int v) {
        int count = 0;
        for (LinkedList<Edge> edges : adjLists) {
            for (Edge e : edges) {
                if (e.to == v) {
                    count++;
                }
            }
        }
        return count;
    }

    // Returns a list of the vertices that lie on the shortest path from start to stop.
    public List<Integer> shortestPath(int start, int stop) {
        PriorityQueue<VertexDist> fringe = new PriorityQueue<>(Comparator.comparingInt(v -> v.dist));
        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Integer> predecessors = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        initializeFringeAndDist(start, fringe, dist);

        while (!fringe.isEmpty()) {
            VertexDist vDist = fringe.poll();
            int v = vDist.vertex;

            if (visited.contains(v)) continue;

            visited.add(v);
            if (v == stop) break;

            processNeighbors(v, dist, predecessors, fringe);
        }

        return constructPath(start, stop, predecessors);
    }

    // Helper method to initialize fringe and distance map
    private void initializeFringeAndDist(int start, PriorityQueue<VertexDist> fringe, Map<Integer, Integer> dist) {
        dist.put(start, 0);
        fringe.add(new VertexDist(start, 0));
    }

    // Helper method to process neighbors in Dijkstra's algorithm
    private void processNeighbors(int v, Map<Integer, Integer> dist, Map<Integer, Integer> predecessors, PriorityQueue<VertexDist> fringe) {
        for (Edge e : adjLists[v]) {
            int w = e.to;
            int newDist = dist.get(v) + e.weight;

            if (!dist.containsKey(w) || newDist < dist.get(w)) {
                dist.put(w, newDist);
                predecessors.put(w, v);
                fringe.add(new VertexDist(w, newDist));
            }
        }
    }

    // Helper method to construct the shortest path
    private List<Integer> constructPath(int start, int stop, Map<Integer, Integer> predecessors) {
        LinkedList<Integer> path = new LinkedList<>();
        if (!predecessors.containsKey(stop)) return path;

        for (Integer at = stop; at != null; at = predecessors.get(at)) {
            path.addFirst(at);
        }

        return path;
    }

    // Adds more edges and vertices for testing purposes
    public void addAdditionalEdges(int[][] edges) {
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1], edge[2]);
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

        @Override
        public String toString() {
            return "(" + from + ", " + to + ", weight = " + weight + ")";
        }

        public int to() {
            return this.to;
        }
    }

    private class VertexDist {
        int vertex;
        int dist;

        VertexDist(int vertex, int dist) {
            this.vertex = vertex;
            this.dist = dist;
        }
    }
}
