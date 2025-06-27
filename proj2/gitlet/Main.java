package gitlet;
import java.io.File;
import java.util.Objects;

import static gitlet.Repository.* ;
import static gitlet.verify.*;
import gitlet.Gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {


    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ...
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        Gitlet git = new Gitlet();

        if (args.length == 0) {
            System.out.println("Please enter a command.");
            return;
        }

        String firstArg = args[0];

        if (!firstArg.equals("init")) {
            File f = new File(".gitlet");
            if (!f.exists()) {
                System.out.println("NOt in an initialized Gitlet directory.");
                return;
            }
        }

        switch(firstArg) {
            case "init":
                if (vInit(args)) {
                    git.init();
                }
                break;

            case "add":
                if (vAdd(args)) {
                    git.add(args[1]);
                }
                break;

            case "commit":
                verifyCommit(args);
                git.commit(args[1]);
                break;

            case "rm":
                verifyRm(args);
                git.rm(args[1]);
                break;

            case "checkout":
                git.checkout(args);
                break;

            case "log":
                git.log();
                break;

            case "global-log":
                git.globalLog();
                break;

            case "find":
                git.find(args[1]);
                break;

            case "DumpObj":
                DumpObj.main();
                break;

            case "status":
                git.status();
                break;

            case "branch":
                git.branch(args[1]);
                break;

            case "rm-branch":
                git.rmBranch(args[1]);
                break;

            case "reset":
                git.reset(args[1]);
                break;
            case "merge":
                git.merge(args[1]);

            case "":
                System.out.println("Please enter a command.");
                break;

            default:
                System.out.println("No command with that name exists.");
                break;
        }
    }
}
