package gh2;

import deque.Deque61B;
import deque.LinkedListDeque61B;

public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque61B<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // Initialize the buffer with capacity = SR / frequency.
        int capacity = (int) Math.round(SR / frequency);
        buffer = new LinkedListDeque61B<>();
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // Dequeue everything in buffer, and replace with random numbers
        // between -0.5 and 0.5.
        for (int i = 0; i < buffer.size(); i++) {
            buffer.removeFirst();
            buffer.addLast(Math.random() - 0.5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        // Dequeue the front sample and enqueue a new sample that is
        // the average of the two multiplied by the DECAY factor.
        double first = buffer.removeFirst();
        double second = buffer.get(0);
        double newDouble = DECAY * 0.5 * (first + second);
        buffer.addLast(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // Return the correct thing.
        return buffer.get(0);
    }
}
