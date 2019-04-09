package hw7.tests;

import hw7.OrderedMap;
import hw7.AvlTreeMap;

import org.junit.Test;

/**
 * Testing implementation of AvlTreeMap.
 */
public class AvlTreeMapTest extends OrderedMapTest {
    @Override
    protected OrderedMap<String, Integer> createMap() {
        return new AvlTreeMap<>();
    }

//    /**
//     * Tests a single right rotation.
//     */
//    @Test
//    public void testSingleRight() {
//
//    }
//
//    /**
//     * Tests a single left rotation.
//     */
//    @Test
//    public void testSingleLeft() {
//
//    }
//
//    /**
//     * Tests a single right rotation.
//     */
//    @Test
//    public void testSingleRight() {
//
//    }
//
//    /**
//     * Tests a single right rotation.
//     */
//    @Test
//    public void testSingleRight() {
//
//    }
}
