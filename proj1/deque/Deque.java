package deque;

public interface Deque<Item> {
    /** Add an item of type T to the front of the deque.*/
    void addFirst(Item item);

    /** Add an item of type T to the back of the deque. */
    void addLast(Item item);

    /** Return true if deque is empty */
    default boolean isEmpty() {
        return size() == 0;
    }

    /** Return the number of items in the deque */
    int size();

    /** Print the items in the deque from first to last, separated by a space.
     * Once add the items have been printed, print out a new line. */
    void printDeque();

    /** Removes and returns the item at the front of the deque.
     * if no such item exists, returns ull. */
    Item removeFirst();

    /** Removes and returns the item at the back of the deque.
     * if no such item exists, returns null. */
    Item removeLast();

    /** Get the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. if no such item exists,
     *  returns null. Must not alter the deque! */
    Item get(int index);

    Item getFirst();
}
