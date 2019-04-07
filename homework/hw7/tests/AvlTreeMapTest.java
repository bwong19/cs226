package hw7.tests;

import hw7.Map;
import hw7.AvlTreeMap;

/**
 * Testing implementation of AvlTreeMap.
 */
public abstract class AvlTreeMapTest extends OrderedMapTest {
    @Override
    protected Map<String, Integer> createMap() {
        return new AvlTreeMap<>();
    }
}
