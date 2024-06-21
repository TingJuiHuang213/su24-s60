public class ConcreteTriangle extends Triangle {
    private int side1, side2, side3;

    @Override
    public void setSides(int side1, int side2, int side3) {
        if (sidesFormTriangle(side1, side2, side3)) {
            this.side1 = side1;
            this.side2 = side2;
            this.side3 = side3;
        } else {
            throw new IllegalArgumentException("Invalid triangle sides");
        }
    }

    @Override
    public double getArea() {
        double s = getPerimeter() / 2.0;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public int getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public boolean isEquilateral() {
        return side1 == side2 && side2 == side3;
    }

    @Override
    public boolean isIsosceles() {
        return side1 == side2 || side1 == side3 || side2 == side3;
    }

    @Override
    public boolean isScalene() {
        return side1 != side2 && side1 != side3 && side2 != side3;
    }

    @Override
    public boolean sidesFormTriangle(int side1, int side2, int side3) {
        return side1 + side2 > side3 && side1 + side3 > side2 && side2 + side3 > side1;
    }

@Override
    public boolean pointsFormTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        // 計算兩個向量的叉積是否為零
        return (x2 - x1) * (y3 - y1) != (x3 - x1) * (y2 - y1);
    }

    @Override
    public String triangleType(int side1, int side2, int side3) {
        if (!sidesFormTriangle(side1, side2, side3)) {
            return "Not a triangle";
        }
        if (side1 == side2 && side2 == side3) {
            return "Equilateral";
        } else if (side1 == side2 || side2 == side3 || side1 == side3) {
            return "Isosceles";
        } else {
            return "Scalene";
        }
    }

    @Override
    public int squaredHypotenuse(int side1, int side2) {
        return side1 * side1 + side2 * side2;
    }
}
