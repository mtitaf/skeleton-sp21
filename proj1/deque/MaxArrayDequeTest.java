package deque;
import net.sf.saxon.expr.Component;
import org.junit.Test;

import java.util.Comparator;

public class MaxArrayDequeTest {
    @Test
    public void maxTest() {
        MaxArrayDeque<String> Ma = new MaxArrayDeque<>(getStringLengthComparator());
        Ma.addFirst("as");
        Ma.addFirst("asdddd");
        Ma.addFirst("asdss");
        Ma.addFirst("asdd");
        Ma.addFirst("asd1");
        System.out.println(Ma.max());

    }


    public static Comparator<String> getStringLengthComparator() {
        return new StringLengthComparator();
    }

    private static class StringLengthComparator implements Comparator<String>  {
        @Override
        public int compare(String o1, String o2) {
            return o1.length() - o2.length();
        }
    }
}
