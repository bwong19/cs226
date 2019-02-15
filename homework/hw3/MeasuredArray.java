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

    /**
     * Constructor for a MeasuredArray that calls the SimpleArray constructor.
     * @param n The size of the array.
     * @param t The initial value to set every object to in the array..
     */
    public MeasuredArray(int n, T t) {
        super(n, t);
        // TODO
    }

    @Override
    public int length() {
        // TODO
        return -1;
    }

    @Override
    public T get(int i) {
        // TODO
        return null;
    }

    @Override
    public void put(int i, T t) throws IndexException {
        // TODO
    }

    @Override
    public void reset() {
        // TODO
    }

    @Override
    public int accesses() {
        // TODO
        return -1;
    }

    @Override
    public int mutations() {
        // TODO
        return -1;
    }

    @Override
    public int count(T t) {
        // TODO
        return 0;
    }
}
