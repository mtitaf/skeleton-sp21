package byow.Core;

import java.io.File;

public class Repository {
    /* Feel free to change the width and height. */
    public static final int WIDTH = 60;
    public static final int HEIGHT = 40;
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File saveFile = Utils.join(CWD, "game.sav");
}
