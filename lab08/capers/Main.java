package capers;

import java.io.File;

/**
 * Canine Capers: A delightful directory-based dog simulation.
 */
public class Main {

    /**
     * Current Working Directory.
     */
    public static final File CWD = new File(System.getProperty("user.dir"));

    /**
     * Main metadata folder.
     */
    public static final File CAPERS_FOLDER = Utils.join(CWD, ".capers"); // FIXME

    /**
     * Does a specific command based on ARGS.
     * @param args The command and arguments
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Must have at least one argument");
            System.exit(1);
        }

        setupPersistence();

        switch (args[0]) {
            case "story":
                writeStory(args);
                break;
            case "dog":
                makeNewDog(args);
                break;
            case "birthday":
                celebrateBirthday(args);
                break;
            default:
                System.out.println("Unknown command");
                System.exit(1);
        }
    }

    /**
     * Sets up the persistence directories.
     */
    public static void setupPersistence() {
        // 初始化CAPERS_FOLDER和DOG_FOLDER
        if (!CAPERS_FOLDER.exists()) {
            CAPERS_FOLDER.mkdirs();
        }
        if (!Dog.DOG_FOLDER.exists()) {
            Dog.DOG_FOLDER.mkdirs();
        }
    }

    /**
     * Appends the first non-command argument to a story file in the main folder.
     * @param args The command and arguments
     */
    public static void writeStory(String[] args) {
        Utils.validateNumArgs("story", args, 2);
        File storyFile = Utils.join(CAPERS_FOLDER, "story.txt");
        String story = args[1] + "\n";

        // 如果文件已經存在，則讀取現有內容並追加新內容
        if (storyFile.exists()) {
            String existingStory = Utils.readContentsAsString(storyFile);
            story = existingStory + story;
        }

        // 將內容寫入文件
        Utils.writeContents(storyFile, story);
        System.out.println(story);
    }

    /**
     * Creates a new dog object and saves it to a file.
     * @param args The command and arguments
     */
    public static void makeNewDog(String[] args) {
        Utils.validateNumArgs("dog", args, 4);
        String name = args[1];
        String breed = args[2];
        int age = Integer.parseInt(args[3]);

        // 創建新狗對象並保存
        Dog newDog = new Dog(name, breed, age);
        newDog.save();
        System.out.println(newDog);
    }

    /**
     * Loads a dog from a file, has it celebrate its birthday, and then saves it back to the file.
     * @param args The command and arguments
     */
    public static void celebrateBirthday(String[] args) {
        Utils.validateNumArgs("birthday", args, 2);
        Dog dog = Dog.fromFile(args[1]);
        dog.haveBirthday();
        dog.save();
    }
}
