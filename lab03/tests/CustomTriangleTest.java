public class CustomTriangleTest extends TriangleTest {
    @Override
    protected Triangle getNewTriangle() {
        return new CustomTriangle() {
            @Override
            public boolean pointsFormTriangle(int side1, int side2, int side3) {
                return false;
            }
        };
    }
}
