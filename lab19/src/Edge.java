public class Edge implements Comparable<Edge> {

    private int src;
    private int dest;
    private int weight;

    /* Creates an Edge (SRC, DEST) with edge weight WEIGHT. */
    Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    /* Returns the edge's source node. */
    public int getSource() {
        return src;
    }

    /* Returns the edge's destination node. */
    public int getDest() {
        return dest;
    }

    /* Returns the weight of the edge. */
    public int getWeight() {
        return weight;
    }

    public int compareTo(Edge other) {
        int cmp = weight - other.weight;
        return cmp == 0 ? 1 : cmp;
    }

    /* Returns true if two Edges have the same source, destination, and weight. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Edge e = (Edge) o;
        return (src == e.src && dest == e.dest && weight == e.weight)
                || (src == e.dest && dest == e.src && weight == e.weight);
    }

    /* Returns the hashcode for this instance. */
    @Override
    public int hashCode() {
        int hash = src ^ (src >>> 32);
        hash = 31 * hash + dest ^ (dest >>> 32);
        hash = 31 * hash + weight ^ (weight >>> 32);
        return hash;
    }

    /* Returns the string representation of an edge. */
    @Override
    public String toString() {
        return "{" + src + ", " + dest + "} -> " + weight;
    }
}
