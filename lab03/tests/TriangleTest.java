import org.junit.jupiter.api.Test;
import static com.google.common.truth.Truth.assertThat;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TriangleTest {

    public Triangle getNewTriangle() {
        return new ConcreteTriangle();
    }

    @Test
    public void testArea() {
        Triangle t = getNewTriangle();
        t.setSides(3, 4, 5);
        assertThat(t.getArea()).isWithin(0.0001).of(6.0);
    }

    @Test
    public void testPerimeter() {
        Triangle t = getNewTriangle();
        t.setSides(3, 4, 5);
        assertThat(t.getPerimeter()).isEqualTo(12);
    }

    @Test
    public void testEquilateral() {
        Triangle t = getNewTriangle();
        t.setSides(3, 3, 3);
        assertThat(t.isEquilateral()).isTrue();
    }

    @Test
    public void testIsosceles() {
        Triangle t = getNewTriangle();
        t.setSides(3, 3, 4);
        assertThat(t.isIsosceles()).isTrue();
    }

    @Test
    public void testScalene() {
        Triangle t = getNewTriangle();
        t.setSides(3, 4, 5);
        assertThat(t.isScalene()).isTrue();
    }

    @Test
    public void testNegativeSides() {
        Triangle t = getNewTriangle();
        assertThrows(IllegalArgumentException.class, () -> {
            t.setSides(-1, 4, 5);
        });
    }
}
