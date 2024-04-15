package gitlet;

import java.io.File;
import static gitlet.Utils.*;
import static gitlet.Repository.GITLET_DIR;

public class Gitlet {
    public void init() {

        File[] folders = {join(GITLET_DIR), join(GITLET_DIR, "refs")};

        for (File f:folders) {
            f.mkdir();
        }
        /*TODO mkdir folders */

        commit("init");
    }

    public void add(String filename) {
        File f = new File(filename);
        String hash = sha1(f);

//        File c = readObject(head);

        if (!f.exists()) {
            System.out.println("file not exist");
        }
    }


    public void commit(String message) {
        Commit NewCommit;

        if (message.equals("init")) {
            NewCommit = new Commit();
            File f = join(GITLET_DIR,sha1("test"));
            writeObject(f,NewCommit);
        }
    }

    public void log() {

    }

    public boolean checkout() {
        throw new RuntimeException();
    }



}
