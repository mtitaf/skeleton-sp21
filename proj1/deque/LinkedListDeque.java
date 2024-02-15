package deque;

public class LinkedListDeque<Item> implements Deque<Item> {
    private class StuffNode {
        public Item item;
        public StuffNode next;
        public StuffNode prev;

        public StuffNode(StuffNode p, Item i, StuffNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private StuffNode sentinel;

    private int size;

    public LinkedListDeque() {
        sentinel = new StuffNode(null,null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    /** Add an item of type T to the front of the deque.*/
    @Override
    public  void addFirst(Item i) {

        StuffNode item = new StuffNode(sentinel,i,sentinel.next);
        sentinel.next.prev = item;
        sentinel.next = item;
        size++;
    }

    /** Add an item of type T to the back of the deque. */
    @Override
    public void addLast(Item i) {
        StuffNode item = new StuffNode(sentinel.prev, i, sentinel);
        sentinel.prev.next = item;
        sentinel.prev = item;
        size++;
    }

    /** Return true if deque is empty */
    @Override
    public boolean isEmpty() {
        return sentinel.next.item == null;
    }

    /** Return the number of items in the deque */
    @Override
    public int size() {
        return size;
    }

    /** Print the items in the deque from first to last, separated by a space.
     * Once add the items have been printed, print out a new line. */
    @Override
    public void printDeque() {
        StuffNode p = sentinel.next;
        while (p.item != null) {
            System.out.print(p.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * if no such item exists, returns ull. */
    @Override
    public Item removeFirst() {
        if (sentinel.next.item == null) {
            return null;
        }

        Item i = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;

        return i;
    }

    /** Removes and returns the item at the back of the deque.
     * if no such item exists, returns null. */
    @Override
    public Item removeLast() {
        if (sentinel.next.item == null) {
            return null;
        }

        Item i = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;

        return i;
    }

    /** Get the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. if no such item exists,
     *  returns null. Must not alter the deque! */
    @Override
    public Item get(int index) {
        if (sentinel.next.item == null) {
            return null;
        }

        StuffNode p = sentinel.next;

        while (index <= 0) {
            if (p.item == null) {
                return null;
            }
            p = p.next;
            index--;
        }

        return p.item;

    }
}
