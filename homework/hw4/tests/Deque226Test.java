// Name: Brandon Wong
// JHED: bwong19
// Email: bwong19@jhu.edu
// Deque226Test.java

package hw4.tests;

import exceptions.EmptyException;
import hw4.Deque226;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * JUnit class for testing both ArrayDeque and ListDeque.
 */
public abstract class Deque226Test {

    /**
     * The unit being tested.
     */
    private Deque226<String> dequeue;

    /**
     * Subclasses implement this to return the instance of the Deque226
     * they are testing.
     * @return deque that is created.
     */
    protected abstract Deque226<String> createUnit();

    /**
     * Creates new deque before each test.
     */
    @Before
    public void setupDequeue() {
        this.dequeue = createUnit();
    }

    /**
     * Tests size of new deque.
     */
    @Test
    public void newDequeueEmpty() {
        assertTrue(dequeue.empty());
        assertEquals(0, dequeue.length());
    }

    /**
     * Ensures removeFront does not work on empty deque.
     */
    @Test(expected = EmptyException.class)
    public void removeFrontOnEmpty() {
        dequeue.removeFront();
    }

    /**
     * Ensures removeBack does not work on empty deque.
     */
    @Test(expected = EmptyException.class)
    public void removeBackOnEmpty() {
        dequeue.removeBack();
    }

    /**
     * Tests the insertion of one element from front.
     */
    @Test
    public void insertFrontOne() {
        dequeue.insertFront("hello");
        assertEquals(1, dequeue.length());
        assertEquals(dequeue.front(), "hello");
    }

    /**
     * Tests the insertion of one element from back.
     */
    @Test
    public void insertBackOne() {
        dequeue.insertBack("goodbye");
        assertEquals(1, dequeue.length());
        assertEquals(dequeue.back(), "goodbye");
    }

    /**
     * Tests the insertion of two elements from front.
     */
    @Test
    public void insertFrontTwo() {
        dequeue.insertFront("hello");
        dequeue.insertFront("world");
        assertEquals(2, dequeue.length());
        assertEquals(dequeue.front(), "world");
        assertEquals(dequeue.back(), "hello");
    }

    /**
     * Tests toString() method.
     */
    @Test
    public void testToString() {
        dequeue.insertBack("hello");
        dequeue.insertBack("world");
        dequeue.insertBack("every");
        dequeue.insertBack("one");
        assertEquals(dequeue.toString(), "[hello, world, every, one]");
    }

    /**
     * Tests the insertion of multiple elements from front.
     */
    @Test
    public void insertFrontMulti() {
        dequeue.insertFront("1");
        dequeue.insertFront("2");
        dequeue.insertFront("3");
        dequeue.insertFront("4");
        dequeue.insertFront("5");
        dequeue.insertFront("6");
        dequeue.insertFront("7");
        dequeue.insertFront("8");
        dequeue.insertFront("9");

        assertEquals(dequeue.toString(), "[9, 8, 7, 6, 5, 4, 3, 2, 1]");

        assertEquals(9, dequeue.length());
        assertEquals(dequeue.front(), "9");
        assertEquals(dequeue.back(), "1");
    }

    /**
     * Tests the insertion of multiple elements from back.
     */
    @Test
    public void insertBackMulti() {
        dequeue.insertBack("1");
        dequeue.insertBack("2");
        dequeue.insertBack("3");
        dequeue.insertBack("4");
        dequeue.insertBack("5");
        dequeue.insertBack("6");
        dequeue.insertBack("7");
        dequeue.insertBack("8");
        dequeue.insertBack("9");

        assertEquals(dequeue.toString(), "[1, 2, 3, 4, 5, 6, 7, 8, 9]");

        assertEquals(9, dequeue.length());
        assertEquals(dequeue.front(), "1");
        assertEquals(dequeue.back(), "9");
    }

    /**
     * Tests the removal of one element from front.
     */
    @Test
    public void removeFrontOne() {
        dequeue.insertFront("hello");
        dequeue.insertFront("world");

        dequeue.removeFront();
        assertEquals(dequeue.front(), "hello");
        assertEquals(dequeue.back(), "hello");
    }

    /**
     * Tests the removal of one element from back.
     */
    @Test
    public void removeBackOne() {
        dequeue.insertBack("hello");
        dequeue.insertBack("world");

        dequeue.removeBack();
        assertEquals(dequeue.front(), "hello");
        assertEquals(dequeue.back(), "hello");
    }

    /**
     * Tests the removal of multiple elements from front.
     */
    @Test
    public void removeFrontMulti() {
        dequeue.insertFront("one");
        dequeue.insertFront("two");
        dequeue.insertFront("three");
        dequeue.insertFront("four");
        dequeue.insertFront("five");

        dequeue.removeFront();
        dequeue.removeFront();
        dequeue.removeFront();
        assertEquals(dequeue.front(), "two");
        assertEquals(dequeue.back(), "one");
    }

    /**
     * Tests the removal of multiple elements from back.
     */
    @Test
    public void removeBackMulti() {
        dequeue.insertBack("one");
        dequeue.insertBack("two");
        dequeue.insertBack("three");
        dequeue.insertBack("four");
        dequeue.insertBack("five");

        dequeue.removeBack();
        dequeue.removeBack();
        dequeue.removeBack();
        assertEquals(dequeue.front(), "one");
        assertEquals(dequeue.back(), "two");
    }
}
