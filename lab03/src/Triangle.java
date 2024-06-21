/** Abstract class for Triangle methods.
 * DO NOT EDIT.
 * @author Crystal Wang
 * */
abstract class AbstractTriangle {
     /** Given triangle side lengths side1, side2, and side3, return whether they could form a valid triangle
      * defined by the triangle inequality: the sum of any two sides must be > the third side. */
     abstract boolean sidesFormTriangle(int side1, int side2, int side3);

     /** Given points (x1, y1), (x2, y2), and (x3, y3), return whether they could form a valid triangle in a 2-D plane. */
     abstract boolean pointsFormTriangle(int x1, int y1, int x2, int y2, int x3, int y3);

     /** Given triangle side lengths side1, side2, and side3, return whether the triangle is
      * Scalene (all sides are different lengths), Isosceles (two sides are different lengths), or Equilateral (all sides are the same length).
      * You may assume that the side lengths actually form a triangle together (no need to check first). */
     abstract String triangleType(int side1, int side2, int side3);

     /** Given triangle leg lengths side1 and side2, return the squared hypotenuse of the triangle according to the formula
      * squaredHypotenuse = side1^2 + side2^2 */
     abstract int squaredHypotenuse(int side1, int side2);
}

public class Triangle extends AbstractTriangle {

     private int side1;
     private int side2;
     private int side3;

     @Override
     boolean sidesFormTriangle(int side1, int side2, int side3) {
          return (side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1);
     }

     @Override
     boolean pointsFormTriangle(int x1, int y1, int x2, int y2, int x3, int y3) {
          // 使用面积的方法来检查点是否能形成一个三角形
          // 面积 = 0.5 * | x1(y2 - y3) + x2(y3 - y1) + x3(y1 - y2) |
          int area = Math.abs(x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
          return area != 0;
     }

     @Override
     String triangleType(int side1, int side2, int side3) {
          if (side1 == side2 && side2 == side3) {
               return "Equilateral"; // 等边三角形
          } else if (side1 == side2 || side2 == side3 || side1 == side3) {
               return "Isosceles"; // 等腰三角形
          } else {
               return "Scalene"; // 不等边三角形
          }
     }

     @Override
     int squaredHypotenuse(int side1, int side2) {
          return side1 * side1 + side2 * side2;
     }

     // 设置三角形的边长
     public void setSides(int side1, int side2, int side3) {
          if (sidesFormTriangle(side1, side2, side3)) {
               this.side1 = side1;
               this.side2 = side2;
               this.side3 = side3;
          } else {
               throw new IllegalArgumentException("Invalid triangle sides");
          }
     }

     // 获取三角形的周长
     public int getPerimeter() {
          return side1 + side2 + side3;
     }

     // 获取三角形的面积
     public double getArea() {
          double s = getPerimeter() / 2.0;
          return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
     }

     // 判断三角形是否有效
     public boolean isValid() {
          return sidesFormTriangle(side1, side2, side3);
     }

     // 判断三角形是否是等边三角形
     public boolean isEquilateral() {
          return side1 == side2 && side2 == side3;
     }

     // 判断三角形是否是等腰三角形
     public boolean isIsosceles() {
          return side1 == side2 || side2 == side3 || side1 == side3;
     }

     // 判断三角形是否是不等边三角形
     public boolean isScalene() {
          return side1 != side2 && side2 != side3 && side1 != side3;
     }
}

