package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;


public class DequeRandomTest {

    @Test
    public void randomTest() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        LinkedListDeque<Integer> linkDeque = new LinkedListDeque<>();

        int N = 1000000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
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
            } else if (operationNumber == 2 && arrayDeque.size() > 0 ) {
                //getLast
                assertEquals(arrayDeque.getLast(), linkDeque.getLast());
            } else if (operationNumber == 3 && arrayDeque.size() > 0) {
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
            } else if (operationNumber == 2 && arrayDeque.size() > 0 ) {
                //getLast
                assertEquals(arrayDeque.getLast(), linkDeque.getLast());
                assertEquals(arrayDeque.get(operationNumber),linkDeque.get(operationNumber));
            } else if (operationNumber == 3 && arrayDeque.size() > 0) {
                //removeLast
                String lastL = arrayDeque.removeLast();
                String lastB = linkDeque.removeLast();
                assertEquals(lastL, lastB);
            }
        }
    }


    public void main(String[] args) {
        randomTest();
    }
}
// YOUR TESTS HERE


