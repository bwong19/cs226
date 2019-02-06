/* TODO - Add your name, JHED, and email.
 * SparseArray.java
 */

package hw2;

import exceptions.IndexException;

import java.util.Iterator;


/**
 * TODO - Write a good Javadoc description for potential clients.
 * @param <T> Element type.
 */
public class SparseArray<T> implements Array<T> {

    // TODO - You'll need this to implement the iterator() method
    private class SparseArrayIterator implements Iterator<T> {
        @Override
        public boolean hasNext() {
            // TODO
            return false;
        }

        @Override
        public T next() {
            // TODO
            return null;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * An array that is meant to be filled primarily with a default value
     * that is not going to change - with the benefit of that default
     * value not being stored numerous times as opposed to once.
     * @param length The number of indexes the array should have.
     * @param defaultValue The default value for the array.
    */
    public SparseArray(int length, T defaultValue) {
        // TODO
    }

    @Override
    public int length() {
        // TODO
        return 0;
    }

    @Override
    public T get(int i) throws IndexException {
        // TODO
        return null;
    }

    @Override
    public void put(int i, T t) throws IndexException {
        // TODO
    }

    @Override
    public Iterator<T> iterator() {
        // TODO
        return new SparseArrayIterator();
    }
}
