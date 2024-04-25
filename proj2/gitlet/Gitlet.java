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

        Branch master = new Branch();
        master.save();
        Commit initCommit = new Commit();
        initCommit.commit();


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

        NewCommit.commit();

//        writeContents(join(PWD, readContentsAsString(HEAD)), NewCommit.Sha);
    }

    public void rm(String filename) {
        String filePath = join(filename).getPath();

        Branch b = readHEAD();
        b.addedMap.remove(filePath);
        b.trackMap.remove(filePath);
        b.removeList.add(filePath);
        b.save();
    }

    public void log() {
        String currentCommitId = readContentsAsString(join(".gitlet", readContentsAsString(HEAD)));

        while (true) {
            File CommitFile = ShaToFile(currentCommitId);

            Commit currentCommit = readObject(CommitFile, Commit.class);
            System.out.println("===");
            System.out.println("commit " + currentCommit.Sha);
            System.out.println("Date: " + currentCommit.Date);
            System.out.println(currentCommit.message);
            System.out.println();
            if (currentCommitId.equals(currentCommit.parent)) {
                break;
            }
            currentCommitId = currentCommit.parent;

        }
    }

    public void status() {
        Branch b = readHEAD();

        System.out.println("=== Branches ===");
        for (String branchName : getAllBranch()) {
            if (branchName.equals(b.name)) {
                branchName = "*" + branchName;
            }
            System.out.println(branchName);
        }

        System.out.println();

        System.out.println("=== Staged Files ===");
        for (String key : b.addedMap.keySet()) {
            System.out.println(key);
        }
        System.out.println();

        System.out.println("=== Removed Files ===");
        for (String i : b.removeList) {
            System.out.println(i);
        }
        System.out.println();

        System.out.println("=== Modifications");
        System.out.println();

        System.out.println("=== Untracked Files ===");
        System.out.println();

    }

    private String[] getAllBranch() {
        File branches = join(".gitlet", "refs", "heads");
        String[] files = branches.list();
        return files;
    }


    public boolean checkout() {
        throw new RuntimeException();
    }




}
