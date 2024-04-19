package gitlet;
import java.io.File;

import static gitlet.Utils.*;
import static gitlet.Repository.*;



public class Gitlet {



    public void init() {
        File[] folders = {PWD, join(REFS, "heads")};

        for (File f:folders) {
            f.mkdirs();
        }

        writeContents(HEAD, join("refs", "heads", "master").toString());
        Commit initCommit = new Commit();
        initCommit.saveCommit();

        Branch master = new Branch();
        master.save();

    }

    public void add(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        String Sha = sha1(readContentsAsString(f));
        File storage = StringtoObjectsFile(readContentsAsString(f));
        storage.mkdirs();

        copy(f.getPath(),storage.getPath());

        Branch b = readHEAD();
        b.addedMap.put(f.getPath(), Sha);
        b.trackMap.put(f.getPath(), Sha);
        b.save();
    }


    public void commit(String message) {
        Commit NewCommit = new Commit(message);
        NewCommit.saveCommit();

        writeContents(HEAD, NewCommit.Sha);
    }

    public void log() {

    }

    public boolean checkout() {
        throw new RuntimeException();
    }




}
