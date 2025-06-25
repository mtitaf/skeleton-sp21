package gitlet;

// TODO: any imports you need here

import java.io.Serializable;

import static gitlet.Utils.*;

import java.util.HashMap;
import static gitlet.Repository.*;


/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable, Dumpable {

    public String Sha;

    public String Date;
    HashMap<String, String> files;
    public String message;
    public String parent;


    public Commit() {
        this.Date = "Wed Dec 31 16:00:00 1969 -0800";
        this.message = "initial commit";
        this.files = new HashMap<>();
        this.Sha = sha1("initRepository");
        this.parent = this.Sha;
    }

    public Commit(String message) {
        this.message = message;
        this.Date = currentTime();
        this.files = readHEAD().trackMap;
        this.parent = readContentsAsString(join(PWD, readContentsAsString(HEAD)));
        this.Sha = sha1(this.toString());
    }


    public void commit() {
//        System.out.println(this);
        // 写入内容到
        writeContents(join(PWD,readContentsAsString(HEAD)), this.Sha);
        writeObject(ShaToFile(this.Sha), this);

        Branch b =  readHEAD();
        b.addedMap.clear();
        b.removeList.clear();
        b.headCommitId = this.Sha;
        b.save();

        GlobalInfo g = readGlobalInfo();
        g.messageTree.put(this.message, this.Sha);
        g.save();
    }
    /**
     * TODO: add instance variables here and .
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */


    /** The message of this Commit. */
    @Override
    public String toString() {
        String s = "===\n" +
                   "commit " + this.Sha + "\n" +
                   "Date: " + this.Date + "\n" +
                   this.message + "\n" ;
        return s;
    }

    @Override
    public void dump() {
        System.out.println(this.toString());
    }
    /* TODO: fill in the rest of this class. */
}
