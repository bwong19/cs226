// Name: Brandon Wong
// JHED: bwong19
// Email: bwong19@jhu.edu
// ArrayDeque226.java

package hw4;

import exceptions.EmptyException;
import hw2.SimpleArray;

/**
 * An implementation of Deque226 using an Array.
 * @param <T> The type of the queue
 */
public class ArrayDeque226<T> implements Deque226<T> {

    private SimpleArray<T> arr;
    private int length;
    private int front; // index of front
    private int back; // index of back

    /**
     * Constructor to create a new ArrayDeque226.
     */
    public ArrayDeque226() {
        arr = new SimpleArray<>(1, null);
    }

    @Override
    public boolean empty() {
        return this.length == 0;
    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public T front() throws EmptyException {
        return this.arr.get(front);
    }

    @Override
    public T back() throws EmptyException {
        return this.arr.get(back);
    }

    /**
     * Doubles the length of arr and copies all elements.
     */
    private void doubleLength() {
        SimpleArray<T> newArr = new SimpleArray<>(2 * this.arr.length(), null);

        // copies elements of the old array into the new one
        for (int i = 0; i < length; ++i) {
            int index = (this.front + i) % this.arr.length();
            newArr.put(index, this.arr.get(index));
        }

        // resets front and back of new array
        arr = newArr;
        front = 0;
        back = this.length - 1;
    }

    @Override
    public void insertFront(T t) {
        if (this.length >= this.arr.length()) {
            doubleLength();
        }

        int index = (this.front - 1) % this.arr.length();
        this.arr.put(index, t);
        front = index;
    }

    @Override
    public void insertBack(T t) {
        if (this.length >= this.arr.length()) {
            doubleLength();
        }

        int index = (this.back + 1) % this.arr.length();
        this.arr.put(index, t);
        back = index;
    }

    @Override
    public void removeFront() throws EmptyException {
        if (length == 0) {
            throw new EmptyException();
        }

        this.arr.put(front, null);
        front = (front + 1) % this.arr.length();
        length--;
    }

    @Override
    public void removeBack() throws EmptyException {
        if (length == 0) {
            throw new EmptyException();
        }

        this.arr.put(back, null);
        back = (back - 1) % this.arr.length();
        length--;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < length; ++i) {
            int index = (this.front + i) % this.arr.length();
            sb.append(this.arr.get(index).toString());
            if (i < length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
