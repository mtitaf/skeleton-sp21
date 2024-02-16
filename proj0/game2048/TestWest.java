package game2048;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestWest extends TestUtils {



    @Test
    public void WestTileTest2() {
        int[][] before = new int[][] {
                {16, 2, 0, 0},
                {2, 0, 0, 0},
                {4, 0, 0, 0},
                {8, 0, 0, 0},
        };
        int[][] after = new int[][] {
                {16, 2, 0, 0},
                {2, 0, 0, 0},
                {4, 0, 0, 0},
                {8, 0, 0, 0},
        };

        updateModel(before, 0, 0, false);
        String prevBoard = model.toString();
        boolean changed = model.tilt(Side.WEST);
        checkChanged(Side.NORTH, false, changed);
        checkModel(after, 0, 0, prevBoard, Side.WEST);
    }
}
