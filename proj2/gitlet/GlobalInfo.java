package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.TreeMap;

import static gitlet.Repository.GlobalInfoFileName;
import static gitlet.Utils.*;

public class GlobalInfo implements Serializable,Dumpable {

    public TreeMap<String, String> messageTree = new TreeMap<>();

    public TreeMap<String, String> branchInfo = new TreeMap<>();

    public void save() {
        File f = StringtoObjectoFile(GlobalInfoFileName);
        writeObject(f, this);
    }

    @Override
    public void dump() {
//        for (String commit, message : this.messageTree) {
//
//        }
//        String info = "added:  " + "\n" + this.addedMap.keySet().toString() + "\n\n" +
//                "track:  " + "\n" + this.trackMap.keySet().toString();
//
//        System.out.println(info);
    }
}
