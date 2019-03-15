package hw5.tests;

import exceptions.EmptyException;
import exceptions.PositionException;
import hw5.List;
import hw5.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementations of the List interface.
 *
 * The tests defined here apply to all implementations of the List
 * interface. However, they cannot be run directly as we don't know
 * which implementation to test or how to create an instance of it.
 *
 * The solution is to define a "template method" called createList()
 * that subclasses of this test override. The LinkedListTest.java class,
 * for example, creates a suitable LinkedList instance to be tested.
 *
 * Note that we (somewhat arbitrarily) choose to test lists of strings.
 * We could have gone for lists of integers or lists of whatever, but
 * strings seem convenient in any case: You can pick strings in such a
 * way as to make your test cases more readable.
 */
public abstract class ListTest {
    /**
     * List to do operations on.
     */
    private List<String> list;

    /**
     * Creates a list according to the type of list to test.
     * @return the newly created list.
     */
    protected abstract List<String> createList();

    /**
     * Setup for every test.
     */
    @Before
    public void setupListTests() {
        list = this.createList();
    }

    /**
     * Ensures an empty list is empty.
     */
    @Test
    public void newListEmpty() {
        assertTrue(list.empty());
        assertEquals(0, list.length());
        assertEquals("[]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(0, c);
    }

    /**
     * Ensures an empty front cannot be accessed.
     */
    @Test(expected = EmptyException.class)
    public void newListNoFront() {
        Position<String> p = list.front();
    }

    /**
     * Ensures an empty back cannot be accessed.
     */
    @Test(expected = EmptyException.class)
    public void newListNoBack() {
        Position<String> p = list.back();
    }

    /**
     * Tests functionality of insertFront(t).
     */
    @Test
    public void insertFrontWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        assertFalse(list.empty());
        assertEquals(3, list.length());
        assertEquals("[Three, Two, One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }

    /**
     * Tests functionality of insertBack().
     */
    @Test
    public void insertBackWorks() {
        list.insertBack("One");
        list.insertBack("Two");
        list.insertBack("Three");

        assertFalse(list.empty());
        assertEquals(3, list.length());
        assertEquals("[One, Two, Three]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }

    /**
     * Ensures front and back stay the same.
     */
    @Test
    public void insertFrontBackConsistent() {
        Position<String> f = list.insertFront("Front");
        assertEquals("Front", f.get());
        Position<String> b = list.insertBack("Back");
        assertEquals("Back", b.get());

        assertNotEquals(f, b);

        assertTrue(list.first(f));
        assertTrue(list.last(b));

        Position<String> x;

        x = list.front();
        assertEquals(f, x);

        x = list.back();
        assertEquals(b, x);
    }

    /**
     * Tests functionality of removeFront().
     */
    @Test
    public void removeFrontWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");
        list.removeFront();
        list.removeFront();

        assertFalse(list.empty());
        assertEquals(1, list.length());
        assertEquals("[One]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(1, c);
    }

    /**
     * Tests functionality of removeBack().
     */
    @Test
    public void removeBackWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");
        list.removeBack();
        list.removeBack();

        assertFalse(list.empty());
        assertEquals(1, list.length());
        assertEquals("[Three]", list.toString());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(1, c);
    }

    // Student-created tests

    /**
     * Tests functionality of next(p).
     */
    @Test
    public void nextWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        assertEquals(3, list.length());
        Position<String> p = list.front();
        assertEquals("Three", p.get());
        p = list.next(p);
        assertEquals("Two", p.get());
        p = list.next(p);
        assertEquals("One", p.get());

        assertFalse(list.first(p));
    }

    /**
     * Tests functionality of previous(p).
     */
    @Test
    public void previousWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        assertEquals(3, list.length());
        Position<String> p = list.back();
        assertEquals("One", p.get());
        p = list.previous(p);
        assertEquals("Two", p.get());
        p = list.previous(p);
        assertEquals("Three", p.get());

        assertFalse(list.last(p));
    }

    /**
     * Tests functionality of forward iterator.
     */
    @Test
    public void forwardIteratorWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        Iterator<String> it = list.forward();

        assertTrue(it.hasNext());
        assertEquals(it.next(), "Three");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "Two");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "One");
        assertFalse(it.hasNext());
    }

    /**
     * Tests functionality of backward iterator.
     */
    @Test
    public void backwardIteratorWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        Iterator<String> it = list.backward();

        assertTrue(it.hasNext());
        assertEquals(it.next(), "One");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "Two");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "Three");
        assertFalse(it.hasNext());
    }

    /**
     * Tests remove(p).
     */
    @Test
    public void removeWorks() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        assertEquals("[Three, Two, One]", list.toString());
        Position<String> p = list.next(list.front());
        list.remove(p);
        assertEquals("[Three, One]", list.toString());
        assertEquals(2, list.length());
        list.remove(list.front());
        assertEquals("[One]", list.toString());
        assertEquals(1, list.length());

        assertEquals(list.front(), list.back());

        list.remove(list.front());
        assertTrue(list.empty());
        assertEquals(0, list.length());
    }

    /**
     * Test first(p) and last(p).
     */
    @Test
    public void firstLast() {
        list.insertFront("One");

        assertEquals(list.front(), list.back());
        assertTrue(list.first(list.front()));
        assertTrue(list.last(list.front()));

        list.insertFront("Two");
        assertEquals("Two", list.front().get());
        assertEquals("One", list.back().get());
        assertFalse(list.first(list.back()));
        assertTrue(list.first(list.front()));
        assertFalse(list.last(list.front()));
        assertTrue(list.last(list.back()));

        list.insertFront("Three");
        Position<String> p = list.next(list.front());
        assertEquals("Two", p.get());
        assertFalse(list.first(p));
        assertFalse(list.last(p));
    }

    /**
     * Test insertBefore(p, t).
     */
    @Test
    public void insertBeforeWorks() {
        list.insertFront("One");
        list.insertFront("Three");

        Position<String> p = list.insertBefore(list.back(), "Two");
        assertEquals("Two", p.get());
        assertEquals("[Three, Two, One]", list.toString());
        assertEquals(3, list.length());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }

    /**
     * Test insertAfter(p, t).
     */
    @Test
    public void insertAfterWorks() {
        list.insertFront("One");
        list.insertFront("Three");

        Position<String> p = list.insertAfter(list.front(), "Two");
        assertEquals("Two", p.get());
        assertEquals("[Three, Two, One]", list.toString());
        assertEquals(3, list.length());

        int c = 0;
        for (String s: list) {
            c++;
        }
        assertEquals(3, c);
    }

    /**
     * Ensures invalid positions are caught for insertBefore(p, t).
     */
    @Test(expected = PositionException.class)
    public void insertBeforeInvalid() {
        list.insertFront("One");
        list.insertFront("Three");

        list.insertBefore(null, "Two");
    }

    /**
     * Ensures invalid positions are caught for insertAfter(p, t).
     */
    @Test(expected = PositionException.class)
    public void insertAfterInvalid() {
        list.insertFront("One");
        list.insertFront("Three");

        list.insertAfter(null, "Two");
    }

    /**
     * Ensures invalid positions do not pass for next(p).
     */
    @Test(expected = PositionException.class)
    public void nextInvalid() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        Position<String> p = list.back();
        p = list.next(p);
    }

    /**
     * Ensures invalid positions do not pass for previous(p).
     */
    @Test(expected = PositionException.class)
    public void previousInvalid() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        Position<String> p = list.front();
        p = list.previous(p);
    }

    /**
     * Ensures invalid positions do not pass for previous(p).
     */
    @Test(expected = PositionException.class)
    public void previousInvalid3() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        Position<String> p = list.front();
        p = list.previous(p);
        p = list.previous(p);
        p = list.previous(p);
    }

    /**
     * Ensures first() doesn't work for an invalid Position.
     */
    @Test(expected = PositionException.class)
    public void firstInvalid() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        Position<String> p = list.front();
        assertEquals("Three", p.get());
        assertTrue(list.first(p));
        assertFalse(list.first(list.back()));
        list.first(null);
    }

    /**
     * Ensures last() doesn't work for an invalid Position.
     */
    @Test(expected = PositionException.class)
    public void lastInvalid() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        Position<String> p = list.back();
        assertEquals("One", p.get());
        assertTrue(list.last(p));
        assertFalse(list.last(list.front()));
        list.last(null);
    }


    /**
     * Cannot removeFront() from an empty list.
     */
    @Test(expected = EmptyException.class)
    public void removeFrontEmpty() {
        list.removeFront();
    }

    /**
     * Cannot removeBack() from an empty list.
     */
    @Test(expected = EmptyException.class)
    public void removeBackEmpty() {
        list.removeBack();
    }

    /**
     * Cannot remove(p) an invalid position.
     */
    @Test(expected = PositionException.class)
    public void removeInvalid() {
        list.insertFront("One");
        list.insertFront("Two");
        list.insertFront("Three");

        assertEquals(3, list.length());
        list.remove(null);
    }
}
