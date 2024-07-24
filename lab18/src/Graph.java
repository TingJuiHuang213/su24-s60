import java.util.*;

public class Graph {

    private LinkedList<Edge>[] adjLists;
    private int vertexCount;

    /* Initializes a graph with NUMVERTICES vertices and no Edges. */
    public Graph(int numVertices) {
        adjLists = (LinkedList<Edge>[]) new LinkedList[numVertices];
        for (int k = 0; k < numVertices; k++) {
            adjLists[k] = new LinkedList<Edge>();
        }
        vertexCount = numVertices;
    }

    /* Adds a directed Edge (V1, V2) to the graph with weight WEIGHT. If the
       Edge already exists, replaces the current Edge with a new Edge with
       weight WEIGHT. */
    public void addEdge(int v1, int v2, int weight) {
        for (Edge e : adjLists[v1]) {
            if (e.to == v2) {
                e.weight = weight;
                return;
            }
        }
        adjLists[v1].add(new Edge(v1, v2, weight));
    }

    /* Adds an undirected Edge (V1, V2) to the graph with weight WEIGHT. If the
       Edge already exists, replaces the current Edge with a new Edge with
       weight WEIGHT. */
    public void addUndirectedEdge(int v1, int v2, int weight) {
        addEdge(v1, v2, weight);
        addEdge(v2, v1, weight);
    }

    /* Returns true if there exists an Edge from vertex FROM to vertex TO.
       Returns false otherwise. */
    public boolean isAdjacent(int from, int to) {
        for (Edge e : adjLists[from]) {
            if (e.to == to) {
                return true;
            }
        }
        return false;
    }

    /* Returns a list of all the vertices u such that the Edge (V, u)
       exists in the graph. */
    public List<Integer> neighbors(int v) {
        List<Integer> result = new ArrayList<>();
        for (Edge e : adjLists[v]) {
            result.add(e.to);
        }
        return result;
    }

    /* Returns the number of incoming Edges for vertex V. */
    public int inDegree(int v) {
        int inDegreeCount = 0;
        for (int i = 0; i < vertexCount; i++) {
            for (Edge e : adjLists[i]) {
                if (e.to == v) {
                    inDegreeCount++;
                }
            }
        }
        return inDegreeCount;
    }

    /* Returns a list of the vertices that lie on the shortest path from start to stop.
       If no such path exists, you should return an empty list. If START == STOP, returns a List with START. */
    public List<Integer> shortestPath(int start, int stop) {
        PriorityQueue<VertexDist> fringe = new PriorityQueue<>(Comparator.comparingInt(v -> v.dist));
        Map<Integer, Integer> dist = new HashMap<>();
        Map<Integer, Integer> predecessors = new HashMap<>();
        Set<Integer> visited = new HashSet<>();

        dist.put(start, 0);
        fringe.add(new VertexDist(start, 0));

        while (!fringe.isEmpty()) {
            VertexDist vDist = fringe.poll();
            int v = vDist.vertex;

            if (visited.contains(v)) {
                continue;
            }

            visited.add(v);

            if (v == stop) {
                break;
            }

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

        List<Integer> path = new LinkedList<>();
        if (!dist.containsKey(stop)) {
            return path;
        }

        for (Integer at = stop; at != null; at = predecessors.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        return path;
    }

    private Edge getEdge(int v1, int v2) {
        for (Edge e : adjLists[v1]) {
            if (e.to == v2) {
                return e;
            }
        }
        return null;
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
