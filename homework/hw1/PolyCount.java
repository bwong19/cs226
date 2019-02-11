/* Name: Brandon Wong
 * JHED: bwong19
 * Email: bwong19@jhu.edu
 * PolyCount.java
 */

package hw1;

/**
 * Basic tests for {@link hw1.ResetableCounter}s. Notes from phf's
 * PolyCount.java for this assignment.
 *
 * This is really just an example of subtype polymorphism. Note how most of
 * the code is written in terms of interfaces, not classes. The only time
 * classes are mentioned is when we have to actually instantiate the
 * counters.
 */
final class PolyCount {

    // For checkstyle to be happy.
    private PolyCount() {}

    // Test any instance of a ResetableCounter
    private static void testAnyCounter(ResetableCounter counter) {
        int x1 = counter.value();
        assert x1 >= 0;

        counter.up();
        int x2 = counter.value();
        assert x2 >= x1;

        // Set of tests for Counters
        counter.reset();
        assert x1 == counter.value();
        
        int numIncrements = 8;
        int x3 = counter.value();
        for (int i = 0; i < numIncrements; ++i) {
            counter.up();
            assert counter.value() >= x3;
            x3 = counter.value();
        }
        for (int i = 0; i < numIncrements; ++i) {
            counter.down();
            assert counter.value() <= x3;
            x3 = counter.value();
        }
        assert x1 == x3;
    }

    /**
     * Run tests on the counters using Java assertions. This means you must
     * run this with -enableassertions (-ea). Assertion testing will do for
     * now, but soon we'll learn about JUnit which is a much better approach!
     * @param args Ignored.
     */
    public static void main(String[] args) {

        ResetableCounter[] counters = {
            new BasicCounter(),
            new TenCounter(),
            new FlexibleCounter(5, 7),
        };

        for (ResetableCounter counter : counters) {
            testAnyCounter(counter);
        }

        System.out.println("All tests passed!");
    }
}