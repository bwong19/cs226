/* Name: Brandon Wong
 * JHED: bwong19
 * Email: bwong19@jhu.edu
 * FlexibleCounter.java
 */

package hw1;

/** Class for a counter with flexible starting and incrementing values. */
public class FlexibleCounter implements ResetableCounter {

    private int val;
    private int init;
    private int increment;

    /**
     * Construct a new FlexibleCounter.
     * @param initialValue The value to start at.
     * @param incrementValue The value to increment the counter by.
     * @throws IllegalArgumentException If incrementValue is negative.
     */
    public FlexibleCounter(int initialValue, int incrementValue) throws
        IllegalArgumentException {

        if (incrementValue < 0) {
            throw new IllegalArgumentException("Negative incrementValue\n");
        }
        val = initialValue;
        init = initialValue;
        increment = incrementValue;
    }

    @Override
    public void reset() {
        val = init;
    }

    @Override
    public int value() {
        return val;
    }

    @Override
    public void up() {
        val += increment;
    }

    @Override
    public void down() {
        val -= increment;
    }
}
