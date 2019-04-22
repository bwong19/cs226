package hw8.tests;

import hw8.Map;
import hw8.HashMapLinear;

/**
 * Testing implementation of HashMap.
 */
public class HashMapLinearTest extends MapTest {
    @Override
    protected Map<String, Integer> createMap() {
        return new HashMapLinear<>();
    }
}
