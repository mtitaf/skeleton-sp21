package byow.Core;

import byow.TileEngine.TETile;

import java.io.File;
import java.io.Serializable;
import byow.Core.Repository.*;
import byow.Core.Utils.*;

public class SaveGame implements Serializable {
    TETile[][] world;
    int width;
    int height;

    public SaveGame(TETile[][] world, int width,int height) {
        this.world = world;
        this.width = width;
        this.height = height;
    }

    public void save() {;
        Utils.writeObject(Repository.saveFile, this);
    }
}
