/* MeasuredArrayTest.java */


package hw3;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MeasuredArrayTest {

    private static final int SIZE = 20;
    private static final String VAL = "test";

    private MeasuredArray<String> array;

    @Before
    public void createArray() {
        this.array = new MeasuredArray<>(SIZE, VAL);
    }

    @Test
    public void newArrayZeroMutations() {
        assertEquals(0, array.mutations());
    }

    // TODO - add more tests
}
