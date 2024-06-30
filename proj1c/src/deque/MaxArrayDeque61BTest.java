package deque;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {

    private static final Comparator<Integer> naturalOrderComparator = Integer::compareTo;
    private static final Comparator<Integer> reverseOrderComparator = (a, b) -> b.compareTo(a);

    @Test
    public void testMaxWithNaturalOrder() {
        MaxArrayDeque61B<Integer> maxDeque = new MaxArrayDeque61B<>(naturalOrderComparator);
        for (int i = 0; i < 10; i++) {
            maxDeque.addLast(i);
        }
        assertThat(maxDeque.max()).isEqualTo(9);
    }

    @Test
    public void testMaxWithReverseOrder() {
        MaxArrayDeque61B<Integer> maxDeque = new MaxArrayDeque61B<>(reverseOrderComparator);
        for (int i = 0;  i < 10; i++) {
            maxDeque.addLast(i);
        }
        assertThat(maxDeque.max()).isEqualTo(0);
    }

    @Test
    public void testMaxWithCustomComparator() {
        MaxArrayDeque61B<String> maxDeque = new MaxArrayDeque61B<>(Comparator.comparingInt(String::length));
        maxDeque.addLast("short");
        maxDeque.addLast("longer");
        maxDeque.addLast("lengthiest");
        assertThat(maxDeque.max()).isEqualTo("lengthiest");

        Comparator<String> lexicographicalOrder = String::compareTo;
        assertThat(maxDeque.max(lexicographicalOrder)).isEqualTo("short");
    }

    @Test
    public void testMaxOnEmptyDeque() {
        MaxArrayDeque61B<Integer> maxDeque = new MaxArrayDeque61B<>(naturalOrderComparator);
        assertThat(maxDeque.max()).isNull();
    }

    @Test
    public void testMaxAfterRemovals() {
        MaxArrayDeque61B<Integer> maxDeque = new MaxArrayDeque61B<>(naturalOrderComparator);
        for (int i = 0; i < 10; i++) {
            maxDeque.addLast(i);
        }
        assertThat(maxDeque.max()).isEqualTo(9);
        for (int i = 0; i < 9; i++) {
            maxDeque.removeLast();
        }
        assertThat(maxDeque.max()).isEqualTo(0);
    }

    @Test
    public void testMaxWithSingleElement() {
        MaxArrayDeque61B<Integer> maxDeque = new MaxArrayDeque61B<>(naturalOrderComparator);
        maxDeque.addLast(42);
        assertThat(maxDeque.max()).isEqualTo(42);
    }

    @Test
    public void testMaxWithDuplicateElements() {
        MaxArrayDeque61B<Integer> maxDeque = new MaxArrayDeque61B<>(naturalOrderComparator);
        maxDeque.addLast(3);
        maxDeque.addLast(5);
        maxDeque.addLast(5);
        maxDeque.addLast(2);
        assertThat(maxDeque.max()).isEqualTo(5);
    }
}
