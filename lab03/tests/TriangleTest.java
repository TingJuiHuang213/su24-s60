import static com.google.common.truth.Truth.assertThat;
import org.junit.Test;

abstract class TriangleTest {

    /** For autograding purposes; do not change this line. */
    abstract Triangle getNewTriangle(int a, int b, int c);

    /* ***** TESTS ***** */

    @Test
    public void testSidesFormTriangle() {
        // Arrange
        Triangle triangle = getNewTriangle(3, 4, 5);

        // Act
        boolean result = triangle.sidesFormTriangle(3, 4, 5);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    public void testPointsFormTriangle() {
        // Arrange
        Triangle triangle = getNewTriangle(3, 4, 5);

        // Act
        boolean result = triangle.pointsFormTriangle(0, 0, 3, 0, 0, 4);

        // Assert
        assertThat(result).isTrue();
    }

    @Test
    public void testTriangleTypeScalene() {
        // Arrange
        Triangle triangle = getNewTriangle(3, 4, 5);

        // Act
        String type = triangle.triangleType(3, 4, 5);

        // Assert
        assertThat(type).isEqualTo("Scalene");
    }

    @Test
    public void testTriangleTypeIsosceles() {
        // Arrange
        Triangle triangle = getNewTriangle(5, 5, 8);

        // Act
        String type = triangle.triangleType(5, 5, 8);

        // Assert
        assertThat(type).isEqualTo("Isosceles");
    }

    @Test
    public void testTriangleTypeEquilateral() {
        // Arrange
        Triangle triangle = getNewTriangle(5, 5, 5);

        // Act
        String type = triangle.triangleType(5, 5, 5);

        // Assert
        assertThat(type).isEqualTo("Equilateral");
    }

    @Test
    public void testSquaredHypotenuse() {
        // Arrange
        Triangle triangle = getNewTriangle(3, 4, 5);

        // Act
        int squaredHypotenuse = triangle.squaredHypotenuse(3, 4);

        // Assert
        assertThat(squaredHypotenuse).isEqualTo(25);
    }
}

// 具體測試類
class MyTriangleTest extends TriangleTest {
    @Override
    Triangle getNewTriangle(int a, int b, int c) {
        // 這裡應該返回具體的 Triangle 類的實例
        return new Triangle() {
            @Override
            boolean sidesFormTriangle(int side1, int side2, int side3) {
                return (side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1);
            }

            @Override
            boolean pointsFormTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
                return (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) != 0;
            }

            @Override
            String triangleType(int side1, int side2, int side3) {
                if (side1 == side2 && side2 == side3) {
                    return "Equilateral";
                } else if (side1 == side2 || side1 == side3 || side2 == side3) {
                    return "Isosceles";
                } else {
                    return "Scalene";
                }
            }

            @Override
            int squaredHypotenuse(int side1, int side2) {
                return side1 * side1 + side2 * side2;
            }
        };
    }
}
