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
    Key key = new Key();
    int midWidth;
    int midHeight;

    int width;
    int height;
    private Random rand;


    public Engine() {
        this.width = Repository.WIDTH;
        this.height = Repository.HEIGHT;
        this.midWidth = width / 2;
        this.midHeight = height / 2;
//        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
//        Font font = new Font("Monaco", Font.BOLD, 30);
//        StdDraw.setFont(font);
//        StdDraw.setXscale(0, this.width);
//        StdDraw.setYscale(0, this.height);
//        StdDraw.clear(Color.BLACK);
//        StdDraw.enableDoubleBuffering();
    }

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        setWorld();
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
                        rand = new Random(seed);
                        TETile[][] world = generateWorld();
                        world[midWidth +1][midHeight -1] = Tileset.AVATAR;
                        generateRoom(midWidth, midHeight, 5, world, rand);
                        ter.renderFrame(world);
                        ui.showGameUI(3,2,"init");
                        key.move(midWidth + 1, midHeight -1,world, ter,false,"");
                        return;
                    } else if (c == 'l' || c == 'L') {
                        SaveGame save = Utils.readObject(Repository.saveFile,SaveGame.class);
                        ter.renderFrame(save.world);
                        ui.showGameUI(3,2,"init");
                        key.move(save.width, save.height ,save.world, ter, false,"");
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
//        StringBuilder seedString = new StringBuilder();
//        StringBuilder cmdString = new StringBuilder();
//        Boolean s =false;
//        Boolean cmd = false;
        String[] inputs = input.split("[sS]",2);

        String seedString = inputs[0];
        // 确保字符串长度足够，至少有3个字符才能移除首尾（否则可能导致IndexOutOfBoundsException）
        if (seedString != null && seedString.length() >= 3) {
            seedString = seedString.substring(1, seedString.length() - 1);
//            System.out.println(seedString); // 输出: elloWorl
        } else {
            System.out.println("字符串太短，无法移除首尾字符或为null。");
        }

        String cmdString = inputs[1];

        long seed = Utils.safeParseLong(seedString, -1);
        rand = new Random(seed);
        TETile[][] world = generateRoom(midWidth,midHeight,5, generateWorld(),rand);
        key.move(midWidth + 1, midHeight -1,world, ter,true,cmdString);
//        ter.renderFrame(world);



        return world;
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
    }

    public void interactWithRemoteClient(String args) {

    }

    private TETile[][] generateWorld() {
        TETile[][] world = new TETile[width][height];
        for (int x = 0 ; x< width ; x += 1) {
            for (int y = 0; y < height; y += 1) {
                    world[x][y] = Tileset.NOTHING;
            }
        }
        return world;

    }

    private void setWorld() {
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
    }

    private TETile[][] generateRoom(int width, int height,int size,TETile[][] world,Random rand) {

        int roomWidth = 5 + rand.nextInt(4);
        int roomHeight = 5 + rand.nextInt(4);

        if (!checkWallRight(width, height ,roomWidth, roomHeight)) {
            return world;
        }

        for (int w = 0; w < roomWidth; w += 1) {
            extWall(width +w , height, world);
            extWall(width +w, height - roomHeight, world);
        }
        for (int h = 0; h < roomHeight; h += 1) {
            extWall(width, height-h, world);
            extWall(width + roomWidth,height - h, world);
            }
        width = width + roomWidth ;
        height = height -2;
        return generateRoom(width, height, size, world,rand);
    }

//    private int generateTile() {
//
//    }
    private void extWall(int width, int height, TETile[][] world) {
        if (world[width][height].equals(Tileset.WALL)) {
            world[width][height] = Tileset.NOTHING;
        } else {
            world[width][height] = Tileset.WALL;
        }
    }


    private  boolean checkWallRight(int width, int height, int roomWidth, int roomHeight) {
        return width + roomWidth <= this.width -1 && height + roomHeight <= this.height -1;
    }



}
