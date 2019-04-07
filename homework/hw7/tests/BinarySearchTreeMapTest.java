package hw7.tests;

import hw7.Map;
import hw7.BinarySearchTreeMap;

/**
 * Testing implementation of BinarySearchTreeMap.
 */
public abstract class BinarySearchTreeMapTest extends OrderedMapTest {
    @Override
    protected Map<String, Integer> createMap() {
        return new BinarySearchTreeMap<>();
    }
}
