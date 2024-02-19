package deque;
import java.util.Comparator;

public class MaxArrayDeque<Item> extends ArrayDeque<Item> {

    private Comparator<Item> cmp;

    public MaxArrayDeque(Comparator<Item> c) {
        super();
        this.cmp = c;
    }

    public Item max() {
        return max(cmp);
    }

    public Item max(Comparator<Item> c) {
        if (this.isEmpty()) {
            return null;
        }
        Item item = this.getFirst();

        if (this.size() == 1) {
            return item;
        }

        for (Item i : this) {
            if (c.compare(item, i) < 0) {
                item = i;
            }
        }
        return item;
    }

}
