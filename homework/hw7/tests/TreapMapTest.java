package hw7.tests;

import hw7.Map;
import hw7.TreapMap;

/**
 * Testing implementation of TreapMap.
 */
public abstract class TreapMapTest extends OrderedMapTest {
    @Override
    protected Map<String, Integer> createMap() {
        return new TreapMap<>();
    }
}
