/** Abstract class for Triangle methods.
 * DO NOT EDIT.
 * @author Crystal Wang
 * */
public abstract class Triangle {
     public abstract void setSides(int side1, int side2, int side3);
     public abstract double getArea();
     public abstract int getPerimeter();
     public abstract boolean isEquilateral();
     public abstract boolean isIsosceles();
     public abstract boolean isScalene();
     public abstract boolean sidesFormTriangle(int side1, int side2, int side3);
     public abstract boolean pointsFormTriangle(int x1, int y1, int x2, int y2, int x3, int y3);
     public abstract String triangleType(int side1, int side2, int side3);
     public abstract int squaredHypotenuse(int side1, int side2);
}

