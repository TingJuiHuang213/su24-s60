import org.junit.Rule;
import org.junit.Test;
import static com.google.common.truth.Truth.assertWithMessage;

public abstract class TriangleTest {

    /** For autograding purposes; do not change this line. */
    abstract Triangle getNewTriangle();

    /* ***** TESTS ***** */

    @Test
    public void testArea() {
        Triangle t = getNewTriangle();
        t.setSides(3, 4, 5);
        assertWithMessage("Triangle area calculation is incorrect")
                .that((double) t.getArea()).isWithin(0.0001).of(6.0);
    }

    @Test
    public void testPerimeter() {
        Triangle t = getNewTriangle();
        t.setSides(3, 4, 5);
        assertWithMessage("Triangle perimeter calculation is incorrect")
                .that(t.getPerimeter()).isEqualTo(12);
    }

    @Test
    public void testEquilateral() {
        Triangle t = getNewTriangle();
        t.setSides(3, 3, 3);
        assertWithMessage("Equilateral triangle check is incorrect")
                .that(t.isEquilateral()).isTrue();
    }

    @Test
    public void testIsosceles() {
        Triangle t = getNewTriangle();
        t.setSides(3, 3, 4);
        assertWithMessage("Isosceles triangle check is incorrect")
                .that(t.isIsosceles()).isTrue();
    }

    @Test
    public void testScalene() {
        Triangle t = getNewTriangle();
        t.setSides(3, 4, 5);
        assertWithMessage("Scalene triangle check is incorrect")
                .that(t.isScalene()).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSides() {
        Triangle t = getNewTriangle();
        t.setSides(-1, 4, 5); // 这里会抛出IllegalArgumentException
        assertWithMessage("Negative side length check is incorrect")
                .that(t.sidesFormTriangle(-1, 4, 5)).isFalse();
    }
}






