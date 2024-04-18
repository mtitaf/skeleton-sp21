package gitlet;

// TODO: any imports you need here

import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import static gitlet.Utils.*;
import java.util.Date; // TODO: You'll likely use this in this class
import java.util.HashMap;
import static gitlet.Repository.*;


/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit implements Serializable {

    public String Sha1;

    private String Date;
    HashMap<String, String> files;
    private String message;
    private String parent;


    public Commit() {
        this.Date = "00:00:00 UTC, Thursday, 1 January 1970";
        this.message = "init";
        this.Sha1 = sha1("initRepository");
        this.files = readBranch(readContentsAsString(headPath)).trackMap;
        this.parent = this.Sha1;
    }

    public Commit(String message) {
        this.message = message;
        this.Date = currentTime();
        this.files = readBranch(readContentsAsString(headPath)).trackMap;
        this.Sha1 = sha1(this.toString());
    }


    public void saveCommit() {
        System.out.println(this);
        writeContents(headPath, this.Sha1);
        writeObject(objectsPath(this.Sha1), this);
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
