import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTest {
    public Triangle getNewTriangle() {
        return new Triangle() {
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
                return true;
            }

            @Override
            public String triangleType(int side1, int side2, int side3) {
                if (isEquilateral()) {
                    return "Equilateral";
                } else if (isIsosceles()) {
                    return "Isosceles";
                } else {
                    return "Scalene";
                }
            }

            @Override
            public int squaredHypotenuse(int side1, int side2) {
                return side1 * side1 + side2 * side2;
            }
        };
    }

    @Test
    public void testArea() {
        Triangle t = getNewTriangle();
        t.setSides(3, 4, 5);
        assertThat(t.getArea()).isWithin(0.0001).of(6.0);
    }

    @Test
    public void testPerimeter() {
        Triangle t = getNewTriangle();
        t.setSides(3, 4, 5);
        assertThat(t.getPerimeter()).isEqualTo(12);
    }

    @Test
    public void testEquilateral() {
        Triangle t = getNewTriangle();
        t.setSides(3, 3, 3);
        assertThat(t.isEquilateral()).isTrue();
    }

    @Test
    public void testIsosceles() {
        Triangle t = getNewTriangle();
        t.setSides(3, 3, 4);
        assertThat(t.isIsosceles()).isTrue();
    }

    @Test
    public void testScalene() {
        Triangle t = getNewTriangle();
        t.setSides(3, 4, 5);
        assertThat(t.isScalene()).isTrue();
    }

    @Test
    public void testNegativeSides() {
        Triangle t = getNewTriangle();
        assertThrows(IllegalArgumentException.class, () -> {
            t.setSides(-1, 4, 5);
        });
    }
}
