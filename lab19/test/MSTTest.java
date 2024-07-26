/*
 * Authors: Ting-Jui(Lyle) Huang
 * Date: 2024-07-25
 */
import org.junit.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import static com.google.common.truth.Truth.assertThat;

/**
 * 用于测试最小生成树（MST）算法的测试类。
 */
public class MSTTest {

    private final static String SEPARATOR = System.getProperty("file.separator");
    private final static String INPUT_FOLDER = System.getProperty("user.dir") + SEPARATOR + "test" + SEPARATOR + "inputs";
    private final static String NORMAL = INPUT_FOLDER + SEPARATOR + "graphTestNormal.in";
    private final static String ALL_DISJOINT = INPUT_FOLDER + SEPARATOR + "graphTestAllDisjoint.in";
    private final static String SOME_DISJOINT = INPUT_FOLDER + SEPARATOR + "graphTestSomeDisjoint.in";
    private final static String MULTI_EDGE = INPUT_FOLDER + SEPARATOR + "graphTestMultiEdge.in";

    @Test
    public void testBasic() {
        Graph g = loadFromText(NORMAL);
        // 基本测试：检查图的加载是否正确
        assertThat(g).isNotNull();
    }

    @Test
    public void testPrims() {
        Graph g = loadFromText(NORMAL);
        Graph mst = g.prims(0);
        assertThat(mst).isNotNull();
        assertThat(mst.spans(g)).isTrue();
    }

    @Test
    public void testKruskals() {
        Graph g = loadFromText(NORMAL);
        Graph mst = g.kruskals();
        assertThat(mst).isNotNull();
        assertThat(mst.spans(g)).isTrue();
    }

    /**
     * 返回一个随机生成的图对象，包含指定数量的顶点和边，边的最大权重为指定值。
     */
    public static Graph randomGraph(int vertices, int edges, int weight) {
        Graph g = new Graph();
        Random rng = new Random();
        for (int i = 0; i < vertices; i += 1) {
            g.addVertex(i);
        }
        for (int i = 0; i < edges; i += 1) {
            Edge e = new Edge(rng.nextInt(vertices), rng.nextInt(vertices), rng.nextInt(weight));
            g.addEdge(e);
        }
        return g;
    }

    /**
     * 从指定的文件中解析图对象，文件中的边权重为整数。
     */
    public static Graph loadFromText(String filename) {
        Charset cs = Charset.forName("US-ASCII");
        try (BufferedReader r = Files.newBufferedReader(Paths.get(filename), cs)) {
            Graph g = new Graph();
            String line;
            while ((line = r.readLine()) != null) {
                String[] fields = line.split(", ");
                if (fields.length == 3) {
                    int from = Integer.parseInt(fields[0]);
                    int to = Integer.parseInt(fields[1]);
                    int weight = Integer.parseInt(fields[2]);
                    g.addEdge(from, to, weight);
                } else if (fields.length == 1) {
                    g.addVertex(Integer.parseInt(fields[0]));
                } else {
                    throw new IllegalArgumentException("输入文件格式错误！");
                }
            }
            return g;
        } catch (IOException e) {
            System.err.println("捕获 IOException: " + e.getMessage());
            System.exit(1);
            return null;
        }
    }
}
