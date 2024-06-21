public class CustomTriangleTest extends TriangleTest {
    @Override
    protected Triangle getNewTriangle() {
        return new CustomTriangle();
    }
}
