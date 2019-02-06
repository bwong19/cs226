/* TODO - Add your name, JHED, and email.
 * PolyArray.java
 */

package hw2;

import exceptions.LengthException;

import java.util.ArrayList; // see note in main() below


/**
 * Simple polymorphic test framework for arrays.
 * See last week's PolyCount. You need to add more test cases (meaning more
 * methods like testNewLength and testNewWrongLength below) to make sure all
 * preconditions and axioms are indeed as expected from the specification.
*/
public final class PolyArray {
    private static final int LENGTH = 113;
    private static final int INITIAL = 7;

    private PolyArray() {}

    private static void testNewLength(Array<Integer> a) {
        assert a.length() == LENGTH;
    }

    // TODO - Add more axiom tests

    private static void testNewWrongLength() {
        try {
            Array<Integer> a = new SimpleArray<>(0, INITIAL);
            assert false;
        } catch (LengthException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new ListArray<>(0, INITIAL);
            assert false;
        } catch (LengthException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new SparseArray<>(0, INITIAL);
            assert false;
        } catch (LengthException e) {
            // passed the test, nothing to do
        }
    }

    // TODO - Add more exception tests

    /**
     * Run (mostly polymorphic) tests on various array implementations.
     * Make sure you run this with -enableassertions! We'll learn a much
     * better approach to unit testing later.
     *
     * @param args Command line arguments (ignored).
    */
    public static void main(String[] args) {
        // For various technical reasons, we cannot use a plain Java array here
        // like we did in PolyCount. Sorry.
        ArrayList<Array<Integer>> arrays = new ArrayList<>();
        arrays.add(new SimpleArray<>(LENGTH, INITIAL));
        arrays.add(new ListArray<>(LENGTH, INITIAL));
        arrays.add(new SparseArray<>(LENGTH, INITIAL));

        // Test all the axioms. We can do that nicely in a loop. In the test
        // methods, keep in mind that you are handed the same object over and
        // over again! I.e., order matters!
        for (Array<Integer> a: arrays) {
            testNewLength(a);
            // TODO - Call your axiom test methods
        }

        // Exception testing. Sadly we have to code each one of these
        // out manually, not even Java's reflection API would help...
        testNewWrongLength();
        // TODO - Call your exception test methods
    }
}
