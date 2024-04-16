package gitlet;

import java.io.File;
import java.nio.file.Files;

import static gitlet.Utils.*;
import static gitlet.Repository.GITLET_DIR;
import java.io.IOException;


public class Gitlet {
    public void init() {

        File[] folders = {join(GITLET_DIR), join(GITLET_DIR, "refs")};

        for (File f:folders) {
            f.mkdir();
        }

        commit("init");
    }

    public void add(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        String hashSha = sha1(readFile(f));
        String tPath = join(GITLET_DIR,hashSha).getPath();

        copy(f.getPath(),tPath);

//        File c = readObject(head);


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
