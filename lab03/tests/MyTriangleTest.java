public class MyTriangleTest extends TriangleTest {

    @Override
    Triangle getNewTriangle() {
        return new ConcreteTriangle();
    }
}

class ConcreteTriangle extends Triangle {
    // 無參構造函數
    public ConcreteTriangle() {
    }

    @Override
    public boolean sidesFormTriangle(int a, int b, int c) {
        return a + b > c && a + c > b && b + c > a;
    }

    @Override
    public boolean pointsFormTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        return (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) != 0;
    }

    @Override
    public String triangleType(int a, int b, int c) {
        if (a == b && b == c) {
            return "Equilateral";
        } else if (a == b || b == c || a == c) {
            return "Isosceles";
        } else {
            return "Scalene";
        }
    }

    @Override
    public int squaredHypotenuse(int a, int b) {
        return a * a + b * b;
    }
}
