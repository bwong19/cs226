package hw7.tests;

import hw7.OrderedMap;
import hw7.TreapMap;

/**
 * Testing implementation of TreapMap.
 */
public class TreapMapTest extends OrderedMapTest {
    @Override
    protected OrderedMap<String, Integer> createMap() {
        return new TreapMap<>();
    }
}
