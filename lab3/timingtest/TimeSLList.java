package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        int[] size = {1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000};

        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int ops = 10000;

        for (int i : size) {
            SLList<String> L = addCount(i);

            Stopwatch sw = new Stopwatch();

            for (int y = 0; y < ops; y++) {
            L.getLast();
            }

            double timeInSeconds = sw.elapsedTime();


            Ns.addLast(L.size());
            times.addLast(timeInSeconds);
            opCounts.addLast(ops);
        }

        printTimingTable(Ns, times, opCounts);
    }

    private static SLList<String> addCount(int x) {
        SLList<String> list = new SLList<>();
        for (int i = 0; i < x; i++) {
            list.addLast("hello");
        }
        return list;
    }

}
