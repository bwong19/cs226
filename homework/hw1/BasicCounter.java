/* Name: Brandon Wong
 * JHED: bwong19
 * Email: bwong19@jhu.edu
 * BasicCounter.java
 */

package hw1;

/** A counter that increments and decrements by 1. */
public class BasicCounter implements ResetableCounter {
    
    private int val;
    
    /** Construct a new BasicCounter. */
    public BasicCounter() {
        val = 0;
    }

    @Override
    public void reset() {
        val = 0;
    }

    @Override
    public int value() {
        return val;
    }

    @Override
    public void up() {
        val++;
    }

    @Override
    public void down() {
        val--;
    }
}
