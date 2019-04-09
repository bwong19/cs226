package hw7.tests;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementations of OrderedMaps only.
 */
public abstract class OrderedMapTest extends MapTest {
    /**
     * Tests order of insert.
     */
    @Test
    public void orderedInsert() {
        map.insert("e", 5);
        map.insert("d", 4);
        map.insert("b", 2);
        map.insert("f", 6);
        map.insert("a", 1);
        map.insert("c", 3);
        assertEquals(6, map.size());
        assertEquals("{a: 1, b: 2, c: 3, d: 4, e: 5, f: 6}", map.toString());
    }

    @Test
    @Override
    public void iteratorWorks() {
        map.insert("e", 5);
        map.insert("d", 4);
        map.insert("b", 2);
        map.insert("f", 6);
        map.insert("a", 1);
        map.insert("c", 3);
        assertEquals(6, map.size());

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
    }
}
