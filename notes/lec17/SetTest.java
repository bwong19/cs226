import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Testing implementations of the Set interface.
 */
public abstract class SetTest {
    private Set<String> unit;

    protected abstract Set<String> createUnit();

    @Before
    public void setupTests() {
        unit = this.createUnit();
    }

    @Test
    public void newSetEmpty() {
        assertEquals(0, unit.size());
    }

    @Test
    public void newSetEmptyIterator() {
        int c = 0;
        for (String s: unit) {
            c++;
        }
        assertEquals(0, c);
    }

    @Test
    public void insertNotEmpty() {
        unit.insert("Magda");
        assertEquals(1, unit.size());
    }

    @Test
    public void insertDuplicateSizeConsistent() {
        // TODO
    }

    @Test
    public void insertHas() {
        // TODO
    }

    @Test
    public void insertRemoveHas() {
        // TODO
    }

    @Test
    public void manyInsertOneRemove() {
        // TODO
    }

    @Test
    public void insertManySizeConsistent() {
        // TODO
    }

    @Test
    public void iteratorWorks() {
        String[] data = {"Peter", "Paul", "Mary", "Beverly"};
        for (String d: data) {
            unit.insert(d);
        }
        for (String s: unit) {
            int count = 0;
            for (String d: data) {
                if (s.equals(d)) {
                    count += 1;
                }
            }
            assertEquals(1, count);
        }
    }
}
