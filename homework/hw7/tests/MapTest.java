package hw7.tests;

import hw7.Map;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing various implementations of the Map interface.
 */
public abstract class MapTest {
    /**
     * Instance of map to be tested.
     */
    private Map<String, Integer> map;

    /**
     * Creates a map according to the type of map to test.
     * @return the newly created map.
     */
    protected abstract Map<String, Integer> createMap();

    /**
     * Sets up a map before each test.
     */
    @Before
    public void setupMapTests() {
        map = this.createMap();
    }

    /**
     * Tests.
     */
    @Test
    public void testTest() {

    }

    /**
     * Tests iterator.
     */
    @Test
    public void iteratorWorks() {

    }
}
