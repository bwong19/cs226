/* Name: Brandon Wong
 * JHED: bwong19
 * Email: bwong19@jhu.edu
 * PolyArray.java
 */

package hw2;

import exceptions.IndexException;
import exceptions.LengthException;

import java.util.ArrayList; // see note in main() below
import java.util.Iterator;

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

    /**
     * AXIOM TESTS
     */

    private static void testNewLength(Array<Integer> a) {
        assert a.length() == LENGTH;
    }
    
    private static void testNewGet(Array<Integer> a) {
        for (int i = 0; i < LENGTH; ++i) {
            assert a.get(i) == INITIAL;
        }
    }
    
    private static void testInitialIterator(Array<Integer> a) {
        Iterator<Integer> it = a.iterator();
        while (it.hasNext()) {
            assert it.next() == INITIAL;
        }
    }
    
    private static void testPutLength(Array<Integer> a) {
        a.put(LENGTH / 2, INITIAL / 2);
        assert a.length() == LENGTH;
    }

    private static void testSparseIterator(Array<Integer> a) {
        Iterator<Integer> it = a.iterator();
        int counter = 0;
        while (it.hasNext()) {
            if (counter == LENGTH / 2) {
                assert it.next() == INITIAL / 2;
            }
            else {
                assert it.next() == INITIAL;
            }
            counter++;
        }
    }

    private static void testPutAndGet(Array<Integer> a) {
        for (int i = 0; i < LENGTH; ++i) {
            a.put(i, INITIAL - 1);
            assert a.get(i) == INITIAL - 1;
        }
    }

    private static void testDenseIterator(Array<Integer> a) {
        Iterator<Integer> it = a.iterator();
        while (it.hasNext()) {
            assert it.next() == INITIAL - 1;
        }
    }


    /**
     * EXCEPTION TESTS
     */

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

    private static void testGetWrongIndex() {
        try {
            Array<Integer> a = new SimpleArray<>(LENGTH, INITIAL);
            int val = a.get(-2);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new SimpleArray<>(LENGTH, INITIAL);
            int val = a.get(LENGTH + 100);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
        
        try {
            Array<Integer> a = new ListArray<>(LENGTH, INITIAL);
            int val = a.get(-2);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new ListArray<>(LENGTH, INITIAL);
            int val = a.get(LENGTH + 100);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }

        try {
            Array<Integer> a = new SparseArray<>(LENGTH, INITIAL);
            int val = a.get(-2);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new SparseArray<>(LENGTH, INITIAL);
            int val = a.get(LENGTH + 100);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
    }

    private static void testPutWrongIndex() {
        try {
            Array<Integer> a = new SimpleArray<>(LENGTH, INITIAL);
            a.put(-2, INITIAL);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new SimpleArray<>(LENGTH, INITIAL);
            a.put(LENGTH + 100, INITIAL - 10);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
        
        try {
            Array<Integer> a = new ListArray<>(LENGTH, INITIAL);
            a.put(-2, INITIAL);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new ListArray<>(LENGTH, INITIAL);
            a.put(LENGTH + 100, INITIAL - 10);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }

        try {
            Array<Integer> a = new SparseArray<>(LENGTH, INITIAL);
            a.put(-2, INITIAL);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new SparseArray<>(LENGTH, INITIAL);
            a.put(LENGTH + 100, INITIAL - 10);
            assert false;
        } catch (IndexException e) {
            // passed the test, nothing to do
        }
    }

    private static void testIteratorRemove() {
        try {
            Array<Integer> a = new SimpleArray<>(LENGTH, INITIAL);
            Iterator<Integer> it = a.iterator();
            it.remove();
            assert false;
        } catch (UnsupportedOperationException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new ListArray<>(LENGTH, INITIAL);
            Iterator<Integer> it = a.iterator();
            it.remove();
            assert false;
        } catch (UnsupportedOperationException e) {
            // passed the test, nothing to do
        }
        try {
            Array<Integer> a = new SparseArray<>(LENGTH, INITIAL);
            Iterator<Integer> it = a.iterator();
            it.remove();
            assert false;
        } catch (UnsupportedOperationException e) {
            // passed the test, nothing to do
        }
    }

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
            testNewGet(a);
            testInitialIterator(a);
            testPutLength(a);
            testSparseIterator(a);
            testPutAndGet(a);
            testDenseIterator(a);
        }

        // Exception testing. Sadly we have to code each one of these
        // out manually, not even Java's reflection API would help...
        testNewWrongLength();
        testGetWrongIndex();
        testPutWrongIndex();
        testIteratorRemove();
    }
}
