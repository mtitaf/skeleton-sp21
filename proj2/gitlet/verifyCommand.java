package gitlet;

import java.io.File;

public class verifyCommand {

    public static boolean verifyInit() {
        File f = new File(".gitlet");
        return f.exists();
    }

//    public static boolean verifyAdd() {
//
//    }

}
