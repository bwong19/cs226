package hw7.tests;

import hw7.OrderedMap;
import hw7.AvlTreeMap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Testing implementation of AvlTreeMap.
 */
public class AvlTreeMapTest extends OrderedMapTest {
    @Override
    protected OrderedMap<String, Integer> createMap() {
        return new AvlTreeMap<>();
    }

    /**
     * Tests a single right rotation.
     */
    @Test
    public void testSingleRight() {
        map.insert("30", 1);
        map.insert("25", 2);
        map.insert("31", 3);
        map.insert("20", 4);
        map.insert("26", 5);
        assertEquals("{20: 4, 25: 2, 26: 5, 30: 1, 31: 3}", map.toString());

        map.insert("21", 6);
        assertEquals("{20: 4, 21: 6, 25: 2, 26: 5, 30: 1," +
                " 31: 3}", map.toString());
    }

    /**
     * Tests a single right remove.
     */
    @Test
    public void testSingleRightRemove() {
        map.insert("30", 1);
        map.insert("25", 2);
        map.insert("31", 3);
        map.insert("20", 4);
        map.insert("26", 5);
        map.insert("35", 0);
        map.insert("21", 6);
        assertEquals("{20: 4, 21: 6, 25: 2, 26: 5, 30: 1," +
                " 31: 3, 35: 0}", map.toString());

        map.remove("35");
        assertEquals("{20: 4, 21: 6, 25: 2, 26: 5, 30: 1," +
                " 31: 3}", map.toString());
    }

    /**
     * Tests a single left rotation.
     */
    @Test
    public void testSingleLeft() {
        map.insert("20", 1);
        map.insert("16", 2);
        map.insert("25", 3);
        map.insert("21", 4);
        map.insert("30", 5);
        assertEquals("{16: 2, 20: 1, 21: 4, 25: 3, 30: 5}", map.toString());

        map.insert("26", 6);
        assertEquals("{16: 2, 20: 1, 21: 4, 25: 3, 26: 6," +
                " 30: 5}", map.toString());
    }

    /**
     * Tests a single left remove.
     */
    @Test
    public void testSingleLeftRemove() {
        map.insert("20", 1);
        map.insert("16", 2);
        map.insert("25", 3);
        map.insert("15", 0);
        map.insert("21", 4);
        map.insert("30", 5);
        map.insert("26", 6);
        assertEquals("{15: 0, 16: 2, 20: 1, 21: 4, 25: 3," +
                " 26: 6, 30: 5}", map.toString());

        map.remove("15");
        assertEquals("{16: 2, 20: 1, 21: 4, 25: 3, 26: 6," +
                " 30: 5}", map.toString());
    }

    /**
     * Tests a double right left rotation.
     */
    @Test
    public void testDoubleRL() {
        map.insert("20", 1);
        map.insert("16", 2);
        map.insert("30", 3);
        map.insert("25", 4);
        map.insert("31", 5);
        assertEquals("{16: 2, 20: 1, 25: 4, 30: 3, 31: 5}", map.toString());

        map.insert("21", 6);
        assertEquals("{16: 2, 20: 1, 21: 6, 25: 4, 30: 3," +
                " 31: 5}", map.toString());
    }

    /**
     * Tests a double right left remove.
     */
    @Test
    public void testDoubleRLRemove() {
        map.insert("20", 1);
        map.insert("16", 2);
        map.insert("30", 3);
        map.insert("15", 0);
        map.insert("25", 4);
        map.insert("31", 5);
        map.insert("21", 6);
        assertEquals("{15: 0, 16: 2, 20: 1, 21: 6, 25: 4," +
                " 30: 3, 31: 5}", map.toString());

        map.remove("15");
        assertEquals("{16: 2, 20: 1, 21: 6, 25: 4, 30: 3," +
                " 31: 5}", map.toString());
    }

    /**
     * Tests a double left right rotation.
     */
    @Test
    public void testDoubleLR() {
        map.insert("30", 1);
        map.insert("20", 2);
        map.insert("31", 3);
        map.insert("16", 4);
        map.insert("25", 5);
        assertEquals("{16: 4, 20: 2, 25: 5, 30: 1, 31: 3}", map.toString());

        map.insert("26", 6);
        assertEquals("{16: 4, 20: 2, 25: 5, 26: 6, 30: 1," +
                " 31: 3}", map.toString());
    }

    /**
     * Tests a double left right remove.
     */
    @Test
    public void testDoubleLRRemove() {
        map.insert("30", 1);
        map.insert("20", 2);
        map.insert("31", 3);
        map.insert("16", 4);
        map.insert("25", 5);
        map.insert("35", 0);
        map.insert("26", 6);
        assertEquals("{16: 4, 20: 2, 25: 5, 26: 6, 30: 1," +
                " 31: 3, 35: 0}", map.toString());

        map.remove("35");
        assertEquals("{16: 4, 20: 2, 25: 5, 26: 6, 30: 1," +
                " 31: 3}", map.toString());
    }
}
