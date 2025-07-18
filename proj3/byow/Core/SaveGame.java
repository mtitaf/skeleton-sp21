package byow.Core;

import byow.TileEngine.TETile;

import java.io.File;
import java.io.Serializable;
import byow.Core.Repository.*;
import byow.Core.Utils.*;

public class SaveGame implements Serializable {
    TETile[][] world;

    public SaveGame(TETile[][] world) {
        this.world = world;
    }

    public void save() {;
        Utils.writeObject(Repository.saveFile, this);
    }
}
