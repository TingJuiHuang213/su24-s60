import org.junit.Test;
import static org.junit.Assert.*;

public class GregorianDateTest {

    /**
     * 測試 GregorianDate 類的基本日期增量功能。
     */
    @Test
    public void testGregorianDate() {
        Date d1 = new GregorianDate(2018, 1, 30);
        assertEquals("2018/1/31", d1.nextDate().toString());
        assertEquals("2018/2/1", d1.nextDate().nextDate().toString());

        Date d2 = new GregorianDate(2018, 12, 30);
        assertEquals("2018/12/31", d2.nextDate().toString());
        assertEquals("2019/1/1", d2.nextDate().nextDate().toString());

        assertEquals("2018/1/30", d1.toString());
        assertEquals("2018/12/30", d2.toString());
        assertEquals("2018/1/31", d1.nextDate().toString());
        assertEquals("2018/12/31", d2.nextDate().toString());

        Date d3 = new GregorianDate(2018, 2, 27);
        assertEquals("2018/2/28", d3.nextDate().toString());
        assertEquals("2018/3/1", d3.nextDate().nextDate().toString());
    }

    /**
     * 測試 GregorianDate 類在閏年中的日期增量功能。
     */
    @Test
    public void testLeapYear() {
        Date d4 = new GregorianDate(2020, 2, 28); // 2020 是閏年
        assertEquals("2020/2/29", d4.nextDate().toString());
        assertEquals("2020/3/1", d4.nextDate().nextDate().toString());

        Date d5 = new GregorianDate(2019, 2, 28); // 2019 不是閏年
        assertEquals("2019/3/1", d5.nextDate().toString());
    }
}
