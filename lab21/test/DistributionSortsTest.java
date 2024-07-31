import org.junit.Test;
import static com.google.common.truth.Truth.assertThat;

public class DistributionSortsTest {

    @Test
    public void testCountingSort() {
        int[] arr = {3, 6, 2, 8, 2, 3, 1, 9, 0};
        DistributionSorts.countingSort(arr);
        assertThat(arr).isEqualTo(new int[]{0, 1, 2, 2, 3, 3, 6, 8, 9});
    }

    @Test
    public void testLSDRadixSort() {
        int[] arr = {329, 457, 657, 839, 436, 720, 355};
        DistributionSorts.lsdRadixSort(arr);
        assertThat(arr).isEqualTo(new int[]{329, 355, 436, 457, 657, 720, 839});
    }
}
