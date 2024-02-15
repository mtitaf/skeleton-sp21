package deque;

public class LinkedListDeque<Item>  {
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
    public  void addFirst(Item i) {

        StuffNode item = new StuffNode(sentinel,i,sentinel.next);
        sentinel.next.prev = item;
        sentinel.next = item;
        size++;
    }

    /** Add an item of type T to the back of the deque. */
    public void addLast(Item i) {
        StuffNode item = new StuffNode(sentinel.prev, i, sentinel);
        sentinel.prev.next = item;
        sentinel.prev = item;
        size++;
    }
    /** Return true if deque is empty */
//    public boolean isEmpty(T item);
    /** Return the number of items in the deque */
//    public int size();
    /** Print the items in the deque from first to last, separated by a space.
     * Once add the items have been printed, print out a new line. */
//    public void printDeque();
    /** Removes and returns the item at the front of the deque.
     * if no such item exists, returns ull. */
//    public T removeFirst();

    /** Removes and returns the item at the back of the deque.
     * if no such item exists, returns null. */
//    public T removeLast();
    /** Get the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. if no such item exists,
     *  returns null. Must not alter the deque! */
//    public T get(int index);
}
