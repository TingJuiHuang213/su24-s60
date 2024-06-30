package gh2;

import deque.Deque61B;
import deque.LinkedListDeque61B;

/**
 * 使用 Karplus-Strong 算法表示吉他弦的类。
 */
public class GuitarString {
    private static final int SR = 44100; // 采样率
    private static final double DECAY = .996; // 能量衰减因子

    private Deque61B<Double> buffer; // 用于存储声音数据的缓冲区

    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SR / frequency);
        buffer = new LinkedListDeque61B<>();
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }

    public void pluck() {
        Deque61B<Double> newBuffer = new LinkedListDeque61B<>();
        int capacity = buffer.size();
        for (int i = 0; i < capacity; i++) {
            newBuffer.addLast(Math.random() - 0.5);
        }
        buffer = newBuffer;
    }

    public void tic() {
        double firstSample = buffer.removeFirst();
        double secondSample = buffer.get(0);
        double newSample = DECAY * 0.5 * (firstSample + secondSample);
        buffer.addLast(newSample);
    }

    public double sample() {
        return buffer.get(0);
    }
}
