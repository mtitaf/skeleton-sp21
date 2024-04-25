package gitlet;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import static gitlet.Utils.*;


public class Branch implements Serializable,Dumpable {
    public String name;


    public HashMap<String, String> addedMap = new HashMap<>();

    public HashMap<String, String> trackMap = new HashMap<>();

    public ArrayList<String> removeList = new ArrayList<>();


    public Branch() {
        this.name = "master";
    }

    public Branch(String name) {
        this.name = name;
    }


    public void save() {
        File f = StringtoObjectsFile(join("refs", "heads", this.name).getPath());
        writeObject(f, this);
    }

    @Override
    public void dump() {
        String info = "added:  " + "\n" + this.addedMap.keySet().toString() + "\n\n" +
                         "track:  " + "\n" + this.trackMap.keySet().toString();

        System.out.println(info);
    }

}
