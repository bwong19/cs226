package hw8.tests;

import hw8.HashMapLinearTomb;
import hw8.Map;

/**
 * Testing implementation of HashMap.
 */
public class HashMapLinearTombTest extends MapTest {
    @Override
    protected Map<String, Integer> createMap() {
        return new HashMapLinearTomb<>();
    }
}
