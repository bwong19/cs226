package hw5;

/**
 * Set implemented using our abstract List, with a LinkedList.
 * @param <T> Element type.
 */
public class MoveToFrontListSet<T> extends ListSet<T> {
    @Override
    public Position<T> find(T t) {
        if (this.list.empty()) {
            return null;
        }

        Position<T> cur = this.list.front();

        while (!this.list.last(cur)) {
            if (cur.get().equals(t)) {
                this.list.remove(cur);
                this.list.insertFront(t);
                return cur;
            }
            cur = this.list.next(cur);
        }

        if (!cur.get().equals(t)) {
            return null;
        }
        this.list.remove(cur);
        this.list.insertFront(t);
        return cur;
    }
}
