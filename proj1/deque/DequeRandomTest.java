package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import static org.junit.Assert.*;


public class DequeRandomTest {


    @Test
    public void randomTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        LinkedListDeque<Integer> linkDeque = new LinkedListDeque<>();

        int N = 100000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 5);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                arrayDeque.addLast(randVal);
                linkDeque.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int sizeL = arrayDeque.size();
                int sizeB = linkDeque.size();
                assertEquals(sizeL, sizeB);
            } else if (operationNumber == 2 && !arrayDeque.isEmpty()) {
                //getLast
                assertEquals(arrayDeque.getLast(), linkDeque.getLast());
            } else if (operationNumber == 3 && !arrayDeque.isEmpty()) {
                //removeLast
                int lastL = arrayDeque.removeLast();
                int lastB = linkDeque.removeLast();
                assertEquals(lastL, lastB);
            }
        }
    }

    @Test
    public void GetPrintTest() {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();
        LinkedListDeque<String> linkDeque = new LinkedListDeque<>();

        int N = 1000000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                arrayDeque.addLast("v" + i);
                linkDeque.addLast("v" + i);
            } else if (operationNumber == 1) {
                // size
                int sizeL = arrayDeque.size();
                int sizeB = linkDeque.size();
                assertEquals(sizeL, sizeB);
            } else if (operationNumber == 2 && !arrayDeque.isEmpty()) {
                //getLast
                assertEquals(arrayDeque.getLast(), linkDeque.getLast());
                assertEquals(arrayDeque.get(operationNumber),linkDeque.getRecursive(operationNumber));
            } else if (operationNumber == 3 && !arrayDeque.isEmpty()) {
                //removeLast
                String lastL = arrayDeque.removeLast();
                String lastB = linkDeque.removeLast();
                assertEquals(lastL, lastB);
            }
        }
    }

    @Test
    public void IterTest() {
        LinkedListDeque<String> LList = new LinkedListDeque<>();
        ArrayDeque<String> AList = new ArrayDeque<>();

        LList.addLast("a");
        AList.addLast("a");
        LList.addLast("b");
        AList.addLast("b");
        LList.addFirst("z");
        AList.addFirst("z");
        LList.addFirst("x");
        AList.addFirst("x");
        LList.removeLast();
        AList.removeLast();

        for (String s : LList) {
            System.out.print(s);
        }
        System.out.println();
        for (String s : AList) {
            System.out.print(s);
        }
        System.out.println();

    }

    @Test
    public void equalTest() {
        LinkedListDeque<String> LList = new LinkedListDeque<>();
        ArrayDeque<String> AList = new ArrayDeque<>();

        LList.addLast("a");
        AList.addLast("a");
        LList.addLast("b");
        AList.addLast("b");
        LList.addFirst("z");
        AList.addFirst("z");
        LList.addFirst("x");
        AList.addFirst("x");
        LList.removeLast();
        AList.removeLast();

        assertTrue(LList.equals(AList));
        assertTrue(AList.equals(LList));
        assertEquals(AList,LList);

    }


    public  void main(String[] args) {
        randomTest();
    }
}
// YOUR TESTS HERE


