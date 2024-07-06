package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author TODO
 */

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            return;
        }

        String firstArg = args[0];
        Repository repo = new Repository();
        switch (firstArg) {
            case "init":
                repo.init();
                break;
            case "add":
                if (args.length < 2) {
                    System.out.println("Please provide a file name.");
                    return;
                }
                repo.add(args[1]);
                break;
            case "commit":
                if (args.length < 2) {
                    System.out.println("Please provide a commit message.");
                    return;
                }
                repo.commit(args[1]);
                break;
            case "restore":
                if (args.length == 3 && args[1].equals("--")) {
                    repo.restore(args[2]);
                } else if (args.length == 4 && args[2].equals("--")) {
                    repo.restore(args[1], args[3]);
                } else {
                    System.out.println("Incorrect operands.");
                    return;
                }
                break;
            case "log":
                repo.log();
                break;
            // 處理其他命令
        }
    }
}
