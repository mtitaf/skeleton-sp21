package deque;

public class ArrayDeque<Item> {

    private Item[] items;
    private int size;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
    }

    public void resize(int size) {
        Item[] newItem =  (Item[]) new Object[size];
        System.arraycopy(items, 0, newItem, 0, size);
        items = newItem;

    }


    /** Add an item of type T to the front of the deque.*/
    public  void addFirst(Item item) {
        Item[] newItem =  (Item[]) new Object[size];
        System.arraycopy(items, 0, newItem, 1, size);
        items = newItem;
        items[0] = item;
        size++;

    }

    /** Add an item of type T to the back of the deque. */
    public void addLast(Item item) {
        items[size] = item;
        size++;
    }

    /** Return true if deque is empty */
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Return the number of items in the deque */
    public int size() {
        return size;
    }

    /** Print the items in the deque from first to last, separated by a space.
     * Once add the items have been printed, print out a new line. */
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[i]);
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * if no such item exists, returns ull. */
    public Item removeFirst() {
        Item i = items[0];
        Item[] newItem =  (Item[]) new Object[size];
        System.arraycopy(items, 1, newItem, 0, size -1);
        items = newItem;
        size--;
        return i;
    }

    /** Removes and returns the item at the back of the deque.
     * if no such item exists, returns null. */
    public Item removeLast() {
        Item i = items[size];
        items[size] = null;
        size--;
        return i;
    }

    /** Get the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. if no such item exists,
     *  returns null. Must not alter the deque! */
    public Item get(int index) {
        return items[index];
    }
}
