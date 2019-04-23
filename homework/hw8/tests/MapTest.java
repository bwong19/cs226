package hw8.tests;

import hw8.Map;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Testing various implementations of the Map interface.
 */
public abstract class MapTest {
    /**
     * Instance of map to be tested.
     */
    protected Map<String, Integer> map;

    /**
     * Creates a map according to the type of map to test.
     * @return the newly created map.
     */
    protected abstract Map<String, Integer> createMap();

    /**
     * Sets up a map before each test.
     */
    @Before
    public void setupMapTests() {
        map = this.createMap();
    }

    /**
     * Tests size of empty map.
     */
    @Test
    public void newMapEmpty() {
        assertEquals(0, map.size());
        assertEquals("{}", map.toString());
    }

    /**
     * Tests only one insert.
     */
    @Test
    public void singleInsert() {
        map.insert("test", 10);
        assertEquals(1, map.size());
        assertEquals("{test: 10}", map.toString());
    }

    /**
     * Tests multiple inserts.
     */
    @Test
    public void multiInsert() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        assertEquals(3, map.size());
        assertEquals("{a: 1, b: 2, c: 3}", map.toString());
    }

    /**
     * Tests single remove.
     */
    @Test
    public void singleRemove() {
        map.insert("a", 1);
        assertEquals(1, map.size());

        map.remove("a");
        assertEquals(0, map.size());
        assertEquals("{}", map.toString());
    }

    /**
     * Tests remove function.
     */
    @Test
    public void testRemove() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        assertEquals(3, map.size());

        map.remove("a");
        assertEquals(2, map.size());
        assertEquals("{b: 2, c: 3}", map.toString());

        map.remove("b");
        assertEquals(1, map.size());
        assertEquals("{c: 3}", map.toString());

        map.remove("c");
        assertEquals(0, map.size());
        assertEquals("{}", map.toString());
    }

    /**
     * Tests put function.
     */
    @Test
    public void testPut() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        assertEquals(3, map.size());

        map.put("a", 11);
        assertEquals(3, map.size());
        assertEquals("{a: 11, b: 2, c: 3}", map.toString());

        map.put("b", 22);
        assertEquals(3, map.size());
        assertEquals("{a: 11, b: 22, c: 3}", map.toString());

        map.put("c", 33);
        assertEquals(3, map.size());
        assertEquals("{a: 11, b: 22, c: 33}", map.toString());
    }

    /**
     * Tests get function.
     */
    @Test
    public void testGet() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        assertEquals(3, map.size());

        assertEquals(1, (int) map.get("a"));
        assertEquals(2, (int) map.get("b"));
        assertEquals(3, (int) map.get("c"));
    }

    /**
     * Test has function.
     */
    @Test
    public void testHas() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        assertEquals(3, map.size());

        assertTrue(map.has("a"));
        assertTrue(map.has("b"));
        assertTrue(map.has("c"));
    }

    /**
     * Tests Iterator hasNext and next functions.
     */
    @Test
    public void iteratorWorks() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);

        Iterator<String> it = map.iterator();

        assertTrue(it.hasNext());
        assertEquals(it.next(), "a");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "b");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "c");
        assertFalse(it.hasNext());
    }

    // EXCEPTION TESTING

    /**
     * Tests duplicate insert.
     */
    @Test(expected = IllegalArgumentException.class)
    public void dupInsert() {
        map.insert("test", 10);
        assertEquals(1, map.size());
        map.insert("test", 100);
    }

    /**
     * Tests removing an invalid key.
     */
    @Test (expected = IllegalArgumentException.class)
    public void invalidRemove() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        assertEquals(3, map.size());

        map.remove("d");
    }

    /**
     * Tests putting an invalid key.
     */
    @Test (expected = IllegalArgumentException.class)
    public void invalidPut() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        assertEquals(3, map.size());

        map.put("d", 4);
    }

    /**
     * Tests getting an invalid key.
     */
    @Test (expected = IllegalArgumentException.class)
    public void invalidGet() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);
        assertEquals(3, map.size());

        map.get("d");
    }

    /**
     * Ensures Iterator cannot get an invalid element.
     */
    @Test (expected = NoSuchElementException.class)
    public void iteratorInvalidNext() {
        map.insert("a", 1);
        map.insert("b", 2);
        map.insert("c", 3);

        Iterator<String> it = map.iterator();

        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
        it.next();
    }
}
