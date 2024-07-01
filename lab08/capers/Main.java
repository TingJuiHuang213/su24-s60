package capers;

import java.io.File;
import java.io.IOException;

/** Canine Capers: A Gitlet Prelude.
 * This class contains the main functionality for the capers application.
 * @authon Sean Dooher
 */
public class Main {
    /** Current Working Directory. */
    static final File CURRENT_WORKING_DIRECTORY = new File(".");

    /** Main metadata folder. */
    static final File CAPERS_METADATA_FOLDER = new File(".capers"); // 初始化CAPERS_METADATA_FOLDER

    /**
     * Runs one of three commands:
     * story [text] -- Appends "text" + a newline to a story file in the
     *                 .capers directory. Additionally, prints out the
     *                 current story.
     *
     * dog [name] [breed] [age] -- Persistently creates a dog with
     *                             the specified parameters; should also print
     *                             the dog's toString(). Assume dog names are
     *                             unique.
     *
     * birthday [name] -- Advances a dog's age persistently
     *                    and prints out a celebratory message.
     *
     * All persistent data should be stored in a ".capers"
     * directory in the current working directory.
     *
     * Recommended structure (you do not have to follow):
     *
     * *YOU SHOULD NOT CREATE THESE MANUALLY,
     *  YOUR PROGRAM SHOULD CREATE THESE FOLDERS/FILES*
     *
     * .capers/ -- top level folder for all persistent data in your lab08 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     *
     * @param args arguments from the command line
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            displayErrorAndExit("Must have at least one argument");
        }
        initializePersistence();
        switch (args[0]) {
            case "story":
                appendToStory(args);
                break;
            case "dog":
                createDog(args);
                break;
            case "birthday":
                celebrateDogBirthday(args);
                break;
            default:
                displayErrorAndExit(String.format("Unknown command: %s", args[0]));
        }
    }

    /**
     * Performs required filesystem operations to allow for persistence.
     * (creates any necessary folders or files)
     * Recommended structure (you do not have to follow):
     *
     * .capers/ -- top level folder for all persistent data in your lab08 folder
     *    - dogs/ -- folder containing all of the persistent data for dogs
     *    - story -- file containing the current story
     */
    public static void initializePersistence() {
        createDirectoryIfNotExists(CAPERS_METADATA_FOLDER);
        createDirectoryIfNotExists(Dog.DOG_DIRECTORY);
    }

    /**
     * Appends the first non-command argument in args
     * to a file called `story` in the .capers directory.
     * Should also print out the Story.
     * @param args Array in format: {'story', text}
     */
    public static void appendToStory(String[] args) {
        validateArgumentCount("story", args, 2);
        File storyFile = new File(CAPERS_METADATA_FOLDER, "story");
        if (!storyFile.exists()) {
            try {
                storyFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        StringBuilder currentStory = new StringBuilder(Utils.readContentsAsString(storyFile));
        currentStory.append(args[1]).append("\n");
        Utils.writeContents(storyFile, currentStory.toString());
        System.out.println(currentStory.toString());
    }

    /**
     * Creates and persistently saves a dog using the first
     * three non-command arguments of args (name, breed, age).
     * Also prints out the dog's information using toString().
     * If the user inputs an invalid age, call displayErrorAndExit()
     * @param args Array in format: {'dog', name, breed, age}
     */
    public static void createDog(String[] args) {
        validateArgumentCount("dog", args, 4);
        String name = args[1];
        String breed = args[2];
        int age;
        try {
            age = Integer.parseInt(args[3]);
        } catch (NumberFormatException e) {
            displayErrorAndExit("Invalid age: " + args[3]);
            return;
        }
        Dog newDog = new Dog(name, breed, age);
        newDog.save();
        System.out.println(newDog);
    }

    /**
     * Advances a dog's age persistently and prints out a celebratory message.
     * Also prints out the dog's information using toString().
     * Chooses dog to advance based on the first non-command argument of args.
     * If the user's input is invalid, call displayErrorAndExit()
     * @param args Array in format: {'birthday', name}
     */
    public static void celebrateDogBirthday(String[] args) {
        validateArgumentCount("birthday", args, 2);
        Dog dog = Dog.loadFromFile(args[1]);
        if (dog == null) {
            displayErrorAndExit("Dog not found: " + args[1]);
            return;
        }
        dog.haveBirthday();
        dog.save();
        System.out.println("Happy birthday! Woof! Woof!");
        System.out.println(dog);
    }

    /**
     * Creates a directory if it does not exist.
     * @param dir the directory to create
     */
    private static void createDirectoryIfNotExists(File dir) {
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    /**
     * Prints out MESSAGE and exits with error code -1.
     * @param message message to print
     */
    public static void displayErrorAndExit(String message) {
        if (message != null && !message.equals("")) {
            System.out.println(message);
        }
        System.exit(-1);
    }

    /**
     * Checks the number of arguments versus the expected number,
     * throws a RuntimeException if they do not match.
     *
     * @param cmd Name of command you are validating
     * @param args Argument array from command line
     * @param n Number of expected arguments
     */
    public static void validateArgumentCount(String cmd, String[] args, int n) {
        if (args.length != n) {
            throw new RuntimeException(
                    String.format("Invalid number of arguments for: %s.", cmd));
        }
    }
}
