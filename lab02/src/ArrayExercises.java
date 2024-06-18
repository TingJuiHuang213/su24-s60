public class ArrayExercises {

    /** Returns an array [1, 2, 3, 4, 5, 6] */
    public static int[] makeDice() {
        // 创建一个新的数组来存储骰子的面值
        int[] diceFaces = new int[6];
        // 使用循环将值填入数组
        for (int i = 0; i < diceFaces.length; i++) {
            diceFaces[i] = i + 1;
        }
        return diceFaces;
    }

    /** Returns the order depending on the customer.
     *  If the customer is Circle, return ["beyti", "pizza", "hamburger", "tea"].
     *  If the customer is Erik, return ["sushi", "pasta", "avocado", "coffee"].
     *  In any other case, return an empty String[] of size 3. */
    public static String[] takeOrder(String customerName) {
        // 根据客户名称返回对应的订单
        if (customerName.equals("Circle")) {
            return new String[]{"beyti", "pizza", "hamburger", "tea"};
        } else if (customerName.equals("Erik")) {
            return new String[]{"sushi", "pasta", "avocado", "coffee"};
        } else {
            // 返回一个长度为3的空数组
            return new String[]{"", "", ""};
        }
    }

    /** Returns the positive difference between the maximum element and minimum element of the given array.
     *  Assumes array is nonempty. */
    public static int findMinMax(int[] array) {
        // 初始化最小值和最大值为数组中的第一个元素
        int minValue = array[0];
        int maxValue = array[0];

        // 遍历数组以找到最小值和最大值
        for (int value : array) {
            if (value < minValue) {
                minValue = value;
            }
            if (value > maxValue) {
                maxValue = value;
            }
        }

        // 计算并返回最大值和最小值之间的差值
        return maxValue - minValue;
    }
}
