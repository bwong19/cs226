import org.junit.Before;
import org.junit.Test;

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
    private List<String> list;

    protected abstract List<String> createList();

    @Before
    public void setupListTests() {
        list = this.createList();
    }

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

    @Test(expected=EmptyException.class)
    public void newListNoFront() {
        Position<String> p = list.front();
    }

    @Test(expected=EmptyException.class)
    public void newListNoBack() {
        Position<String> p = list.back();
    }

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

    // TODO You need to add *many* more test cases here, ideally before you
    // even start working on LinkedList!
}
