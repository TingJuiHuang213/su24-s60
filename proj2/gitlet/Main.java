package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ...
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            return;
        }
        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                Repository.init();
                break;
            case "add":
                if (args.length < 2) {
                    System.out.println("Please specify the file to add.");
                } else {
                    Repository.add(args[1]);
                }
                break;
            case "commit":
                if (args.length < 2) {
                    System.out.println("Please enter a commit message.");
                } else {
                    Repository.commit(args[1]);
                }
                break;
            case "restore":
                if (args.length < 3) {
                    System.out.println("Please specify the file to restore and the commit ID.");
                } else {
                    Repository.restore(args[1], args[2]);
                }
                break;
            // TODO: FILL THE REST IN
        }
    }
}
