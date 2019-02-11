/* Name: Brandon Wong
 * JHED: bwong19
 * Email: bwong19@jhu.edu
 * TenCounter.java
 */

package hw1;

/** A counter for powers of 10. */
public class TenCounter implements ResetableCounter {

    private int val;
    
    /** Construct a new TenCounter. */
    public TenCounter() {
        val = 1;
    }

    @Override
    public void reset() {
        val = 1;
    }

    @Override
    public int value() {
        return val;
    }

    @Override
    public void up() {
        val *= 10;
    }

    @Override
    public void down() {
        if (val > 1) {
            val /= 10;
        }
    }
}
