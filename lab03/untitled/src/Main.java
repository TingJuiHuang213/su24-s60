public class Main {
    public static void main(String[] args) {
        int n = 1000;  // 你可以調整 n 的大小來測試

        // 測試 Q3.1
        long startTime = System.currentTimeMillis();
        testQ31(n);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken by Q3.1: " + (endTime - startTime) + "ms");

        // 測試 Q3.2
        startTime = System.currentTimeMillis();
        System.out.println("Result of f1: " + f1(n));
        endTime = System.currentTimeMillis();
        System.out.println("Time taken by f1: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        System.out.println("Result of f2: " + f2(n));
        endTime = System.currentTimeMillis();
        System.out.println("Time taken by f2: " + (endTime - startTime) + "ms");
    }

    // Q3.1 測試代碼
    public static void testQ31(int n) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                count += j;
            }
        }
        System.out.println("Count: " + count);
    }

    // Q3.2 測試代碼
    public static int f1(int n) {
        int x = 0;
        for (int i = 0; i < n; i++) {
            x++;
        }
        return x;
    }

    public static int f2(int n) {
        if (n <= 1) {
            return 1;
        }
        return f1(n) + f2(n/2) + f2(n/2);
    }
}
