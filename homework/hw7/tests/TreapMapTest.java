package hw7.tests;

import hw7.OrderedMap;
import hw7.TreapMap;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementation of TreapMap.
 */
public class TreapMapTest extends OrderedMapTest {
    @Override
    protected OrderedMap<String, Integer> createMap() {
        return new TreapMap<>();
    }

    /**
     * Tests heap property.
     */
    @Test
    public void testHeap() {
        map.insert("30", 1);
        map.insert("25", 2);
        map.insert("31", 3);
        map.insert("20", 4);
        map.insert("26", 5);
        map.insert("35", 0);
        map.insert("21", 6);
        assertEquals("{20: 4, 21: 6, 25: 2, 26: 5, 30: 1," +
                " 31: 3, 35: 0}", map.toString());
        assertTrue(((TreapMap<String, Integer>) map).heapified("30"));
        assertTrue(((TreapMap<String, Integer>) map).heapified("25"));
        assertTrue(((TreapMap<String, Integer>) map).heapified("31"));
        assertTrue(((TreapMap<String, Integer>) map).heapified("20"));
        assertTrue(((TreapMap<String, Integer>) map).heapified("26"));
        assertTrue(((TreapMap<String, Integer>) map).heapified("35"));
        assertTrue(((TreapMap<String, Integer>) map).heapified("21"));
    }

    /**
     * Tests all removes.
     */
    @Test
    public void testMultiRemoves() {
        // multiple loops are used because priorities are randomized.
        // extra loops ensure that the randomness does not affect operations.
        for (int i = 0; i < 10000; ++i) {
            map.insert("30", 1);
            map.insert("25", 2);
            map.insert("31", 3);
            map.insert("20", 4);
            map.insert("26", 5);
            map.insert("35", 0);
            map.insert("21", 6);
            assertEquals("{20: 4, 21: 6, 25: 2, 26: 5, 30: 1," +
                    " 31: 3, 35: 0}", map.toString());

            map.remove("30");
            assertEquals("{20: 4, 21: 6, 25: 2, 26: 5, 31: 3," +
                    " 35: 0}", map.toString());
            map.remove("25");
            assertEquals("{20: 4, 21: 6, 26: 5, 31: 3, 35: 0}", map.toString());
            map.remove("31");
            assertEquals("{20: 4, 21: 6, 26: 5, 35: 0}", map.toString());
            map.remove("20");
            assertEquals("{21: 6, 26: 5, 35: 0}", map.toString());
            map.remove("26");
            assertEquals("{21: 6, 35: 0}", map.toString());
            map.remove("35");
            assertEquals("{21: 6}", map.toString());
            map.remove("21");
            assertEquals("{}", map.toString());
        }
    }

    /**
     * Tests right rotate with fixed priorities.
     */
    @Test
    public void testRight() {
        ((TreapMap<String, Integer>) map).insert("30", 30, 40);
        ((TreapMap<String, Integer>) map).insert("40", 40, 30);
        assertEquals("{40, _, 30}",
                ((TreapMap<String, Integer>) map).priorities("30"));

        ((TreapMap<String, Integer>) map).insert("20", 20, 50);
        assertEquals("{50, _, 40}",
                ((TreapMap<String, Integer>) map).priorities("20"));
        assertEquals("{40, _, 30}",
                ((TreapMap<String, Integer>) map).priorities("30"));
    }

    /**
     * Tests left rotate with fixed priorities.
     */
    @Test
    public void testLeft() {
        ((TreapMap<String, Integer>) map).insert("30", 30, 40);
        ((TreapMap<String, Integer>) map).insert("20", 20, 30);
        assertEquals("{40, 30, _}",
                ((TreapMap<String, Integer>) map).priorities("30"));

        ((TreapMap<String, Integer>) map).insert("40", 40, 50);
        assertEquals("{50, 40, _}",
                ((TreapMap<String, Integer>) map).priorities("40"));
        assertEquals("{40, 30, _}",
                ((TreapMap<String, Integer>) map).priorities("30"));
    }
}
