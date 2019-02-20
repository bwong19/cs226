/* MeasuredArray.java */

package hw3;

import exceptions.IndexException;
import hw2.SimpleArray;

/**
 * An Array that is able to report the number of accesses and mutations,
 * as well as reset those statistics.
 * @param <T> The type of the array.
 */
public class MeasuredArray<T> extends SimpleArray<T> implements Measured<T> {

    // integer variables that keep track of the number of accesses and mutations
    private int accesses;
    private int mutations;
    
    /**
     * Constructor for a MeasuredArray that calls the SimpleArray constructor.
     * @param n The size of the array.
     * @param t The initial value to set every object to in the array..
     */
    public MeasuredArray(int n, T t) {
        super(n, t);
        accesses = 0;
        mutations = 0;
    }

    @Override
    public int length() {
        accesses++;
        return super.length();
    }

    @Override
    public T get(int i) {
        T t = super.get(i); // stops execution if exception thrown
        accesses++; // only increments if get(i) doesn't throw an exception
        return t;
    }

    @Override
    public void put(int i, T t) throws IndexException {
        super.put(i, t);
        mutations++;
    }

    @Override
    public void reset() {
        accesses = 0;
        mutations = 0;
    }

    @Override
    public int accesses() {
        return accesses;
    }

    @Override
    public int mutations() {
        return mutations;
    }

    @Override
    public int count(T t) {
        int count = 0;
        for (int i = 0; i < super.length(); ++i) {
            // each time get(i) is called, automatically increments
            if (t == this.get(i)) {
                count++;
            }
        }
        return count;
    }
}
