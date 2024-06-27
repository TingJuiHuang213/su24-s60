public class GregorianDate extends Date {

    private static final int[] MONTH_LENGTHS = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public GregorianDate(int year, int month, int dayOfMonth) {
        super(year, month, dayOfMonth);
    }

    @Override
    public int dayOfYear() {
        return calculateDayOfYear(year, month, dayOfMonth);
    }

    @Override
    public Date nextDate() {
        // 使用臨時變量來計算新日期
        int newDay = dayOfMonth + 1;
        int newMonth = month;
        int newYear = year;

        // 檢查新日期是否超過當月的天數
        if (newDay > getMonthLength(newYear, newMonth)) {
            newDay = 1; // 將日期重置為1
            newMonth += 1; // 月份加1
            if (newMonth > 12) {
                newMonth = 1; // 將月份重置為1月
                newYear += 1; // 年份加1
            }
        }

        // 返回新的 GregorianDate 對象
        return new GregorianDate(newYear, newMonth, newDay);
    }

    private int calculateDayOfYear(int year, int month, int dayOfMonth) {
        int precedingMonthDays = 0;
        for (int m = 1; m < month; m++) {
            precedingMonthDays += getMonthLength(year, m);
        }
        return precedingMonthDays + dayOfMonth;
    }

    private int getMonthLength(int year, int month) {
        if (month == 2 && isLeapYear(year)) {
            return 29; // 閏年的2月
        }
        return MONTH_LENGTHS[month - 1];
    }

    private boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }
}
