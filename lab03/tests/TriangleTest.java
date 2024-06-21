import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.google.common.truth.Truth.assertWithMessage;

public class TriangleTest {

    public Triangle getNewTriangle() {
        return new ConcreteTriangle(); // 確保返回一個新的Triangle實例
    }

    @Test
    public void testArea() {
        Triangle t = getNewTriangle();
        t.setSides(3, 4, 5);
        assertWithMessage("Triangle area calculation is incorrect")
                .that(t.getArea()).isWithin(0.0001).of(6.0);
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

    @Test
    public void testNegativeSides() {
        Triangle t = getNewTriangle();
        assertThrows(IllegalArgumentException.class, () -> {
            t.setSides(-1, 4, 5);
        });
    }
}

