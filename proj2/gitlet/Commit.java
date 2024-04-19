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
public class Commit implements Serializable {

    public String Sha;

    private String Date;
    HashMap<String, String> files;
    private String message;
    private String parent;


    public Commit() {
        this.Date = "00:00:00 UTC, Thursday, 1 January 1970";
        this.message = "init";
        this.Sha = sha1("initRepository");
        this.files = new HashMap<>();
        this.parent = this.Sha;
    }

    public Commit(String message) {
        this.message = message;
        this.Date = currentTime();
        this.files = readHEAD().trackMap;
        this.Sha = sha1(this.toString());
    }


    public void saveCommit() {
        System.out.println(this);
        writeContents(join(PWD,readContentsAsString(HEAD)), this.Sha);
        writeObject(StringtoObjectsFile(this.toString()), this);
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
        String s = "Date:       " + this.Date + "\n" +
                   "message:    " + this.message + "\n" +
                   "files:      " + this.files.keySet() + "\n" +
                   "parent:     " + this.parent ;

        return s;
    }
    /* TODO: fill in the rest of this class. */
}
