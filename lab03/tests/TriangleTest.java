import org.junit.Rule;
import org.junit.Test;
import static com.google.common.truth.Truth.assertWithMessage;

public class TriangleTest {

    /** For autograding purposes; do not change this line. */
    Triangle getNewTriangle() {
        return new Triangle(); // 确保返回一个新的Triangle实例
    }

    /* ***** TESTS ***** */

    @Test
    public void testArea() {
        Triangle t = getNewTriangle();
        t.setSides(3, 4, 5);
        assertWithMessage("Area calculation is incorrect")
                .that(t.getArea()).isWithin(0.0001).of(6.0);
    }

    @Test
    public void testPerimeter() {
        Triangle t = getNewTriangle();
        t.setSides(3, 4, 5);
        assertWithMessage("Perimeter calculation is incorrect")
                .that(t.getPerimeter()).isEqualTo(12);
    }

    @Test
    public void testEquilateralTriangle() {
        Triangle t = getNewTriangle();
        t.setSides(3, 3, 3);
        assertWithMessage("Equilateral triangle check is incorrect")
                .that(t.isEquilateral()).isTrue();
    }

    @Test
    public void testIsoscelesTriangle() {
        Triangle t = getNewTriangle();
        t.setSides(3, 3, 4);
        assertWithMessage("Isosceles triangle check is incorrect")
                .that(t.isIsosceles()).isTrue();
    }

    @Test
    public void testScaleneTriangle() {
        Triangle t = getNewTriangle();
        t.setSides(3, 4, 5);
        assertWithMessage("Scalene triangle check is incorrect")
                .that(t.isScalene()).isTrue();
    }

    @Test
    public void testInvalidTriangle() {
        Triangle t = getNewTriangle();
        assertWithMessage("Invalid triangle check is incorrect")
                .that(t.sidesFormTriangle(1, 2, 3)).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeSides() {
        Triangle t = getNewTriangle();
        t.setSides(-1, 4, 5); // 这里会抛出IllegalArgumentException
    }
}



