/** A unique class that charts a path through pursuit curves. */
public class Path {
    private Point currentLocation; // 使用更加个性化的变量名
    private Point destination; // 使用更加个性化的变量名

    /**
     * Constructs a new Path starting at the specified coordinates.
     *
     * @param startX The x-coordinate of the starting point.
     * @param startY The y-coordinate of the starting point.
     */
    public Path(double startX, double startY) {
        this.currentLocation = new Point(0, 0); // 起始点
        this.destination = new Point(startX, startY);
    }

    /**
     * Returns the x-coordinate of the current location.
     *
     * @return The x-coordinate of the current location.
     */
    public double getCurrentX() {
        return currentLocation.getX();
    }

    /**
     * Returns the y-coordinate of the current location.
     *
     * @return The y-coordinate of the current location.
     */
    public double getCurrentY() {
        return currentLocation.getY();
    }

    /**
     * Returns the x-coordinate of the destination.
     *
     * @return The x-coordinate of the destination.
     */
    public double getDestinationX() {
        return destination.getX();
    }

    /**
     * Returns the y-coordinate of the destination.
     *
     * @return The y-coordinate of the destination.
     */
    public double getDestinationY() {
        return destination.getY();
    }

    /**
     * Returns the current location as a Point object.
     *
     * @return The current location.
     */
    public Point getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Sets the current location to the specified point.
     *
     * @param newLocation The point to set as the current location.
     */
    public void setCurrentLocation(Point newLocation) {
        this.currentLocation = newLocation;
    }

    /**
     * Updates the path by moving the current location to the destination
     * and adjusting the destination by the specified deltas.
     *
     * @param deltaX The change in the x-coordinate.
     * @param deltaY The change in the y-coordinate.
     */
    public void advance(double deltaX, double deltaY) {
        // 先将当前地点移动到目的地
        this.currentLocation = this.destination;
        // 再计算新的目的地
        this.destination = new Point(currentLocation.getX() + deltaX, currentLocation.getY() + deltaY);
    }
}
