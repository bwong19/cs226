package hw7.tests;

import hw7.OrderedMap.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementations of OrderedMaps only
 */
public abstract class OrderedMapTest {
    private OrderedMap<String> orderedmap;

    protected abstract OrderedMap<String> createOrderedMap();

    @Before
    public void setupOrderedMapTest() {
        orderedmap = this.createOrderedMap();
    }
}
