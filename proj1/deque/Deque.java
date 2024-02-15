package deque;

public interface Deque<Item> {
    /** Add an item of type T to the front of the deque.*/
    public  void addFirst(Item item);
    /** Add an item of type T to the back of the deque. */
    public void addLast(Item item);
    /** Return true if deque is empty */
    public boolean isEmpty(Item item);
    /** Return the number of items in the deque */
    public int size();
    /** Print the items in the deque from first to last, separated by a space.
     * Once add the items have been printed, print out a new line. */
    public void printDeque();
    /** Removes and returns the item at the front of the deque.
     * if no such item exists, returns ull. */
    public Item removeFirst();

    /** Removes and returns the item at the back of the deque.
     * if no such item exists, returns null. */
    public Item removeLast();
    /** Get the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. if no such item exists,
     *  returns null. Must not alter the deque! */
    public Item get(int index);

}
