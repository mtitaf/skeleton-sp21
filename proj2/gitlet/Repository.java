package gitlet;
import java.io.File;
import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */

    /** The .gitlet directory. */
    public static final File PWD = join(CWD, ".gitlet");

    public static final File REFS = join(PWD, "refs");

    public static final File HEAD = join(PWD, "HEAD");

    public static final File objects_dir = join(PWD, "objects");

    public static final String GlobalInfoFileName = "globalInfo";


    /* TODO: fill in the rest of this class. */
}
