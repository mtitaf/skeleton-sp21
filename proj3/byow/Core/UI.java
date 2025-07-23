package byow.Core;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

/** 绘制所有的用户界面 */



public class UI {
    int width = Repository.WIDTH;
    int height = Repository.HEIGHT;
    int midWidth = width / 2;
    int midHeight = height / 2;
    
    
    
    public void showStartUI() {
        StdDraw.text(midWidth, midHeight +5, "CS61B");
        Font font = new Font("Monaco", Font.BOLD, 16);
        StdDraw.setFont(font);
        StdDraw.textLeft(midWidth-4, midHeight -2, "New Game (N)");
        StdDraw.textLeft(midWidth-4, midHeight -4, "Load Game (L)");
        StdDraw.textLeft(midWidth-4, midHeight-6, "Quit (Q)");
        StdDraw.show();

    }

    public void seedUI() {
        StdDraw.text(midWidth, height -2, "Please enter a seed,Only digit");
        StdDraw.show();
    }

    public void showGameUI(int heath, int magic, String map) {
        StdDraw.textLeft(0,height - 1,"HEATH" +  heath);
        StdDraw.textLeft(0,height - 3, "magic" + magic);
        StdDraw.text(midWidth,height -1 , "map" + map);
        StdDraw.show();

    }

//    public void


}
