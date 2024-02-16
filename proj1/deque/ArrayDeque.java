package deque;

import afu.org.checkerframework.checker.igj.qual.I;

public class ArrayDeque<Item> implements Deque<Item>{

    private Item[] items;
    private int size;
    private int first;
    private int last;
    private int middle;

    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        first = 0;
        last = 0;
    }

    /** 调整数组大小为reSize */
    private void resize(int reSize) {
        Item[] newItem =  (Item[]) new Object[reSize];
        int index;

        for (int i = 0; i < size; i++) {
            index = safeIndex(i) ;
            newItem[i] = items[index];
        }

        first = 0;
        last = size - 1;
        items = newItem;
    }


    /** Add an item of type T to the front of the deque.*/
    @Override
    public  void addFirst(Item i) {
        if (size == items.length) {
            resize((int)(items.length * 1.2));
        }

        first--;
        setFirstLast();

        items[first] = i;
        size++;
    }

    /** Add an item of type T to the back of the deque. */
    @Override
    public void addLast(Item i) {
        if (size == items.length) {
            resize((int)(items.length * 1.2));
        }


        last++;
        setFirstLast();

        items[last] = i;
        size++;
    }

    /** Return true if deque is empty */
    @Override
    public boolean isEmpty() {
        return (size == 0);
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
        int index;

        for (int i = 0; i < size; i++) {
            index = safeIndex(i);
            System.out.print(items[first + i] + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * if no such item exists, returns ull. */
    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }

        Item i = items[first];
        items[first] = null;

        first++;
        setFirstLast();
        size--;

        if ((size < items.length / 4) && (size > 4)) {
            resize(items.length / 4);
        }

        return i;
    }

    /** Removes and returns the item at the back of the deque.
     * if no such item exists, returns null. */
    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }

        Item i = items[last];
        items[last] = null;

        last--;
        setFirstLast();
        size--;
        if ((size < items.length / 4) && (size > 8)) {
            resize(items.length / 4);
        }

        return i;
    }

    /** Get the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. if no such item exists,
     *  returns null. Must not alter the deque! */
    @Override
    public Item get(int index) {
        index = safeIndex(index);
        return items[index];
    }

    /** Return last item,if not exists, return null */
    public Item getLast() {
        if (isEmpty()) {
            return null;
        }
        return items[last];
    }

    /** 调整 first last 的指向, 如果数组为空,将 first last 重置为 0 ,否则 ,确保指向不超出数组长度 */
    public void setFirstLast() {
        if (size == 0) {
            first = 0;
            last = 0;
        } else {
            first = indexFirstLast(first);
            last = indexFirstLast(last);
        }
    }


    /**  */
    public int indexFirstLast(int index) {
        if (index == items.length) {
            index = 0;
        } else if (index < 0) {
            index = items.length - 1;
        }
        return index;
    }


    /** 确保 index 不会超出数组长度 */
    public int safeIndex(int index) {
        if (index + first >= items.length) {
            index = index + first - items.length;
        }else {
            index += first;
        }
        return index;
    }

}
