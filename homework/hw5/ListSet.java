package hw5;

import java.util.Iterator;

/**
 * Set implemented using our abstract List, with a LinkedList.
 * @param <T> Element type.
 */
public class ListSet<T> implements Set<T> {

    /**
     * List of elements.
     */
    protected List<T> list;

    /**
     * Constructor for ListSet.
     */
    public ListSet() {
        list = new LinkedList<T>();
    }

    /**
     * Helper method to find the position of t.
     * @param t Value to find.
     * @return the position of the value found.
     */
    public Position<T> find(T t) {
        if (this.list.empty()) {
            return null;
        }

        Position<T> cur = this.list.front();

        while (!this.list.last(cur)) {
            if (cur.get().equals(t)) {
                return cur;
            }
            cur = this.list.next(cur);
        }

        if (!cur.get().equals(t)) {
            return null;
        }
        return cur;
    }

    @Override
    public void insert(T t) {
        if (this.find(t) != null) {
            return;
        }
        this.list.insertBack(t);
    }

    @Override
    public void remove(T t) {
        Position<T> p = this.find(t);

        if (p == null) {
            return;
        }
        this.list.remove(p);
    }

    @Override
    public boolean has(T t) {
        return this.find(t) != null;
    }

    @Override
    public int size() {
        return this.list.length();
    }

    @Override
    public Iterator<T> iterator() {
        return this.list.iterator();
    }

}
