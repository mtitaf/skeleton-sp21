package gitlet;
import static gitlet.Repository.* ;
import static gitlet.verifyCommand.*;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                if (verifyInit()) {
                initRepository();
                }
                // TODO: handle the `init` command
            case "add":

                // TODO: handle the `add [filename]` command
                break;
            case "commit":
                break;
            case "checkout":
                break;
            case "log":
                break;
        }
    }
}
