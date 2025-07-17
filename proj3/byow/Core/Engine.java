package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;
import byow.Core.Repository.*;

public class Engine {
    TERenderer ter = new TERenderer();

    int width;
    int height;
    private Random rand;
    int status;


    public Engine() {
        this.width = Repository.WIDTH;
        this.height = Repository.HEIGHT;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
        this.status = 0;
    }

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        Key key = new Key();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                key.execute(StdDraw.nextKeyTyped(),this.status);
            }
        }
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        String seedString = input.substring(1, input.length() - 2);
        long seed = safeParseLong(seedString, -1);
        this.rand = new Random(seed);
        TETile[][] world = new TETile[width][height];
        for (int x = 0 ; x< width ; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = randomTile();
            }
        }
        return world;
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
    }

    private TETile randomTile() {
        int tileNum = this.rand.nextInt(6);
        return switch (tileNum) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            case 2 -> Tileset.GRASS;
            case 4 -> Tileset.AVATAR;
            case 5 -> Tileset.SAND;
            default -> Tileset.UNLOCKED_DOOR;
        };
    }

    public static long safeParseLong(String str, long defaultValue) {
        if (str == null || str.trim().isEmpty()) {
            return defaultValue;
        }

        try {
            // 移除数字中的逗号（如 "1,000,000"）
            String cleaned = str.replace(",", "").trim();
            return Long.parseLong(cleaned);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


}
