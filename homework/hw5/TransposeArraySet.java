package hw5;

import java.util.Iterator;

/**
 * Set implemented using plain Java arrays.
 * @param <T> Element type.
 */
public class TransposeArraySet<T> extends ArraySet<T> {
    @Override
    protected int find(T t) {
        for (int i = 0; i < this.used; i++) {
            if (this.data[i].equals(t)) {
                if (i != 0) {
                    T tmp = this.data[i];
                    this.data[i] = this.data[i - 1];
                    this.data[i - 1] = tmp;
                }
                return i;
            }
        }
        return -1;
    }
}
