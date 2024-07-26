/**
 * 表示图中的一条边，带有源顶点、目标顶点和权重。
 * 这个类实现了Comparable接口，用于边的排序。
 */
public class Edge implements Comparable<Edge> {

    private int src; // 源顶点
    private int dest; // 目标顶点
    private int weight; // 边的权重

    /**
     * 构造方法，创建一条边 (SRC, DEST) 并指定边的权重 WEIGHT。
     */
    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    /**
     * 返回边的源顶点。
     */
    public int getSource() {
        return src;
    }

    /**
     * 返回边的目标顶点。
     */
    public int getDest() {
        return dest;
    }

    /**
     * 返回边的权重。
     */
    public int getWeight() {
        return weight;
    }

    /**
     * 比较两条边的权重以进行排序。
     */
    public int compareTo(Edge other) {
        int cmp = Integer.compare(this.weight, other.weight);
        return cmp == 0 ? 1 : cmp;
    }

    /**
     * 判断两条边是否相等（源顶点、目标顶点和权重均相等）。
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return (src == edge.src && dest == edge.dest && weight == edge.weight) ||
                (src == edge.dest && dest == edge.src && weight == edge.weight);
    }

    /**
     * 返回边的哈希码。
     */
    @Override
    public int hashCode() {
        int result = src;
        result = 31 * result + dest;
        result = 31 * result + weight;
        return result;
    }

    /**
     * 返回边的字符串表示形式。
     */
    @Override
    public String toString() {
        return String.format("{%d, %d} -> %d", src, dest, weight);
    }
}
