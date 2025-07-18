package byow.Core;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

public class Key {
    UI ui = new UI();

    public boolean execute(String args,char key) {
        switch (args){
            case "startUI":
                startUIKey(key);
                return true;
        }
        return false;
    }


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

    public void move(int width, int height, TETile[][] world, TERenderer ter) {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                if ((c == 'w' || c == 'W') && !world[width][height + 1].equals(Tileset.WALL)) {
                    world[width][height] = Tileset.NOTHING;
                    height += 1;
                    world[width][height] = Tileset.AVATAR;
                    ter.renderFrame(world);
                }     else  if ((c == 's' || c == 'S') && !world[width][height - 1].equals(Tileset.WALL)) {
                    world[width][height] = Tileset.NOTHING;
                    height -= 1;
                    world[width][height] = Tileset.AVATAR;
                    ter.renderFrame(world);
                }else if ((c == 'a' || c == 'A') && !world[width - 1][height].equals(Tileset.WALL)) {
                    world[width][height] = Tileset.NOTHING;
                    width = width - 1;
                    world[width][height] = Tileset.AVATAR;
                    ter.renderFrame(world);
                }
                else if ((c == 'd' || c == 'D') && !world[width + 1][height].equals(Tileset.WALL)) {
                    world[width][height] = Tileset.NOTHING;
                    width = width + 1;
                    world[width][height] = Tileset.AVATAR;
                    ter.renderFrame(world);
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
                s.append(c);
                StdDraw.clear();
                StdDraw.text(Repository.WIDTH / 2, Repository.HEIGHT / 2, s.toString());
                ui.seedUI();
            }
        }

    }
}





