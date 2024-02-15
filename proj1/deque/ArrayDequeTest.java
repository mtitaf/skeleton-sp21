package deque;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {
    @Test
    public void addTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        int x = 500;

        for (int i = 0; i < x; i++) {
        arrayDeque.addFirst(i);
        arrayDeque.addLast(i);
        }

        assertEquals(x * 2,arrayDeque.size());
    }
    @Test
    public void addIsEmptySizeTest() {


        ArrayDeque<String> lld1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse("lld1 should now contain 1 item", lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");
        lld1.printDeque();

    }

    @Test
    public void removeTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        int x = 10;
        int y = 12;

        for (int i =0; i < x; i++) {
        arrayDeque.addLast(3);
        }

        for (int i = 0; i < y; i++) {
            arrayDeque.removeLast();
        }

        for (int i =0; i < x; i++) {
            arrayDeque.addFirst(4);
        }


        for (int i =0; i < y; i++) {
            arrayDeque.removeFirst();
        }

    }

    @Test
    public void sizeTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        int x = 10000;
        int y = 9000;
        int z = 2600;

        for (int i =0; i < x; i++) {
            arrayDeque.addLast(3);
        }

        for (int i =0; i < y; i++) {
            arrayDeque.removeFirst();
        }

        assertTrue(arrayDeque.size() < z);



    }


}
