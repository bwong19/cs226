package hw1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/** checkstyle.
 *
 */
public class FlexibleCounterTest {

    private static final int START = 1;
    private static final int DELTA = 2;
    private ResetableCounter unit;

    /** checkstyle.
     */
    @Before
    public void createUnit() {
        unit = new FlexibleCounter(START, DELTA);
    }

    /** checkstyle.
     */
    @Test
    public void initialValueZero() {
        assertEquals(0, unit.value());
    }

    /** checkstyle.
     */
    @Test
    public void upWorks() {
        unit.up();
        assertEquals(1, unit.value());
        unit.up();
        assertEquals(2, unit.value());
    }

    /** checkstyle.
     */
    @Test
    public void downWorks() {
        unit.down();
        assertEquals(-1, unit.value());
        unit.down();
        assertEquals(-2, unit.value());
    }

    /** checkstyle.
     */
    @Test
    public void upAndDown() {
        for (int i = 0; i < 10; i++) {
            unit.up();
        }
        assertEquals(10, unit.value());
        for (int i = 0; i < 5; i++) {
            unit.down();
        }
        assertEquals(5, unit.value());
    }

    /** checkstyle.
     */
    @Test
    public void canReset() {
        for (int i = 0; i < 15; i++) {
            unit.up();
        }
        assertNotEquals(0, unit.value());
        unit.reset();
        assertEquals(0, unit.value());
    }

    /** checkstyle.
     */
    @Test(expected = IllegalArgumentException.class)
    public void somethingBad() {
        // this code should throw RuntimeException
        throw new IllegalArgumentException();
    }
}
