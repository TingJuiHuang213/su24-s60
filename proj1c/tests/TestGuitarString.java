import edu.princeton.cs.algs4.StdAudio;
import org.junit.jupiter.api.Test;
import gh2.GuitarString;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** 测试 GuitarString 类。
 *  该类提供了各种单元测试，以确保 GuitarString 类使用 Karplus-Strong 算法正确工作。
 */
public class TestGuitarString {

    @Test
    public void testPluckTheAString() {
        double concertA = 440.0;
        GuitarString aString = new GuitarString(concertA);
        aString.pluck();
        for (int i = 0; i < 50000; i++) {
            StdAudio.play(aString.sample());
            aString.tic();
        }
    }

    @Test
    public void testSample() {
        GuitarString string = new GuitarString(100);
        assertThat(string.sample()).isEqualTo(0.0);
        string.pluck();

        double firstSample = string.sample();
        assertWithMessage("拨弦后，您的样本不应为0").that(firstSample).isNotEqualTo(0.0);

        String errorMsg = "样本不应改变您的字符串状态";
        double secondSample = string.sample();
        assertWithMessage(errorMsg).that(secondSample).isWithin(0.0001).of(firstSample);
    }

    @Test
    public void testTic() {
        GuitarString string = new GuitarString(100);
        assertThat(string.sample()).isEqualTo(0.0);
        string.pluck();

        double initialSample = string.sample();
        assertWithMessage("拨弦后，您的样本不应为0").that(initialSample).isNotEqualTo(0.0);

        string.tic();
        String errorMsg = "tic() 后，您的样本不应保持不变";
        double newSample = string.sample();
        assertWithMessage(errorMsg).that(newSample).isNotEqualTo(initialSample);
    }

    @Test
    public void testTicCalculations() {
        GuitarString string = new GuitarString(11025);
        string.pluck();

        double s1 = string.sample();
        string.tic();
        double s2 = string.sample();
        string.tic();
        double s3 = string.sample();
        string.tic();
        double s4 = string.sample();

        string.tic();
        double expectedSample = 0.996 * 0.5 * (s1 + s2);
        double actualSample = string.sample();

        String errorMsg = "错误的 tic 值。尝试在 TestGuitarString.java 中运行 testTicCalculations 方法";
        assertWithMessage(errorMsg).that(actualSample).isWithin(0.001).of(expectedSample);
    }
}
