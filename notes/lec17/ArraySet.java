import java.util.Iterator;

/**
 * Set implemented using plain Java arrays.
 * @param <T> Element type.
 */
public class ArraySet<T> implements Set<T> {
    private int used;
    private T[] data;

    private class SetIterator implements Iterator<T> {
        private int current;

        @Override
        public boolean hasNext() {
            if (current < used) {
                return true;
            }
            return false;
        }

        @Override
        public T next() {
            T t = data[current];
            current++;
            return t;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Make a set.
     */
    public ArraySet() {
        this.data = (T[]) new Object[1];
    }

    private boolean full() {
        return this.used >= this.data.length;
    }

    private void grow() {
        T[] bigger = (T[]) new Object[2 * this.used];
        for (int i = 0; i < this.used; i++) {
            bigger[i] = this.data[i];
        }
        this.data = bigger;
    }

    private int find(T t) {
        for (int i = 0; i < used; ++i) {
            if (t == data[i]) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return used;
    }

    @Override
    public void insert(T t) {
        if (find(t) == -1) {
            return;
        }
        if (used >= data.length) {
            T[] temp = new T[2 * used];
            for (int i = 0; i < used; ++i) {
                temp[i] = data[i];
            }
            data = temp;
        }
        data[used++] = t;
    }

    @Override
    public void remove(T t) {
        int index = find(t);
        data[index] = data[--used];
    }

    @Override
    public boolean has(T t) {
        return find(t) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new SetIterator();
    }
}
