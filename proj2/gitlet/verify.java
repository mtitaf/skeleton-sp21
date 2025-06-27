package gitlet;

import java.io.File;

import static gitlet.Utils.join;
import static gitlet.Utils.readHEAD;

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

        if (args.length > 2) {
            System.out.println("Please surround message in quotes.");
            System.exit(0);
        }
        if (args.length == 1) {
            System.out.println("Please enter a commit message.");
            System.exit(0);
        }
        if (args[1].isEmpty()) {
            System.out.println("Please enter a commit message.");
            System.exit(0);
        }

        Branch b = readHEAD();
        if (b.addedMap.isEmpty() && b.removeList.isEmpty()) {
            System.out.println("No changes added to the commit.");
            System.exit(0);
        }

        return true;
    }

    public static void verifyRm(String[] args) {
        if (args.length != 2) {
            System.out.println("Enter empty or not surround message in quotes.");
            System.exit(0);
        }
        Branch b = readHEAD();
        File f = join(args[1]);
        String filePath = f.getPath();

        if (!b.addedMap.containsKey(filePath) && !b.trackMap.containsKey(filePath)) {
            System.out.println("No reason to remove the file.");
            System.exit(0);
        }

    }




//    public static boolean verifyAdd() {
//
//    }

}
