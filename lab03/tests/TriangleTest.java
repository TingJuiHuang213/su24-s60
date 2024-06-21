import org.junit.Rule;
import org.junit.Test;
import static com.google.common.truth.Truth.assertWithMessage;

public abstract class TriangleTest {

    /** For autograding purposes; do not change this line. */
    abstract Triangle getNewTriangle();

    /* ***** TESTS ***** */

    @Test
    public void test1() {
        // Arrange
        Triangle t = getNewTriangle();

        // Act
        boolean sidesResult = t.sidesFormTriangle(3, 4, 5);
        boolean pointsResult = t.pointsFormTriangle(0, 0, 3, 0, 0, 4);
        String scaleneType = t.triangleType(3, 4, 5);
        String isoscelesType = t.triangleType(5, 5, 8);
        String equilateralType = t.triangleType(5, 5, 5);
        int squaredHypotenuse = t.squaredHypotenuse(3, 4);

        // Assert
        assertWithMessage("3, 4, 5 should form a triangle").that(sidesResult).isTrue();
        assertWithMessage("Points (0,0), (3,0), (0,4) should form a triangle").that(pointsResult).isTrue();
        assertWithMessage("3, 4, 5 should form a scalene triangle").that(scaleneType).isEqualTo("Scalene");
        assertWithMessage("5, 5, 8 should form an isosceles triangle").that(isoscelesType).isEqualTo("Isosceles");
        assertWithMessage("5, 5, 5 should form an equilateral triangle").that(equilateralType).isEqualTo("Equilateral");
        assertWithMessage("The squared hypotenuse of 3 and 4 should be 25").that(squaredHypotenuse).isEqualTo(25);
    }
}
