package hw7.tests;

import hw7.OrderedMap;
import hw7.BinarySearchTreeMap;

/**
 * Testing implementation of BinarySearchTreeMap.
 */
public class BinarySearchTreeMapTest extends OrderedMapTest {
    @Override
    protected OrderedMap<String, Integer> createMap() {
        return new BinarySearchTreeMap<>();
    }
}
