package hw8.tests;

import hw8.Map;
import hw8.HashMap;

import java.util.Iterator;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Testing implementation of HashMap.
 */
public class HashMapTest extends MapTest {
    @Override
    protected Map<String, Integer> createMap() {
        return new HashMap<>();
    }

    @Test
    @Override
    public void multiInsert() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        assertEquals(3, map.size());
        assertEquals("{c: 3, a: 1, b: 2}", map.toString());
    }

    @Test
    @Override
    public void testRemove() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        assertEquals(3, map.size());

        int r1 = map.remove("a");
        assertEquals(2, map.size());
        assertEquals("{c: 3, b: 2}", map.toString());
        assertEquals(r1, 1);

        int r2 = map.remove("b");
        assertEquals(1, map.size());
        assertEquals("{c: 3}", map.toString());
        assertEquals(r2, 2);

        int r3 = map.remove("c");
        assertEquals(0, map.size());
        assertEquals("{}", map.toString());
        assertEquals(r3, 3);
    }

    @Test
    @Override
    public void testPut() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        assertEquals(3, map.size());

        map.put("a", 11);
        assertEquals(3, map.size());
        assertEquals("{c: 3, a: 11, b: 2}", map.toString());

        map.put("b", 22);
        assertEquals(3, map.size());
        assertEquals("{c: 3, a: 11, b: 22}", map.toString());

        map.put("c", 33);
        assertEquals(3, map.size());
        assertEquals("{c: 33, a: 11, b: 22}", map.toString());
    }

    @Test
    @Override
    public void iteratorWorks() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);

        Iterator<String> it = map.iterator();

        assertTrue(it.hasNext());
        assertEquals(it.next(), "c");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "a");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "b");
        assertFalse(it.hasNext());
    }

    /**
     * Tests order of iterator after inserting extra elements.
     */
    @Test
    public void iteratorWorks2() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        map.insert("d", 4);

        map.insert("e", 5);
        map.insert("f", 6);

        Iterator<String> it = map.iterator();

        assertTrue(it.hasNext());
        assertEquals(it.next(), "a");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "b");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "c");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "d");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "e");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "f");

        assertFalse(it.hasNext());


        map.remove("c");

        Iterator<String> it2 = map.iterator();

        assertTrue(it2.hasNext());
        assertEquals(it2.next(), "a");
        assertTrue(it2.hasNext());
        assertEquals(it2.next(), "b");
        assertTrue(it2.hasNext());
        assertEquals(it2.next(), "d");
        assertTrue(it2.hasNext());
        assertEquals(it2.next(), "e");
        assertTrue(it2.hasNext());
        assertEquals(it2.next(), "f");

        assertFalse(it2.hasNext());
    }
}
