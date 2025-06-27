package gitlet;
import java.io.*;
import java.util.*;

import static gitlet.Utils.*;

/* 分支信息 */

public class Branch implements Serializable,Dumpable {
    public String name;

    public String Sha;

    public String headCommitId;


    public HashMap<String, String> addedMap = new HashMap<>();

    public HashMap<String, String> trackMap = new HashMap<>();

    public ArrayList<String> removeList = new ArrayList<>();

    public ArrayList<String> commitList = new ArrayList<>();



    public Branch() {
        this.name = "master";
        this.Sha = sha1(join("refs", "heads", this.name).getPath());
    }

    public Branch(String name) {
        this.name = name;
        this.Sha = sha1(join("refs", "heads", this.name).getPath());
    }


    public void save() {
        File f = ShaToFile(this.Sha);
        writeObject(f, this);
    }

    @Override
    public void dump() {
        String info = "added:  " + "\n" + this.addedMap.keySet().toString() + "\n\n" +
                         "track:  " + "\n" + this.trackMap.keySet().toString();

        System.out.println(info);
    }

}
