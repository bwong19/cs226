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


public abstract class Deque226Test {

    // Subclasses implement this to return the instance of the Deque226
    // they are testing.
    protected abstract Deque226<String> createUnit();

    // The unit being tested
    private Deque226<String> dequeue;

    @Before
    public void setupDequeue() {
        this.dequeue = createUnit();
    }

    @Test
    public void newDequeueEmpty() {
       assertTrue(dequeue.empty());
       assertEquals(0, dequeue.length());
    }

    @Test(expected=EmptyException.class)
    public void removeFrontOnEmpty() {
        dequeue.removeFront();
    }

    // TODO - Add more tests!
}
