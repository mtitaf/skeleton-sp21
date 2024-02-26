package gitlet;

// TODO: any imports you need here

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date; // TODO: You'll likely use this in this class

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit {

//    private class commitNode() {
//        private String commitID;
//        private String Date;
//        private String files;
//        private String versionLog;
//
//        private commitNode() {
//            this.Date = ;
//            this.files = ;
//            this.versionLog = ;
//            this.commitID = ;
//        }
//
//    }


    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;


    public static String currentTime() {
        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return currentTime.format(formatter);
    }



    /* TODO: fill in the rest of this class. */
}
