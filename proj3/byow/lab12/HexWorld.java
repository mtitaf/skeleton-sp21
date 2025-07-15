package byow.lab12;
import org.junit.Test;

import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import ucb.gui.Widget;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 40;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        return switch (tileNum) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            case 2 -> Tileset.GRASS;
            case 4 -> Tileset.AVATAR;
            case 5 -> Tileset.SAND;
            default -> Tileset.UNLOCKED_DOOR;
        };
    }

    public TETile[][] addHexagon(int length,int startWidth,int startHeight, TETile[][] world) {
        TETile t = randomTile();
        int height = startHeight;
        int num = length;
        int length2 = length;
        while (length > 0 ) {
            Genhex(startWidth, num, height, world, t);
            length -= 1;
            num += 2;
            startWidth -=1;
            height -= 1;
        }
        length = length2;
        while (length > 0 ) {
            length -= 1;
            num -= 2;
            startWidth +=1;
            Genhex(startWidth, num, height, world,t);
            height -= 1;

        }
        return world;

    }

    private TETile[][] Genhex(int startTile, int num ,int height, TETile[][] world, TETile t) {
        while (num > 0) {
            world[startTile][height] = t;
            startTile += 1;
            num -= 1;
        }
        return world;
    }

    private TETile[][] Genworld(int width, int height) {

        TETile[][] world = new TETile[width][height];
        for (int x=0; x < width; x+=1) {
            for (int y=0; y < height; y+=1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        return world;
    }

    private int getStartTile(int length) {
         return  (WIDTH - length) / 2;

    }
    private boolean checkLeft(int length, int startWight, int startHeight) {
            int leftest = startWight - length * 3 - 1 ;
            int bottomest = startHeight - length * 3;
            return leftest > 0 && bottomest > 0;

    }

    private boolean checkRight(int length, int startWight,int startHeight) {
        int rightest = startWight + length *3 - 1 ;
        int bottomest = startHeight - length * 3;
        return rightest < WIDTH -2 && bottomest > 0;

    }

    private boolean checkBottom(int length, int startHeight) {
        int bottomest = startHeight - length *2;
        return bottomest > 0;
    }

    private TETile[][] genLeft(int length, int startWidth, int startHeight, TETile[][] world) {
        startWidth = startWidth - (length * 2 -1);
        startHeight = startHeight - length;
        return addHexagon(length,startWidth,startHeight, world);
    }


    public static void main(String[] args) {
        HexWorld h = new HexWorld();
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        int length = 7;
        TETile[][] world = h.Genworld(WIDTH, HEIGHT);
        int startWidth = h.getStartTile(length);
        int startHeight = HEIGHT -1;

        while (h.checkBottom(length,startHeight)) {
            world =  h.addHexagon(length, startWidth,startHeight,world);

            int startWidthLeft = startWidth;
            int startHeightLeft = startHeight;


//
            while (h.checkLeft(length,startWidthLeft,startHeightLeft)) {
                startWidthLeft = startWidthLeft - (length * 2 -1);
                startHeightLeft = startHeightLeft - length;
                world = h.addHexagon(length,startWidthLeft,startHeightLeft,world);
            }
//
            int startWidthRight = startWidth;
            int startHeightRight = startHeight;

            while (h.checkRight(length, startWidthRight, startHeightRight)) {
                startWidthRight = startWidthRight + (length * 2 -1);
                startHeightRight = startHeightRight - length;
                world = h.addHexagon(length,startWidthRight,startHeightRight,world);
            }

//            int startWidthLeft,startHeightLeft;
//            startWidthLeft = startWidth - (length * 2 -1);
//            startHeightLeft = startHeight - length;
//            world = h.addHexagon(length,startWidthLeft,startHeightLeft, world);



            startHeight -= length * 2;


        }
        ter.renderFrame(world);
    }
}


