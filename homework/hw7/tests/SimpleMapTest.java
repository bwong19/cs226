package hw7.tests;

import hw7.Map;
import hw7.SimpleMap;

/**
 * Testing implementation of SimpleMap.
 */
public class SimpleMapTest extends MapTest {
    @Override
    protected Map<String, Integer> createMap() {
        return new SimpleMap<>();
    }
}
