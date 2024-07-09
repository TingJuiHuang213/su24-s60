package gitlet;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            return;
        }

        Repository repo = new Repository();
        String cmd = args[0];

        switch (cmd) {
            case "init":
                validateNumArgs(cmd, args, 1);
                repo.init();
                break;
            case "add":
                validateNumArgs(cmd, args, 2);
                repo.add(args[1]);
                break;
            case "commit":
                validateNumArgs(cmd, args, 2);
                repo.commit(args[1]);
                break;
            case "rm":
                validateNumArgs(cmd, args, 2);
                repo.rm(args[1]);
                break;
            case "log":
                validateNumArgs(cmd, args, 1);
                repo.log();
                break;
            case "global-log":
                validateNumArgs(cmd, args, 1);
                repo.globalLog();
                break;
            case "find":
                validateNumArgs(cmd, args, 2);
                repo.find(args[1]);
                break;
            case "status":
                validateNumArgs(cmd, args, 1);
                repo.status();
                break;
            case "checkout":
                if (args.length == 3 && args[1].equals("--")) {
                    validateNumArgs(cmd, args, 3);
                    repo.checkout(args[2]);
                } else if (args.length == 4 && args[2].equals("--")) {
                    validateNumArgs(cmd, args, 4);
                    repo.checkout(args[1], args[3]);
                } else if (args.length == 2) {
                    validateNumArgs(cmd, args, 2);
                    repo.checkoutBranch(args[1]);
                } else {
                    System.out.println("Invalid command.");
                }
                break;
            case "branch":
                validateNumArgs(cmd, args, 2);
                repo.branch(args[1]);
                break;
            case "rm-branch":
                validateNumArgs(cmd, args, 2);
                repo.rmBranch(args[1]);
                break;
            case "reset":
                validateNumArgs(cmd, args, 2);
                repo.reset(args[1]);
                break;
            case "merge":
                validateNumArgs(cmd, args, 2);
                repo.merge(args[1]);
                break;
            case "restore":
                if (args.length == 3 && args[1].equals("--")) {
                    validateNumArgs(cmd, args, 3);
                    repo.restore(args[2]);
                } else if (args.length == 4 && args[2].equals("--")) {
                    validateNumArgs(cmd, args, 4);
                    repo.restore(args[1], args[3]);
                } else {
                    System.out.println("Invalid command.");
                }
                break;
            default:
                System.out.println("Invalid command.");
                break;
        }
    }

    private static void validateNumArgs(String cmd, String[] args, int n) {
        if (args.length != n) {
            System.out.println("Incorrect operands.");
            System.exit(0);
        }
    }
}
