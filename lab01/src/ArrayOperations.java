/** Array Operations Class. Optional Exercise **/
public class ArrayOperations {
    /**
     * Delete the value at the given position in the argument array, shifting
     * all the subsequent elements down, and storing a 0 as the last element of
     * the array.
     */
    public static void delete(int[] values, int pos) {
        if (pos < 0 || pos >= values.length) {
            return;
        }
        // 使用 System.arraycopy 提高效率
        System.arraycopy(values, pos + 1, values, pos, values.length - 1 - pos);
        values[values.length - 1] = 0;
    }

    /**
     * Insert newInt at the given position in the argument array, shifting all
     * the subsequent elements up to make room for it. The last element in the
     * argument array is lost.
     */
    public static void insert(int[] values, int pos, int newInt) {
        if (pos < 0 || pos >= values.length) {
            return;
        }
        // 使用 System.arraycopy 提高效率
        System.arraycopy(values, pos, values, pos + 1, values.length - 1 - pos);
        values[pos] = newInt;
    }

    /**
     * Returns a new array consisting of the elements of A followed by the
     *  the elements of B.
     */
    public static int[] catenate(int[] A, int[] B) {
        int[] result = new int[A.length + B.length];
        // 使用 System.arraycopy 提高效率
        System.arraycopy(A, 0, result, 0, A.length);
        System.arraycopy(B, 0, result, A.length, B.length);
        return result;
    }
}