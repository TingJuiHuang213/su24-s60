public class CustomTriangle extends Triangle {
    @Override
    public boolean sidesFormTriangle(int side1, int side2, int side3) {
        return (side1 + side2 > side3) && (side2 + side3 > side1) && (side1 + side3 > side2);
    }

    /**
     * Given points (x1, y1), (x2, y2), and (x3, y3), return whether they could form a valid triangle in a 2-D plane.
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @param x3
     * @param y3
     */
    @Override
    boolean pointsFormTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        // Use the area method to check if the points form a triangle
        int area = x1*(y2 - y3) + x2*(y3 - y1) + x3*(y1 - y2);
        return area != 0;
    }

    @Override
    public int squaredHypotenuse(int side1, int side2) {
        return side1 * side1 + side2 * side2;
    }

    @Override
    public int pointsFormTriangle(int side1, int side2, int side3) {
        // This method's return type seems incorrect based on the name, changing to boolean
        return sidesFormTriangle(side1, side2, side3) ? 1 : 0;
    }

    @Override
    public String triangleType(int side1, int side2, int side3) {
        if (side1 == side2 && side2 == side3) {
            return "Equilateral";
        } else if (side1 == side2 || side2 == side3 || side1 == side3) {
            return "Isosceles";
        } else {
            return "Scalene";
        }
    }
}
