import java.util.List;
import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraphTest {

    // Helper method to create a directed graph with sample edges
    private Graph createSampleDirectedGraph() {
        Graph g = new Graph(5);
        g.addEdge(0, 1, 10);
        g.addEdge(0, 3, 30);
        g.addEdge(0, 4, 100);
        g.addEdge(1, 2, 50);
        g.addEdge(2, 4, 10);
        g.addEdge(3, 4, 60);
        g.addEdge(3, 2, 20);
        return g;
    }

    // Helper method to create a simple undirected graph
    private Graph createSimpleUndirectedGraph() {
        Graph g = new Graph(6);
        g.addUndirectedEdge(0, 1, 7);
        g.addUndirectedEdge(0, 2, 9);
        g.addUndirectedEdge(0, 5, 14);
        g.addUndirectedEdge(1, 2, 10);
        g.addUndirectedEdge(1, 3, 15);
        g.addUndirectedEdge(2, 3, 11);
        g.addUndirectedEdge(2, 5, 2);
        g.addUndirectedEdge(3, 4, 6);
        g.addUndirectedEdge(4, 5, 9);
        return g;
    }

    // Helper method to create a complex undirected graph
    private Graph createComplexUndirectedGraph() {
        Graph g = new Graph(8);
        g.addUndirectedEdge(0, 1, 4);
        g.addUndirectedEdge(0, 4, 1);
        g.addUndirectedEdge(0, 6, 2);
        g.addUndirectedEdge(0, 7, 6);
        g.addUndirectedEdge(1, 2, 1);
        g.addUndirectedEdge(1, 3, 1);
        g.addUndirectedEdge(1, 4, 3);
        g.addUndirectedEdge(2, 3, 0);
        g.addUndirectedEdge(3, 4, 2);
        g.addUndirectedEdge(3, 5, 1);
        g.addUndirectedEdge(3, 6, 3);
        g.addUndirectedEdge(4, 6, 4);
        g.addUndirectedEdge(5, 6, 5);
        g.addUndirectedEdge(5, 7, 0);
        g.addUndirectedEdge(6, 7, 2);
        return g;
    }

    @Test
    public void testShortestPathInDirectedGraph() {
        Graph g = createSampleDirectedGraph();

        List<Integer> path0 = g.shortestPath(0, 1);
        List<Integer> path1 = g.shortestPath(0, 2);
        List<Integer> path2 = g.shortestPath(0, 3);
        List<Integer> path3 = g.shortestPath(0, 4);

        List<Integer> expected0 = Arrays.asList(0, 1);
        List<Integer> expected1 = Arrays.asList(0, 3, 2);
        List<Integer> expected2 = Arrays.asList(0, 3);
        List<Integer> expected3 = Arrays.asList(0, 3, 2, 4);

        assertEquals(expected0, path0);
        assertEquals(expected1, path1);
        assertEquals(expected2, path2);
        assertEquals(expected3, path3);
    }

    @Test
    public void testUndirectedGraphPaths() {
        Graph g = createSimpleUndirectedGraph();

        List<Integer> path0 = g.shortestPath(0, 5);
        List<Integer> path1 = g.shortestPath(0, 2);
        List<Integer> path2 = g.shortestPath(0, 3);
        List<Integer> path3 = g.shortestPath(4, 0);

        List<Integer> expected0 = Arrays.asList(0, 2, 5);
        List<Integer> expected1 = Arrays.asList(0, 2);
        List<Integer> expected2 = Arrays.asList(0, 2, 3);
        List<Integer> expected3 = Arrays.asList(4, 5, 2, 0);

        assertEquals(expected0, path0);
        assertEquals(expected1, path1);
        assertEquals(expected2, path2);
        assertEquals(expected3, path3);
    }

    @Test
    public void testComplexUndirectedGraphPaths() {
        Graph g = createComplexUndirectedGraph();

        List<Integer> path0 = g.shortestPath(0, 1);
        List<Integer> path1 = g.shortestPath(0, 2);
        List<Integer> path2 = g.shortestPath(0, 3);
        List<Integer> path3 = g.shortestPath(0, 4);
        List<Integer> path4 = g.shortestPath(0, 5);
        List<Integer> path5 = g.shortestPath(0, 6);

        List<Integer> expected0 = Arrays.asList(0, 1);
        List<Integer> expected1 = Arrays.asList(0, 4, 3, 2);
        List<Integer> expected2 = Arrays.asList(0, 4, 3);
        List<Integer> expected3 = Arrays.asList(0, 4);
        List<Integer> expected4 = Arrays.asList(0, 4, 3, 5);
        List<Integer> expected5 = Arrays.asList(0, 6);

        assertEquals(expected0, path0);
        assertEquals(expected1, path1);
        assertEquals(expected2, path2);
        assertEquals(expected3, path3);
        assertEquals(expected4, path4);
        assertEquals(expected5, path5);
    }

    @Test
    public void testShortestPathWithCustomWeights() {
        Graph g = new Graph(7);
        g.addEdge(0, 1, 2);
        g.addEdge(0, 2, 3);
        g.addEdge(1, 3, 5);
        g.addEdge(2, 3, 2);
        g.addEdge(2, 4, 6);
        g.addEdge(3, 5, 1);
        g.addEdge(4, 5, 2);
        g.addEdge(5, 6, 3);

        List<Integer> path0 = g.shortestPath(0, 6);
        List<Integer> path1 = g.shortestPath(1, 5);
        List<Integer> path2 = g.shortestPath(2, 6);

        List<Integer> expected0 = Arrays.asList(0, 2, 3, 5, 6);
        List<Integer> expected1 = Arrays.asList(1, 3, 5);
        List<Integer> expected2 = Arrays.asList(2, 3, 5, 6);

        assertEquals(expected0, path0);
        assertEquals(expected1, path1);
        assertEquals(expected2, path2);
    }
}
