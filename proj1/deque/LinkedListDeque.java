package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class StuffNode {
        private T item;
        private StuffNode next;
        private StuffNode prev;

        private StuffNode(StuffNode p, T i, StuffNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private StuffNode sentinel;
    private int size;

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

    public class LinkedListDequeIterator implements Iterator<T> {
        private StuffNode p;
        public LinkedListDequeIterator() {
            p = sentinel.next;
        }

        @Override
        public T next() {
            T i = p.item;
            p = p.next;
            return i;
        }

        @Override
        public boolean hasNext() {
            return p.item != null;
        }
    }

    /** Return an T iterator */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    public LinkedListDeque() {
        sentinel = new StuffNode(null, null,null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }


    /** Add an item of type T to the front of the deque.*/
    @Override
    public  void addFirst(T i) {

        StuffNode item = new StuffNode(sentinel,i,sentinel.next);
        sentinel.next.prev = item;
        sentinel.next = item;
        size++;
    }

    /** Add an item of type T to the back of the deque. */
    @Override
    public void addLast(T i) {
        StuffNode item = new StuffNode(sentinel.prev, i, sentinel);
        sentinel.prev.next = item;
        sentinel.prev = item;
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
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T i = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size--;

        return i;
    }

    /** Removes and returns the item at the back of the deque.
     * if no such item exists, returns null. */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T i = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size--;

        return i;
    }

    /** Get the item at the given index, where 0 is the front,
     *  1 is the next item, and so forth. if no such item exists,
     *  returns null. Must not alter the deque! */
    @Override
    public T get(int index) {
        if (isEmpty() || index < 0) {
            return null;
        }

        StuffNode p = sentinel.next;

        while (index > 0) {
            if (p.item == null) {
                return null;
            }
            p = p.next;
            index--;
        }

        return p.item;

    }


    public T getFirst() {
        if (sentinel.next.item == null) {
            return null;
        }
        return sentinel.next.item;
    }

    public T getLast() {
        if (sentinel.prev.item == null) {
            return null;
        }
        return sentinel.prev.item;
    }



    public T getRecursive(int index) {
        if (isEmpty() || index < 0) {
            return null;
        }
        StuffNode p = sentinel.next;
        return Recursive(p, index);
    }

    private T Recursive(StuffNode p, int index) {
        if (p.item == null) {
            return null;
        } else if ( index == 0) {
            return p.item;
        }
        return Recursive(p.next, index -1);
    }


}
