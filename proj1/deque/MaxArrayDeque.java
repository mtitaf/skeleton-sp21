package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.cmp = c;
    }

    public T max() {
        return max(cmp);
    }

    public T max(Comparator<T> c) {
        if (this.isEmpty()) {
            return null;
        }
        T item = this.getFirst();

        if (this.size() == 1) {
            return item;
        }

        for (T i : this) {
            if (c.compare(item, i) < 0) {
                item = i;
            }
        }
        return item;
    }

}
