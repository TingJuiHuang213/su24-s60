package gitlet;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            return;
        }

        String command = args[0];
        Repository repo = new Repository();

        // Command handlers
        Map<String, Consumer<String[]>> commands = new HashMap<>();
        commands.put("init", (String[] arguments) -> repo.init());
        commands.put("add", (String[] arguments) -> {
            if (arguments.length < 2) {
                System.out.println("Please provide a file name.");
                return;
            }
            repo.add(arguments[1]);
        });
        commands.put("commit", (String[] arguments) -> {
            if (arguments.length < 2) {
                System.out.println("Please provide a commit message.");
                return;
            }
            repo.commit(arguments[1]);
        });
        commands.put("restore", (String[] arguments) -> {
            if (arguments.length == 3 && arguments[1].equals("--")) {
                repo.restore(arguments[2]);
            } else if (arguments.length == 4 && arguments[2].equals("--")) {
                repo.restore(arguments[1], arguments[3]);
            } else {
                System.out.println("Incorrect operands.");
            }
        });
        commands.put("log", (String[] arguments) -> repo.log());
        commands.put("global-log", (String[] arguments) -> repo.globalLog());
        commands.put("rm", (String[] arguments) -> {
            if (arguments.length < 2) {
                System.out.println("Please provide a file name.");
                return;
            }
            repo.rm(arguments[1]);
        });
        commands.put("status", (String[] arguments) -> repo.status());
        commands.put("reset", (String[] arguments) -> {
            if (arguments.length < 2) {
                System.out.println("Please provide a commit id.");
                return;
            }
            repo.reset(arguments[1]);
        });
        commands.put("find", (String[] arguments) -> {
            if (arguments.length < 2) {
                System.out.println("Please provide a commit message.");
                return;
            }
            repo.find(arguments[1]);
        });

        // Execute command
        if (commands.containsKey(command)) {
            commands.get(command).accept(args);
        } else {
            System.out.println("Invalid command.");
        }
    }
}
