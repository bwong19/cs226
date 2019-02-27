import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic position-based linked list.
 *
 * This is only a partial solution!
 *
 * Here we use the "standard" representation of a doubly-linked list with
 * head (front, first) and tail (back, last) pointers. The empty list and
 * the ends of the list are marked with null pointers. As such there are
 * a *lot* of special cases to take care of in the code.
 *
 * Your first job is to convert this implementation to a sentinel-based
 * doubly linked list. That will require adding dummy first and last nodes
 * to the implementation, "guarding" the beginning and end. The data in 
 * those nodes should be null, but their purpose is to eliminate the need
 * for special cases, particularly for the insert and remove methods.
 *
 * After you have written and tested this code to incorporate sentinel nodes 
 * (don't forget the Iterator!), add the missing method definitions.
 *
 * @param <T> Element type.
 */
public class LinkedList<T> implements List<T> {

    private static final class Node<T> implements Position<T> {
        // The usual doubly-linked list stuff.
        Node<T> next;   // reference to the Node after this
        Node<T> prev;   // reference to the Node before this
        T data;

        // List that created this node, to validate positions.
        List<T> owner;

        @Override
        public T get() {
            return this.data;
        }

        @Override
        public void put(T t) {
            this.data = t;
        }
    }

    /** This iterator can be used to create either a forward
        iterator, or a backwards one.
    */
    private final class ListIterator implements Iterator<T> {
        Node<T> current;
        boolean forward;

        ListIterator(boolean f) {
            this.forward = f;
            if (this.forward) {
                this.current = LinkedList.this.head;
            } else {
                this.current = LinkedList.this.tail;
            }
        }

        @Override
        public T next() throws NoSuchElementException {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            T t = this.current.get();
            if (this.forward) {
                this.current = this.current.next;
            } else {
                this.current = this.current.prev;
            }
            return t;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }
    }

    /* ** LinkedList instance variables are declared here! ** */

//    private Node<T> head;   // reference to the first node
//    private Node<T> tail;   // reference to the last node
    private Node<T> sentinelHead;
    private Node<T> sentinelTail;
    private int length;     // how many nodes in the list

    /**
     * Create an empty list.
     */
    public LinkedList() {
        // TODO: create sentinel head and tail nodes!
        sentinelHead = new Node<T>();
        sentinelTail = new Node<T>();
    }

    // Convert a position back into a node. Guards against null positions,
    // positions from other data structures, and positions that belong to
    // other LinkedList objects. That about covers it?
    private Node<T> convert(Position<T> p) throws PositionException {
        try {
            Node<T> n = (Node<T>) p;
            if (n.owner != this) {
                throw new PositionException();
            }
            return n;
        } catch (NullPointerException | ClassCastException e) {
            throw new PositionException();
        }
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
    public boolean first(Position<T> p) throws PositionException {
        Node<T> n = this.convert(p);
        return this.sentinelHead.next == n;
    }

    @Override
    public boolean last(Position<T> p) throws PositionException {
        Node<T> n = this.convert(p);
        return this.sentinelTail.prev == n;
    }

    @Override
    public Position<T> front() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        return this.sentinelHead.next;
    }

    @Override
    public Position<T> back() throws EmptyException {
        if (this.empty()) {
            throw new EmptyException();
        }
        return this.sentinelTail.prev;
    }

    @Override
    public Position<T> next(Position<T> p) throws PositionException {
        if (this.last(p)) {
            throw new PositionException();
        }
        return this.convert(p).next;
    }

    @Override
    public Position<T> previous(Position<T> p) throws PositionException {
        if (this.first(p)) {
            throw new PositionException();
        }
        return this.convert(p).prev;
    }

    @Override
    public Position<T> insertFront(T t) {
        Node<T> n = new Node<T>();
        n.data = t;
        n.owner = this;

        this.insertAfter(this.sentinelHead, t);

//        n.next = this.sentinelHead.next;
//        this.sentinelHead.next.prev = n;
//        this.sentinelHead.next = n;
//        n.prev = this.sentinelHead;

        this.length += 1;
        return n;
    }

    @Override
    public Position<T> insertBack(T t) {
        Node<T> n = new Node<T>();
        n.data = t;
        n.owner = this;

        n.prev = this.sentinelTail.prev;
        this.sentinelTail.prev.next = n;
        this.sentinelTail.prev = n;
        n.next = this.sentinelTail;

        this.length += 1;
        return n;
    }

    @Override
    public void removeFront() throws EmptyException {
        if (this.length == 1) {
            this.sentinelHead.next = this.sentinelTail;
            this.sentinalTail.prev = this.sentinelHead;
        } else if (this.length == 0) {
            throw new EmptyException();
        }

        this.length--;

        this.sentinelHead.next = this.sentinelHead.next.next;
        this.sentinelHead.next.prev = this.sentinalHead;
    }

    @Override
    public void removeBack() throws EmptyException {
        if (this.length == 1) {
            this.sentinelHead.next = this.sentinelTail;
            this.sentinalTail.prev = this.sentinelHead;
        } else if (this.length == 0) {
            throw new EmptyException();
        }

        this.length--;

        this.sentinelTail.prev = this.sentinelHead.prev.prev;
        this.sentinelTail.prev.next = this.sentinalTail;
    }

    @Override
    public void remove(Position<T> p) throws PositionException {
        Node<T> n = this.convert(p);
        n.owner = null;
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    @Override
    public Position<T> insertBefore(Position<T> p, T t)
    throws PositionException {
        Node<T> current = this.convert(p);
        Node<T> n = new Node<T>();
        n.owner = this;
        n.data = t;

        n.next = current;
        current.prev = n;

        return n;
    }

    @Override
    public Position<T> insertAfter(Position<T> p, T t)
    throws PositionException {
        Node<T> current = this.convert(p);
        Node<T> n = new Node<T>();
        n.owner = this;
        n.data = t;

        n.next = current.next;
        n.prev = current.prev;

        return n;
    }

    @Override
    public Iterator<T> forward() {
        return new ListIterator(true);
    }

    @Override
    public Iterator<T> backward() {
        return new ListIterator(false);
    }

    @Override
    public Iterator<T> iterator() {
        return this.forward();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (Node<T> n = this.head; n != null; n = n.next) {
            s.append(n.data);
            if (n.next != null) {
                s.append(", ");
            }
        }
        s.append("]");
        return s.toString();
    }
}
