public class ArrayExercises {

    /** Returns an array [1, 2, 3, 4, 5, 6] */
    public static int[] makeDice() {
        // 定义一个表示骰子面的数组
        int[] diceFaces = new int[6];
        // 使用循环填充数组
        for (int i = 0; i < 6; i++) {
            diceFaces[i] = i + 1;
        }
        return diceFaces;
    }

    /** Returns the order depending on the customer.
     *  If the customer is Circle, return ["beyti", "pizza", "hamburger", "tea"].
     *  If the customer is Erik, return ["sushi", "pasta", "avocado", "coffee"].
     *  In any other case, return an empty String[] of size 3. */
    public static String[] takeOrder(String customerName) {
        // 定义两个数组以表示不同客户的订单
        String[] circleOrder = {"beyti", "pizza", "hamburger", "tea"};
        String[] erikOrder = {"sushi", "pasta", "avocado", "coffee"};

        // 根据客户名称返回相应的订单
        switch (customerName) {
            case "Circle":
                return circleOrder;
            case "Erik":
                return erikOrder;
            default:
                return new String[3]; // 返回一个长度为3的空字符串数组
        }
    }

    /** Returns the positive difference between the maximum element and minimum element of the given array.
     *  Assumes array is nonempty. */
    public static int findMinMax(int[] array) {
        // 初始化最小值和最大值为数组的第一个元素
        int minValue = array[0];
        int maxValue = array[0];

        // 遍历数组找出最小值和最大值
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
