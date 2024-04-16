package gitlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;

import static gitlet.Utils.*;
import static gitlet.Repository.GITLET_DIR;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class Gitlet {

    private final File  PWD = GITLET_DIR;
    private final File track = join(PWD, "track");

    public void init() {

        File[] folders = {join(PWD), join(PWD, "refs")};

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

        String fileSha = sha1(readFile(f));
        File storage = objectsPath(fileSha);
        storage.mkdirs();

        copy(f.getPath(),storage.getPath());

        HashMap<String,String> trackList = new HashMap<>();



        if (track.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(track))) {
                trackList = (HashMap<String, String>) ois.readObject();
                System.out.println("trackList has been read from file.");


            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Failed to read trackList from file: " + e.getMessage());
            }
        }

        trackList.put(f.getPath(),fileSha);
        writeObject(track, trackList);

    }


    public void commit(String message) {
        Commit NewCommit;

        if (message.equals("init")) {
            NewCommit = new Commit();
            File f = join(PWD,sha1("test"));
            writeObject(f,NewCommit);
        }
    }

    public void log() {

    }

    public boolean checkout() {
        throw new RuntimeException();
    }




}
