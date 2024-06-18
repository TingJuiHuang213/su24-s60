/** A class that represents a path via pursuit curves. */
public class Path {
    private Point curr;
    private Point next;

    /**
     * Constructs a new Path starting at the specified coordinates.
     *
     * @param x The x-coordinate of the starting point.
     * @param y The y-coordinate of the starting point.
     */
    public Path(double x, double y) {
        this.curr = new Point(0, 0);  // 初始点，可以根据需求进行调整
        this.next = new Point(x, y);
    }

    /**
     * Returns the x-coordinate of the current point.
     *
     * @return The x-coordinate of the current point.
     */
    public double getCurrX() {
        return curr.getX();
    }

    /**
     * Returns the y-coordinate of the current point.
     *
     * @return The y-coordinate of the current point.
     */
    public double getCurrY() {
        return curr.getY();
    }

    /**
     * Returns the x-coordinate of the next point.
     *
     * @return The x-coordinate of the next point.
     */
    public double getNextX() {
        return next.getX();
    }

    /**
     * Returns the y-coordinate of the next point.
     *
     * @return The y-coordinate of the next point.
     */
    public double getNextY() {
        return next.getY();
    }

    /**
     * Returns the current point as a Point object.
     *
     * @return The current point.
     */
    public Point getCurrentPoint() {
        return curr;
    }

    /**
     * Sets the current point to the specified point.
     *
     * @param point The point to set as the current point.
     */
    public void setCurrentPoint(Point point) {
        this.curr = point;
    }

    /**
     * Updates the path by setting the current point to the next point and
     * moving the next point by the specified deltas.
     *
     * @param dx The change in the x-coordinate.
     * @param dy The change in the y-coordinate.
     */
    public void iterate(double dx, double dy) {
        this.curr = this.next;
        this.next = new Point(curr.getX() + dx, curr.getY() + dy);
    }
}
