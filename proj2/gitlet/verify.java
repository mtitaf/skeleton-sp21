package gitlet;

import java.io.File;

public class verify {


    public static boolean vInit(String[] args) {
        if (args.length != 1) {
            return false;
            //exception
        }

        File f = new File(".gitlet");
        if (f.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            return false;
        }
        return true;
    }


    public static boolean vAdd(String[] args) {
        if (args.length != 2) {
            return false;
        }
        return true;
    }

    public static boolean verifyCommit(String[] args) {
        if (args.length != 2) {
            System.out.println("message error");
            return false;
        }
        return true;
    }




//    public static boolean verifyAdd() {
//
//    }

}
