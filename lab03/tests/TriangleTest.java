import static org.junit.Assert.*;
import org.junit.Test;

public class TriangleTest {
    @Test
    public void testSidesFormTriangle() {
        Triangle triangle = getNewTriangle();
        assertTrue(triangle.sidesFormTriangle(3, 4, 5));
        assertFalse(triangle.sidesFormTriangle(1, 2, 3));
    }

    @Test
    public void testPointsFormTriangle() {
        Triangle triangle = getNewTriangle();
        assertTrue(triangle.pointsFormTriangle(0, 0, 1, 1, 1, 0));
        assertFalse(triangle.pointsFormTriangle(0, 0, 1, 1, 2, 2));
    }

    @Test
    public void testSquaredHypotenuse() {
        Triangle triangle = getNewTriangle();
        assertEquals(25, triangle.squaredHypotenuse(3, 4));
    }

    @Test
    public void testTriangleType() {
        Triangle triangle = getNewTriangle();
        assertEquals("Equilateral", triangle.triangleType(3, 3, 3));
        assertEquals("Isosceles", triangle.triangleType(3, 3, 4));
        assertEquals("Scalene", triangle.triangleType(3, 4, 5));
    }

    // Ensure this method exists and is correctly implemented in your TriangleTest class
    protected Triangle getNewTriangle() {
        return new CustomTriangle();
    }
}
