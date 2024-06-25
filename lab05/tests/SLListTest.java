import org.junit.Test;
import static com.google.common.truth.Truth.*;

public class SLListTest {

    /** 測試在指定索引處添加元素的功能 */
    @Test
    public void testSLListAdd() {
        SLList test1 = SLList.of(1, 3, 5); /* test1: {1, 3, 5} */
        SLList test2 = new SLList(); /* test2: {} */

        test1.add(1, 2); /* test1: {1, 2, 3, 5}*/
        test1.add(3, 4); /* test1: {1, 2, 3, 4, 5}*/
        assertWithMessage("test1 does not have a size of 5").that(test1.size()).isEqualTo(5);
        assertWithMessage("test1 does not have 3 at index 2").that(test1.get(2)).isEqualTo(3);
        assertWithMessage("test1 does not have 4 at index 3").that(test1.get(3)).isEqualTo(4);

        test2.add(1, 1); /* test2: {1} */
        assertWithMessage("test2 does not contain 1").that(test2.get(0)).isEqualTo(1);
        assertWithMessage("test2 does not have a size of 1").that(test2.size()).isEqualTo(1);

        test2.add(10, 10); /* test2: {1, 10} */
        assertWithMessage("test2 does not have 10 at the index 1").that(test2.get(1)).isEqualTo(10);
        test1.add(0, 0); /* test1: {0, 1, 2, 3, 4, 5}*/
        assertWithMessage("test1 is incorrect after addition at the front").that(test1.equals(SLList.of(0, 1, 2, 3, 4, 5))).isTrue();
    }

    /** 測試反轉鏈表的功能 */
    @Test
    public void testSLListReverse() {
        SLList test1 = SLList.of(1, 2, 3, 4, 5); /* test1: {1, 2, 3, 4, 5} */
        test1.reverse(); /* test1: {5, 4, 3, 2, 1}*/
        assertWithMessage("test1 is incorrect after reverse").that(test1.equals(SLList.of(5, 4, 3, 2, 1))).isTrue();

        SLList test2 = SLList.of(1); /* test2: {1} */
        test2.reverse(); /* test2: {1}*/
        assertWithMessage("test2 is incorrect after reverse").that(test2.equals(SLList.of(1))).isTrue();

        SLList test3 = new SLList(); /* test3: {} */
        test3.reverse(); /* test3: {}*/
        assertWithMessage("test3 is incorrect after reverse").that(test3.equals(new SLList())).isTrue();
    }
}
