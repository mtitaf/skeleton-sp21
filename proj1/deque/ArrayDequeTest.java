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


}
