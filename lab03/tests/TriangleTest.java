import org.junit.Test;
import static org.junit.Assert.*;

public class TriangleTest {

    protected Triangle getNewTriangle() {
        return new CustomTriangle() {
            @Override
            public boolean pointsFormTriangle(int side1, int side2, int side3) {
                return false;
            }
        };
    }

    @Test
    public void testSidesFormTriangle() {
        Triangle triangle = getNewTriangle();
        assertTrue(triangle.sidesFormTriangle(3, 4, 5));
        assertFalse(triangle.sidesFormTriangle(1, 2, 3));
    }

    @Test
    public void testTriangleType() {
        Triangle triangle = getNewTriangle();
        assertEquals("Equilateral", triangle.triangleType(3, 3, 3));
        assertEquals("Isosceles", triangle.triangleType(3, 3, 4));
        assertEquals("Scalene", triangle.triangleType(3, 4, 5));
    }

    @Test
    public void testPointsFormTriangle() {
        Triangle triangle = getNewTriangle();
        assertTrue(triangle.pointsFormTriangle(0, 0, 3, 0, 3, 4));
        assertFalse(triangle.pointsFormTriangle(0, 0, 1, 1, 2, 2));
    }

    @Test
    public void testSquaredHypotenuse() {
        Triangle triangle = getNewTriangle();
        assertEquals(25, triangle.squaredHypotenuse(3, 4));
    }
}
