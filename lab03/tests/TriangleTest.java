import org.junit.Test;
import static com.google.common.truth.Truth.assertWithMessage;

public abstract class TriangleTest {

    /** For autograding purposes; do not change this line. */
    abstract Triangle getNewTriangle();

    /* ***** TESTS ***** */

    @Test
    public void testSidesFormTriangle() {
        Triangle t = getNewTriangle();

        // Valid triangles
        assertWithMessage("3, 4, 5 should form a triangle").that(t.sidesFormTriangle(3, 4, 5)).isTrue();
        assertWithMessage("5, 12, 13 should form a triangle").that(t.sidesFormTriangle(5, 12, 13)).isTrue();

        // Invalid triangles
        assertWithMessage("1, 2, 3 should not form a triangle").that(t.sidesFormTriangle(1, 2, 3)).isFalse();
        assertWithMessage("1, 1, 2 should not form a triangle").that(t.sidesFormTriangle(1, 1, 2)).isFalse();
        assertWithMessage("10, 1, 1 should not form a triangle").that(t.sidesFormTriangle(10, 1, 1)).isFalse();
        assertWithMessage("10, 5, 4 should not form a triangle").that(t.sidesFormTriangle(10, 5, 4)).isFalse();
    }

    @Test
    public void testPointsFormTriangle() {
        Triangle t = getNewTriangle();

        // Valid triangles
        assertWithMessage("Points (0,0), (3,0), (0,4) should form a triangle").that(t.pointsFormTriangle(0, 0, 3, 0, 0, 4)).isTrue();
        assertWithMessage("Points (1,1), (4,5), (7,2) should form a triangle").that(t.pointsFormTriangle(1, 1, 4, 5, 7, 2)).isTrue();

        // Invalid triangles (collinear points)
        assertWithMessage("Points (0,0), (1,1), (2,2) should not form a triangle").that(t.pointsFormTriangle(0, 0, 1, 1, 2, 2)).isFalse();
        assertWithMessage("Points (1,1), (2,2), (3,3) should not form a triangle").that(t.pointsFormTriangle(1, 1, 2, 2, 3, 3)).isFalse();
    }

    @Test
    public void testTriangleType() {
        Triangle t = getNewTriangle();

        // Different types of triangles
        assertWithMessage("3, 4, 5 should form a scalene triangle").that(t.triangleType(3, 4, 5)).isEqualTo("Scalene");
        assertWithMessage("5, 5, 8 should form an isosceles triangle").that(t.triangleType(5, 5, 8)).isEqualTo("Isosceles");
        assertWithMessage("5, 5, 5 should form an equilateral triangle").that(t.triangleType(5, 5, 5)).isEqualTo("Equilateral");
    }

    @Test
    public void testSquaredHypotenuse() {
        Triangle t = getNewTriangle();

        assertWithMessage("The squared hypotenuse of 3 and 4 should be 25").that(t.squaredHypotenuse(3, 4)).isEqualTo(25);
        assertWithMessage("The squared hypotenuse of 5 and 12 should be 169").that(t.squaredHypotenuse(5, 12)).isEqualTo(169);
    }
}
