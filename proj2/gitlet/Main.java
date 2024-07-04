package gitlet;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            return;
        }

        String command = args[0];
        switch (command) {
            case "init":
                Repository.init();
                break;
            case "add":
                if (args.length < 2) {
                    System.out.println("Please enter a file name.");
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
                if (args.length == 3 && args[1].equals("--")) {
                    Repository.restore(args[2]);
                } else if (args.length == 4 && args[2].equals("--")) {
                    Repository.restore(args[1], args[3]);
                } else {
                    System.out.println("Incorrect operands.");
                }
                break;
            case "log":
                Repository.log();
                break;
            // 添加更多命令的处理逻辑
            default:
                System.out.println("No command with that name exists.");
        }
    }
}
