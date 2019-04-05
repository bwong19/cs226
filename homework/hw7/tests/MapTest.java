package hw7.tests;

import hw7.Map.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing various implementations of the Map interface.
 */
public abstract class MapTest {
    // We're testing both maps of Strings.
    private Map<String> map;

    // Each child needs a create diffeerent type of map.
    protected abstract Map<String> createMap();

    @Before
    public void setupMapTests() {
        map = this.createList();
    }

    
}
