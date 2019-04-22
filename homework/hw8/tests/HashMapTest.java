package hw8.tests;

import hw8.Map;
import hw8.HashMap;

/**
 * Testing implementation of HashMap.
 */
public class HashMapTest extends MapTest {
    @Override
    protected Map<String, Integer> createMap() {
        return new HashMap<>();
    }
}
