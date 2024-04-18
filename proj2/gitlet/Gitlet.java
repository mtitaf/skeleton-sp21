package gitlet;
import java.io.File;
import java.io.IOException;
import static gitlet.Utils.*;
import static gitlet.Repository.*;



public class Gitlet {



    public void init() {

        File[] folders = {PWD};

//        for (File f:folders) {
//            f.mkdirs();
//        }

//        writeContents(HEAD, "ref/heads/master");
//        Commit initCommit = new Commit();
//        initCommit.saveCommit();



    }

    public void add(String filename) {
        File f = new File(filename);
        if (!f.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        String fileSha = sha1(readFile(f));
        File storage = objectsPath(fileSha);
        storage.mkdirs();

        copy(f.getPath(),storage.getPath());

        Branch index = readBranch(readContentsAsString(headPath));
        index.addedMap.put(f.getPath(), fileSha);
        index.trackMap.put(f.getPath(), fileSha);
        index.save();

    }


    public void commit(String message) {
        Commit NewCommit = new Commit(message);
        NewCommit.saveCommit();

        writeContents(HEAD, NewCommit.Sha1);
    }

    public void log() {

    }

    public boolean checkout() {
        throw new RuntimeException();
    }




}
