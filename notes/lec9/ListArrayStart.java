import java.util.Iterator;

/**
 * Array implementation using a linked list.
 *
 * The sole purpose of this (otherwise useless) implementation is
 * to show that we <b>can</b> implement a given interface in many
 * different ways. (Also it's a stepping stone for the SparseArray
 * homework.)
 *
 * This version is incomplete - for use as a class exercise.
 *
 * @param <T> Element type.
 */
public class ListArray<T> implements Array<T> {
    // A nested Node class to build our linked list out of. We use a
    // nested class (instead of an inner class) here since we don't
    // need access to the ListArray object we're part of. Also, since
    // Node is already private to ListArray, there's no need to make
    // the fields of Node private. (That would be like trying to hide
    // them from ourselves.)
    private static final class Node<T> {
        T data;
        Node<T> next;

        Node(T t, Node<T> n) {
            this.data = t;
            this.next = n;
        }
    }

    // The not-so-obvious representation of our abstract Array: A
    // linked list with "length" nodes instead of a basic array of
    // "length" slots. We also keep an explicit length so we don't
    // have to re-compute it every time.

    /** The first node in our list. */
    private Node<T> first;
    /** How many nodes are in the list. */
    private int length;

    /**
     * Create a new ListArray.
     *
     * @param n Length of array, must be &gt; 0.
     * @param t Initial value to store in each slot.
     * @throws LengthException if n &le; 0.
     */
    public ListArray(int n, T t) throws LengthException {
        if (n <= 0) {
            throw new LengthException();
        }

        // TODO:
        // Initialize all positions as we promise in the specification.
        // Unlike in SimpleArray we cannot avoid the initialization even
        // if t == null since the nodes still have to be created. On the
        // upside we don't need an awkward cast anywhere.
        for (int i = 0; i < n; ++i) {
            this.prepend(t);
        }

        // Remember to set the length!
        this.length = n;
    }

    // Insert a node at the beginning of the linked list.
    private void prepend(T t) {
        Node<T> n = new Node<>(t, this.first);
        this.first = n;
    }

    // Find the node for a given index. We enforce the precondition
    // on index here so we don't have to duplicate the check in get()
    // and put() below.
    private Node<T> find(int index) throws IndexException {
        if (index < 0 || index >= this.length) {
            throw new IndexException();
        }

        // TODO: fill in the body of this method, update the return value
        Node<T> cur = this.first;
        for (int i = 0; i < index; ++i) {
            cur = cur.next;
        }
        
        return cur;
    }

    @Override
    public T get(int i) throws IndexException {
        
        // TODO: fill in the body of this method, update the return value
        return this.find(i).data;
    }

    @Override
    public void put(T t, int i) throws IndexException {

        // TODO: fill in the body of this method, update the return value
        this.find(i).data = t;
    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    /** Count the number of occurrences of a value in the ListArray.
     *  @param t the value to count
     *  @return the number of occurrences of t
     */
    public int count(T t) {
        int cnt = 0;

        // TODO: fill in the body of this method
        for (int i = 0; i < this.length; ++i) {
            if (this.data == t) {
                cnt++;
            }
        }
        
        return cnt;
    }

    // An iterator to traverse the array from front to back. Note
    // that we use an inner class here (not a nested class) so we
    // can access the outer object's "this" reference. If we didn't
    // do that, we'd have to pass the outer object (or at least the
    // first node) to the iterator's constructor.
    private final class ArrayIterator implements Iterator<T> {
        // Current position in the linked list.
        Node<T> current;

        ArrayIterator() {
            this.current = ListArray.this.first;
        }

        @Override
        public T next() {

            // TODO: fill in the body of this method, update the return value
            T t = this.current.data;
            this.current = this.current.next;
            
            return t;
        }

        @Override
        public boolean hasNext() {

            // TODO
            return this.current.next.data;
        }
        
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (Node<T> n = this.first; n != null; n = n.next) {
            s.append(n.data);
            if (n.next != null) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }
}
