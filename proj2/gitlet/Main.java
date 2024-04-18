package gitlet;
import java.io.File;

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
                vAdd(args);
                git.add(args[1]);
                break;

            case "commit":
                verifyCommit(args);
                git.commit(args[1]);
                break;

            case "checkout":
                break;

            case "log":
                break;

            case "":
                System.out.println("Please enter a command.");
                break;

            default:
                System.out.println("No command with that name exists.");
                break;
        }
    }
}
