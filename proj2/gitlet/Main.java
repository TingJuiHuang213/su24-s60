package gitlet;

public class Main {

    public static void main(String... args) {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            return;
        }

        String firstArg = args[0];
        switch (firstArg) {
            case "init":
                Repository.init();
                break;
            case "add":
                if (args.length < 2) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.add(args[1]);
                break;
            case "commit":
                if (args.length < 2) {
                    System.out.println("Please enter a commit message.");
                    return;
                }
                Repository.commit(args[1]);
                break;
            case "log":
                Repository.log();
                break;
            case "checkout":
                if (args.length < 3) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.checkout(args[1], args[2]);
                break;
            case "rm":
                if (args.length < 2) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.rm(args[1]);
                break;
            case "global-log":
                Repository.globalLog();
                break;
            case "find":
                if (args.length < 2) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.find(args[1]);
                break;
            case "status":
                Repository.status();
                break;
            case "restore":
                if (args.length < 3) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.restore(args[1], args[2]);
                break;
            case "branch":
                if (args.length < 2) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.branch(args[1]);
                break;
            case "reset":
                if (args.length < 2) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.reset(args[1]);
                break;
            case "merge":
                if (args.length < 2) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.merge(args[1]);
                break;
            default:
                System.out.println("No command with that name exists.");
        }
    }
}
