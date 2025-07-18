package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.util.Random;

public class Engine {
    TERenderer ter = new TERenderer();
    Utils unit = new Utils();
    UI ui = new UI();

    int width;
    int height;
    private Random rand;


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
    }

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        Key key = new Key();
        StdDraw.clear();
        ui.showStartUI();
        while (true) {

            if (StdDraw.hasNextKeyTyped()) {
                String s = "nNLlQq";
                char c = StdDraw.nextKeyTyped();
                for (char i : s.toCharArray()) {
                    if (c == 'n' || c == 'N') {
                        String seedString = key.startUIKey(c);
                        long seed = Utils.safeParseLong(seedString, -1);
                        this.rand = new Random(seed);
                        TETile[][] world = generateWorld(this.rand);
                        generateRoom(20, 20, 5, world);
                        ter.renderFrame(world);
                        ui.showGameUI(3,2,"init");
                        key.move(20 + 1, 20 -1,world, ter);
                        return;
                    } else if (c == 'l' || c == 'L') {
                        return;
                    } else if (c == 'q' || c == 'Q') {
                        System.exit(0);
                    }
                }
//                System.out.println("不支持的指令");
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
        long seed = Utils.safeParseLong(seedString, -1);
        this.rand = new Random(seed);
        return generateWorld(rand);
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
    }

    private TETile[][] generateWorld(Random rand) {
        TETile[][] world = new TETile[width][height];
        for (int x = 0 ; x< width ; x += 1) {
            for (int y = 0; y < height; y += 1) {
//                if (y > height -4) {
                    world[x][y] = Tileset.NOTHING;
//                }else {
//
//                    world[x][y] = unit.randomTile(rand);
//                }
            }
        }
        return world;

    }

    private TETile[][] generateRoom(int width, int height,int size,TETile[][] world) {
//        ArrayList<Integer> x = new ArrayList<>();
//        ArrayList<Integer> y = new ArrayList<>();
        for (int i = 0; i < size; i += 1) {
            world[width + i][height] = Tileset.WALL;
            world[width][height - i] = Tileset.WALL;
            world[width + i][height -size] = Tileset.WALL;
            world[width +size][height -i] = Tileset.WALL;
            world[width +1][height -1] = Tileset.AVATAR;
        }
        return world;



    }





}
