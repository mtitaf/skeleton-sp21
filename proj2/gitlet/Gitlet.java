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

        Branch headBranch = readHEAD();
        if (headBranch.removeList.contains(filename)) {
            headBranch.removeList.remove(filename);
            headBranch.save();
        }

        String Sha = sha1(readContentsAsString(f));
        Commit headCommit = readHeadCommit();
        if (headCommit.files.containsKey(filename)) {
            if (headCommit.files.get(filename).equals(Sha)) {
                return;
            }
        }


        File storage = StringtoObjectoFile(readContentsAsString(f));
        storage.mkdirs();

        copy(f.getPath(),storage.getPath());


        headBranch.addedMap.put(f.getPath(), Sha);
        headBranch.trackMap.put(f.getPath(), Sha);
        headBranch.save();
    }

    public void commit(String message) {

        Commit NewCommit = new Commit(message);
        NewCommit.commit();

    }

    public void rm(String filename) {
        Branch b = readHEAD();
        Commit headCommit = readHeadCommit();
        if (headCommit.files.containsKey(filename)) {
            join(filename).delete();
            if (!b.removeList.contains(filename)) {
                b.removeList.add(filename);
            }
        }
        b.addedMap.remove(filename);
        b.trackMap.remove(filename);

        b.save();
    }

    public void log() {
        String currentCommitId = readContentsAsString(join(".gitlet", readContentsAsString(HEAD)));

        while (true) {
            File CommitFile = ShaToFile(currentCommitId);

            Commit currentCommit = readObject(CommitFile, Commit.class);
            System.out.println(currentCommit);
            if (currentCommitId.equals(currentCommit.parent)) {
                break;
            }
            currentCommitId = currentCommit.parent;

        }
    }

    public void status() {
        GlobalInfo g = readGlobalInfo();

        Branch b = readHEAD();
        System.out.println("=== Branches ===");
        for (String branchName : g.branchInfo.keySet()) {
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

        System.out.println("=== Modifications Not Staged For Commit ===");
        System.out.println();

        System.out.println("=== Untracked Files ===");
        System.out.println();

    }

    public void globalLog() {
        GlobalInfo g = readGlobalInfo();
        for (String s : g.messageTree.values()) {
            File f = ShaToFile(s);
            Commit c = readObject(f, Commit.class);
            System.out.println(c);
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
        writeContents(f, headCommit.Sha);
    }

    public void rmBranch(String name) {
        Branch b = readBranch(name);
        if (b.name.equals(name)) {
            System.out.println("Cannot remove the current branch");
            return;
        }
        GlobalInfo g = readGlobalInfo();

        File branch = ShaToFile(b.Sha);
        File branchHead = join( PWD,"refs", "heads", name);
        g.branchInfo.remove(name);

        branchHead.delete();
        branch.delete();

        g.save();
    }

    public boolean checkout(String... args) {
        if (args[1].equals("--")) {
            String filename = args[2];
            Branch b = readHEAD();
            String fileSha = b.trackMap.get(filename);
            File f = ShaToFile(fileSha);
            copy(f.getPath(),join(filename).getPath());
            System.exit(0);
        }

        GlobalInfo g = readGlobalInfo();

        if (g.branchInfo.containsKey(args[1])) {
            String branchName = args[1];
            Branch b =readBranch(branchName);
            for (String s : b.trackMap.keySet()) {
                File f = ShaToFile(b.trackMap.get(s));
                copy(f.getPath(), s);
            }
            String head = join( "refs" , "heads" , b.name).getPath();
            writeContents(HEAD, head);
            System.exit(0);

        }

        if (g.messageTree.containsValue(args[1])) {
            String commitId = args[1];
            String filename = args[3];
            Commit c = readObject(ShaToFile(commitId), Commit.class);
            String fileSha = c.files.get(filename);
            File f = ShaToFile(fileSha);
            copy(f.getPath(),filename);
            System.exit(0);
        }

        throw new RuntimeException();
    }

    public void reset(String commitId) {
        Commit inputCommit = readObject(ShaToFile(commitId),Commit.class);
        Branch currentHead = readHEAD();
        for (String i : inputCommit.files.keySet()) {
            if (!currentHead.trackMap.containsKey(i)) {
                currentHead.trackMap.remove(i);
            }
        }
        currentHead.headCommitId = commitId;
        writeContents( StringtoObjectoFile( readContentsAsString(HEAD)),commitId);
        currentHead.save();
    }

    public void merge(String name) {
        Branch currentBranch = readHEAD();

        //TODO if commit not exist ,print error info

        Branch mergeBranch = readBranch(name);

        if (currentBranch.equals(mergeBranch)) {
            System.out.println("Current branch fast-forwarded.");
            System.exit(0);
        }

        if (currentBranch.headCommitId.equals(mergeBranch.headCommitId)) {
            System.out.println("Given branch is an ancestor of the current branch.");
            System.exit(0);
        }

        for (String filename: mergeBranch.trackMap.keySet()) {
            if (currentBranch.trackMap.containsKey(filename)) {
                File file1 = ShaToFile(currentBranch.trackMap.get(filename));
                File file2 = ShaToFile(mergeBranch.trackMap.get(filename));
                mergeFile(file1, file2);
            }
        }
    }

    public void mergeFile(File file1, File file2) {
        if (file1.equals(file2)) {
            return;
        }
    }
}
