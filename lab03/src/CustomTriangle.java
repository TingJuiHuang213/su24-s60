public abstract class CustomTriangle extends Triangle {

    @Override
    public boolean sidesFormTriangle(int side1, int side2, int side3) {
        return (side1 + side2 > side3) && (side2 + side3 > side1) && (side1 + side3 > side2);
    }

    @Override
    public boolean pointsFormTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) != 0;
    }

    @Override
    public int squaredHypotenuse(int side1, int side2) {
        return side1 * side1 + side2 * side2;
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
