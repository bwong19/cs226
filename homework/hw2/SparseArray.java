/* Name: Brandon Wong
 * JHED: bwong19
 * Email: bwong19@jhu.edu
 * SparseArray.java
 */

package hw2;

import exceptions.IndexException;
import exceptions.LengthException;
import java.util.Iterator;


/**
 * SparseArray is built for arrays that have few elements that are
 * modified from the initial value. In such arrays, SparseArray uses
 * less space than a SimpleArray. Because SparseArray is a linked list,
 * it takes more time to access elements of the array, as finding an
 * element by index requires traversing the linked list.
 * @param <T> Element type.
 */
public class SparseArray<T> implements Array<T> {

    /**
     * The Node class stores the information of each array entry.
     * This information includes the data, the position of each
     * entry, and the next Node in the linked list.
     * @param <T> Element type.
     */
    private static final class Node<T> {
        T data;
        int position;
        Node<T> next;

        Node(T t, int pos, Node<T> n) {
            this.data = t;
            this.position = pos;
            this.next = n;
        }
    }

    // Stores first Node, which points to the rest of the linkes list.
    private Node<T> first;
    // Length of linked list.
    private int listLength;
    // Default value of array.
    private T defaultValue;
    // Length of whole array.
    private int length;
    

    
    /**
     * An Iterator that traverses the whole array (not just the
     * linked list). This allows the user to traverse through
     * the SparseArray just like a regular array.
     *
     */
    private class SparseArrayIterator implements Iterator<T> {        
        int current;

        @Override
        public boolean hasNext() {
            return (current < length - 1);
        }

        @Override
        public T next() {
            Node<T> n = SparseArray.this.find(current);
            T val;
            if (n != null) {
                val = n.data;
            }
            else {
                val = defaultValue;
            }
            current++;
            return val;
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
     * @throws LengthException if the user inputs an invalid starting length.
    */
    public SparseArray(int length, T defaultValue) throws LengthException {
        if (length <= 0) {
            throw new LengthException();
        }
        this.length = length;
        this.defaultValue = defaultValue;
    }

    /**
     * Adds node to end of list
     * @param t the value being stored.
     * @param pos the position in the array.
     */
    private void append(T t, int pos) {
        Node<T> cur = this.first;

        if (cur == null) {
            this.first = new Node<T>(t, pos, null);
            return;
        }

        // finds last element
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = new Node<T>(t, pos, null);
        this.listLength++;
    }
    
    @Override
    public int length() {
        return this.length;
    }

    /**
     * Finds the Node of a certain index.
     * @param index The index to search for.
     * @return the node of the given index if it exists, 0 otherwise.
     * @throws IndexException if given an invalid index.
     */
    private Node<T> find(int index) throws IndexException {
        if (index < 0 || index >= length) {
            throw new IndexException();
        }

        boolean found = false;
        Node<T> cur = this.first;
        while (cur != null) {
            if (cur.position == index) {
                found = true;
                break;
            }
            cur = cur.next;
        }

        if (!found) {
            return null;
        }
        return cur;
    }
    
    @Override
    public T get(int i) throws IndexException {
        if (i < 0 || i >= length) {
            throw new IndexException();
        }

        Node<T> n = this.find(i);
        if (n != null) {
            return n.data;
        }
        return this.defaultValue;
    }

    @Override
    public void put(int i, T t) throws IndexException {
        if (i < 0 || i >= length) {
            throw new IndexException();
        }
        
        if (t == this.defaultValue) {
            return;
        }
        
        Node<T> n = this.find(i);
        if (n != null) {
            n.data = t;
        }
        this.append(t, i);
    }
    
    /**
     * If the given node exists, this method removes it.
     * @param index The index to remove.
     * @throws IndexException if given an invalid index.
     */
    private void remove(int index) throws IndexException {
        if (index < 0 || index >= this.length) {
            throw new IndexException();
        }

        if (this.find(index) == null) {
            return;
        }
        
        Node<T> prev = this.find(index - 1);
        prev.next = prev.next.next;
    }

    @Override
    public Iterator<T> iterator() {
        return new SparseArrayIterator();
    }
}
