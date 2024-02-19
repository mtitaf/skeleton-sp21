package deque;


import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {

    private T[] items;
    private int size;
    private int first;
    private int last;

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof Deque)) {
            return false;
        }
        Deque<T> o = (Deque<T>) other;

        if (o.size() != this.size()) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (this.get(i) != o.get(i)) {
                return false;
            }
        }
        return true;
    }

    public class ArrayDequeIterator implements Iterator<T> {
        private int iterIndex;
        private int iterSize;

        public ArrayDequeIterator() {
            iterIndex = 0;
            iterSize = size;
        }

        @Override
        public T next() {
            T i = items[safeIndex(iterIndex)];
            iterSize--;
            iterIndex++;
            return i;
        }

        @Override
        public boolean hasNext() {
            return iterSize != 0;
        }
    }

    /** Return an T iterator */

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        first = 0;
        last = 0;
    }

    /** 调整数组大小为 reSize ,将 first 指向 0 , last 指向数组末端 */
    private void resize(int reSize) {
        T[] newT =  (T[]) new Object[reSize];
        int index;

        for (int i = 0; i < size; i++) {
            index = safeIndex(i) ;
            newT[i] = items[index];
        }

        first = 0;
        last = size - 1;
        items = newT;
    }

    /** 检查是否需要调整大小,如果需要,则调整大小 */
    private void checkSize() {
        int length = items.length;

        if (size == length) {
            resize((int)(length * 1.2));
        } else if ((size < length / 4) && (size > 8)) {
            resize(length / 4);
        }
    }


    /** Add an item of type T to the front of the deque.*/
    @Override
    public  void addFirst(T i) {
        checkSize();

        first--;
        setFirstLast();

        items[first] = i;
        size++;
    }

    /** Add an item of type T to the back of the deque. */
    @Override
    public void addLast(T i) {
        checkSize();

        last++;
        setFirstLast();

        items[last] = i;
        size++;
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
        if (size == 0) {
            System.out.println("empty ArrayDeque");
            return;
        }

        int index;

        for (int i = 0; i < size; i++) {
            index = safeIndex(i);
            System.out.print(items[index] + " ");
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     * if no such item exists, returns ull. */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T i = items[first];
        items[first] = null;

        first++;
        setFirstLast();
        size--;

        checkSize();

        return i;
    }

    /** Removes and returns the item at the back of the deque.
     * if no such item exists, returns null. */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T i = items[last];
        items[last] = null;

        last--;
        setFirstLast();

        size--;
        checkSize();

        return i;
    }



    /** Get the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. if no such item exists,
     *  returns null. Must not alter the deque! */
    @Override
    public T get(int index) {
        index = safeIndex(index);
        return items[index];
    }


    public T getFirst() {
        if (size == 0) {
            return null;
        }
        return items[first];
    }

    /** Return last item,if not exists, return null */
    public T getLast() {
        if (isEmpty()) {
            return null;
        }
        return items[last];
    }

    /** 调整 first last 的指向, 如果数组为空,将 first last 重置为 0 ,否则 ,确保指向不超出数组长度 */
    private void setFirstLast() {
        if (size == 0) {
            first = 0;
            last = 0;
        } else {
            first = indexFirstLast(first);
            last = indexFirstLast(last);
        }
    }


    /**  如果指向超出数组范围,将指向调整为 0 或 数组末端*/
    private int indexFirstLast(int index) {
        if (index == items.length) {
            index = 0;
        } else if (index < 0) {
            index = items.length - 1;
        }
        return index;
    }


    /** 确保 index 不会超出数组范围 */
    private int safeIndex(int index) {
        if (index + first >= items.length) {
            index = index + first - items.length;
        }else {
            index += first;
        }
        return index;
    }

}
