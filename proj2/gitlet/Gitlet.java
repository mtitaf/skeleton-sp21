package gitlet;
import edu.princeton.cs.algs4.ST;

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
        GlobalInfo g = new GlobalInfo();
        g.branchInfo.put(master.name, master.Sha);
        g.save();

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
        if (!b.removeList.contains(filename)) {
            b.removeList.add(filePath);
        }
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

    public void globalLog() {
        GlobalInfo g = readGlobalInfo();
        for (String s : g.messageTree.values()) {
            System.out.println(s);
        }
    }


    public void find(String message) {
        GlobalInfo g = readGlobalInfo();
        for (String m : g.messageTree.keySet()) {
            if (m.contains(message)) {
                System.out.println(g.messageTree.get(m) + "  " + m);
            }
        }
    }

    public void branch(String name) {

        Branch newBranch = new Branch(name);
        Commit headCommit = readHeadCommit();
        newBranch.trackMap = headCommit.files;
        newBranch.save();

        GlobalInfo g = readGlobalInfo();
        g.branchInfo.put(newBranch.name, newBranch.Sha);
        g.save();

        File f = join(".gitlet", "refs", "heads", name);
        writeContents(f, headCommit);
    }

    public void rmBranch(String name) {

    }

    public boolean checkout(String... args) {
        if (args[1].equals("--")) {
            String filename = args[2];
            Branch b = readHEAD();
            String fileSha = b.trackMap.get(filename);
            File f = ShaToFile(fileSha);
            copy(f.getPath(),join(filename).getPath());
        }

        GlobalInfo g = readGlobalInfo();

        if (g.branchInfo.containsKey(args[1])) {
            String branchName = args[1];
            Branch b =readBranch(branchName);
            for (String s : b.trackMap.keySet()) {
                File f = ShaToFile(b.trackMap.get(s));
                copy(f.getPath(), s);
            }
            String head = "refs" + "heads" + b.name;
            writeContents(HEAD, head);

        }

        if (g.messageTree.containsValue(args[1])) {
            String commitId = args[1];
            String filename = args[3];
            Commit c = readObject(ShaToFile(commitId), Commit.class);
            String fileSha = c.files.get(filename);
            File f = ShaToFile(fileSha);
            copy(f.getPath(),filename);
        }

        throw new RuntimeException();
    }






}
