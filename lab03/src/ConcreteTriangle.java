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
        return side1 == side2 || side2 == side3 || side1 == side3;
    }

    @Override
    public boolean isScalene() {
        return side1 != side2 && side2 != side3 && side1 != side3;
    }

    @Override
    public boolean sidesFormTriangle(int side1, int side2, int side3) {
        return side1 + side2 > side3 && side2 + side3 > side1 && side1 + side3 > side2;
    }
}
