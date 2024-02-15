package deque;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;


public class DequeRandomTest {

    @Test
    public void randomTest() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        LinkedListDeque<Integer> B = new LinkedListDeque<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeB = B.size();
                assertEquals(sizeL, sizeB);
            } else if (operationNumber == 2 && L.size() > 0) {
                //getLast
                assertEquals(L.getLast(), B.getLast());
            } else if (operationNumber == 3 && L.size() > 0) {
                //removeLast
                int lastL = L.removeLast();
                int lastB = B.removeLast();
                assertEquals(lastL, lastB);
            }
        }
    }


    public void main(String[] args) {
        randomTest();
    }
}
// YOUR TESTS HERE


