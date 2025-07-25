package byow.Core;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;
import byow.Core.Utils;

public class Key {
    UI ui = new UI();


    public String startUIKey(char key) {
        if (key == 'n' || key == 'N') {
            StdDraw.clear();
            ui.seedUI();
            return inputSeed();

        } else if (key == 'l' || key == 'L') {
            StdDraw.clear();
//                ui.loadUI();

        } else {
            System.exit(0);
        }
        return null;
    }

    public TETile[][] move(int width, int height, TETile[][] world, TERenderer ter,boolean headless,String cmdString) {
        while (true) {

            if (headless) {
                boolean quitMode = false;

                for (char key : cmdString.toCharArray()) {
                    char c = key;
                    if ((c == 'w' || c == 'W') && world[width][height + 1].character() != '#') {
                        world[width][height] = Tileset.NOTHING;
                        height += 1;
                        world[width][height] = Tileset.AVATAR;
                    }     else  if ((c == 's' || c == 'S') && world[width][height - 1].character() != '#') {
                        world[width][height] = Tileset.NOTHING;
                        height -= 1;
                        world[width][height] = Tileset.AVATAR;
                    }else if ((c == 'a' || c == 'A') && world[width - 1][height].character() != '#') {
                        world[width][height] = Tileset.NOTHING;
                        width = width - 1;
                        world[width][height] = Tileset.AVATAR;
                    }
                    else if ((c == 'd' || c == 'D') && world[width + 1][height].character() != '#') {
                        world[width][height] = Tileset.NOTHING;
                        width = width + 1;
                        world[width][height] = Tileset.AVATAR;
                    }else if (c == ':') {
                            quitMode = true;
                        }
                    else if ((c == 'q' || c == 'Q') && quitMode){
                        Utils.saveGame(world, width, height);
                        System.exit(0);
                    }
                    }

                return world;
                }
                else if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();


                if ((c == 'w' || c == 'W') && world[width][height + 1].character() != '#') {
                    world[width][height] = Tileset.NOTHING;
                    height += 1;
                    world[width][height] = Tileset.AVATAR;
                    ter.renderFrame(world);
                }     else  if ((c == 's' || c == 'S') && world[width][height - 1].character() != '#') {
                    world[width][height] = Tileset.NOTHING;
                    height -= 1;
                    world[width][height] = Tileset.AVATAR;
                    ter.renderFrame(world);
                }else if ((c == 'a' || c == 'A') && world[width - 1][height].character() != '#') {
                    world[width][height] = Tileset.NOTHING;
                    width = width - 1;
                    world[width][height] = Tileset.AVATAR;
                    ter.renderFrame(world);
                }
                else if ((c == 'd' || c == 'D') && world[width + 1][height].character() != '#') {
                    world[width][height] = Tileset.NOTHING;
                    width = width + 1;
                    world[width][height] = Tileset.AVATAR;
                    ter.renderFrame(world);
                }else if (c == ':') {
                    while (true) {
                        if (StdDraw.hasNextKeyTyped()) {
                            c = StdDraw.nextKeyTyped();
                            if (c == 'q' || c == 'Q') {
                                Utils.saveGame(world, width, height);
                                System.exit(0);
                            }
                        }
                    }
                }

            }
        }
    }


    private String inputSeed() {

        StringBuilder s = new StringBuilder();
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {

                char c = StdDraw.nextKeyTyped();
                if (c == 's' || c == 'S') {
                    return s.toString();
                }
                if (Character.isDigit(c)) {
                    s.append(c);
                    StdDraw.clear();
                    StdDraw.text(Repository.WIDTH / 2, Repository.HEIGHT / 2, s.toString());
                    ui.seedUI();
                }
            }
        }

    }
}





