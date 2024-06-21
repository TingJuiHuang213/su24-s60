public class MyTriangleTest extends TriangleTest {

    @Override
    Triangle getNewTriangle() {
        return new ConcreteTriangle(3, 4, 5); // 替换成实际参数
    }
}

class ConcreteTriangle extends Triangle {
    private int side1, side2, side3;

    public ConcreteTriangle(int a, int b, int c) {
        this.side1 = a;
        this.side2 = b;
        this.side3 = c;
    }

    @Override
    boolean sidesFormTriangle(int a, int b, int c) {
        // 实现 sidesFormTriangle 方法
        return a + b > c && a + c > b && b + c > a;
    }

    @Override
    boolean pointsFormTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
        // 实现 pointsFormTriangle 方法
        return (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) != 0;
    }

    @Override
    String triangleType(int a, int b, int c) {
        // 实现 triangleType 方法
        if (a == b && b == c) {
            return "Equilateral";
        } else if (a == b || b == c || a == c) {
            return "Isosceles";
        } else {
            return "Scalene";
        }
    }

    @Override
    int squaredHypotenuse(int a, int b) {
        // 实现 squaredHypotenuse 方法
        return a * a + b * b;
    }
}
