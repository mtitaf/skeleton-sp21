package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class UI {
    int width = Repository.WIDTH;
    int height = Repository.HEIGHT;
    public void showStartUI() {
        StdDraw.text(width /2, height /2 +5, "CS61B");
        Font font = new Font("Monaco", Font.BOLD, 16);
        StdDraw.setFont(font);
        StdDraw.textLeft(width /2-4, height /2-2, "New Game (N)");
        StdDraw.textLeft(width /2-4, height /2-4, "Load Game (L)");
        StdDraw.textLeft(width /2-4, height /2-6, "Quit (Q)");
        StdDraw.show();

    }

    public void seedUI() {
        StdDraw.text(width, height, "Please enter a seed");
        StdDraw.show();
    }

    public void showGameUI(int heath, int magic, String map) {
        StdDraw.text(0,height - 1,"HEATH" +  heath);
        StdDraw.text(0,height - 3, "magic" + magic);
        StdDraw.text(width /2,height -1 , "map" + map);
        StdDraw.show();

    }


}
