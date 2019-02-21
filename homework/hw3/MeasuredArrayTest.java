/* Name: Brandon Wong
 * JHED: bwong19
 * Email: bwong19@jhu.edu
 * MeasuredArrayTest.java
 */

package hw3;

import exceptions.IndexException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class for testing MeasuredArray.java, uses
 * JUnit for unit testing.
 */
public class MeasuredArrayTest {

    private static final int SIZE = 20;
    private static final String VAL = "test";

    private MeasuredArray<String> array;

    /**
     * Creates a new MeasuredArray before
     * each test.
     */
    @Before
    public void createArray() {
        this.array = new MeasuredArray<>(SIZE, VAL);
    }

    /**
     * Newly created array has no mutations.
     */
    @Test
    public void newArrayZeroMutations() {
        assertEquals(0, array.mutations());
    }

    /**
     * Tests length() of new array.
     */
    @Test
    public void newArrayLength() {
        assertEquals(SIZE, array.length());
    }

    /**
     * Checks if length() increments accesses.
     */
    @Test
    public void testLengthAccesses() {
        array.length();
        assertEquals(1, array.accesses());
        array.length();
        assertEquals(2, array.accesses());
    }

    /**
     * Checks if get(i) increments accesses.
     */
    @Test
    public void testGetAccesses() {
        array.get(0);
        assertEquals(1, array.accesses());
        array.get(SIZE - 1);
        assertEquals(2, array.accesses());
    }

    /**
     * Checks if put(i, t) increments mutations.
     */
    @Test
    public void testPutAccesses() {
        array.put(0, "new");
        assertEquals(1, array.mutations());
        array.put(SIZE - 1, "new");
        assertEquals(2, array.mutations());
    }

    /**
     * Checks if count() increments accesses.
     */
    @Test
    public void testCountAccesses() {
        array.count("test");
        assertEquals(SIZE, array.accesses());
    }

    /**
     * Checks if get(i) works.
     */
    @Test
    public void testGet() {
        assertEquals(array.get(0), "test");
    }

    /**
     * Checks if put(i, t) and get(i) work.
     */
    @Test
    public void testPutGet() {
        array.put(0, "new");
        assertEquals(array.get(0), "new");
    }

    /**
     * Checks if count() works.
     */
    @Test
    public void testCount() {
        assertEquals(SIZE, array.count("test"));
    }

    /**
     * Checks if get(i), put(i, t), and reset() work.
     */
    @Test
    public void testGetPutReset() {
        array.get(0);
        assertEquals(1, array.accesses());
        array.get(SIZE - 1);
        assertEquals(2, array.accesses());

        array.put(0, "test");
        assertEquals(1, array.mutations());
        array.put(SIZE - 1, "test");
        assertEquals(2, array.mutations());

        array.reset();
        assertEquals(0, array.accesses());
        assertEquals(0, array.mutations());
    }

    /**
     * Checks if reset() works after count().
     */
    @Test
    public void testCountReset() {
        array.count("test");
        array.reset();
        assertEquals(0, array.accesses());
        assertEquals(0, array.mutations());
    }

    /**
     * Tests if invalid indices work.
     */
    @Test(expected = IndexException.class)
    public void testInvalidIndex() {
        array.get(-1);
    }
}
