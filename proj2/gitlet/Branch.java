package gitlet;
import java.io.*;
import java.util.HashMap;
import static gitlet.Utils.*;


public class Branch implements Serializable {
    public String name;

    public Branch() {
        this.name = "master";
    }

    public Branch(String name) {
        this.name = name;
    }


    public HashMap<String, String> addedMap = new HashMap<>();

    public HashMap<String, String> trackMap = new HashMap<>();

    public void save() {
        writeObject(StringtoObjectsFile(join("refs", "heads", this.name).getPath()), this);
    }

}
