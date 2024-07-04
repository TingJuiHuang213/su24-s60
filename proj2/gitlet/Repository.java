package gitlet;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static gitlet.Utils.*;

public class Repository {

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    /** The staging area for added files. */
    private static final File STAGING_AREA = join(GITLET_DIR, "staging");
    /** The commits directory. */
    private static final File COMMITS_DIR = join(GITLET_DIR, "commits");

    // Staging area maps
    private static Map<String, String> stagingArea = new HashMap<>();

    // Initialize the repository
    public static void init() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }

        GITLET_DIR.mkdir();
        STAGING_AREA.mkdir();
        COMMITS_DIR.mkdir();

        Commit initialCommit = new Commit("initial commit", null);
        saveCommit(initialCommit);

        File head = join(GITLET_DIR, "HEAD");
        writeObject(head, initialCommit);
    }

    // Add file to staging area
    public static void add(String fileName) {
        File file = Utils.join(CWD, fileName);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            System.exit(0);
        }

        byte[] fileContent = Utils.readContents(file);
        String fileHash = Utils.sha1(fileContent);

        // Save file content to staging area
        File blob = Utils.join(STAGING_AREA, fileHash);
        if (!blob.exists()) {
            Utils.writeContents(blob, fileContent);
        }

        // Add to staging area map
        stagingArea.put(fileName, fileHash);
    }

    // Commit the staged files
    public static void commit(String message) {
        if (message.isEmpty()) {
            System.out.println("Please enter a commit message.");
            System.exit(0);
        }

        Commit currentCommit = loadCurrentCommit();
        Commit newCommit = new Commit(message, currentCommit.getId());

        // Transfer blobs from staging area to commit
        for (Map.Entry<String, String> entry : stagingArea.entrySet()) {
            newCommit.getBlobs().put(entry.getKey(), entry.getValue());
        }

        // Save the new commit
        saveCommit(newCommit);
        clearStagingArea();
    }

    // Restore file from the latest commit
    public static void restore(String fileName) {
        Commit currentCommit = loadCurrentCommit();
        String fileHash = currentCommit.getBlobs().get(fileName);
        if (fileHash == null) {
            System.out.println("File does not exist in that commit.");
            System.exit(0);
        }

        File blob = join(STAGING_AREA, fileHash);
        byte[] fileContent = readContents(blob);
        writeContents(join(CWD, fileName), fileContent);
    }

    // Restore file from a specific commit
    public static void restore(String commitId, String fileName) {
        File commitFile = join(COMMITS_DIR, commitId);
        if (!commitFile.exists()) {
            System.out.println("No commit with that id exists.");
            System.exit(0);
        }

        Commit commit = readObject(commitFile, Commit.class);
        String fileHash = commit.getBlobs().get(fileName);
        if (fileHash == null) {
            System.out.println("File does not exist in that commit.");
            System.exit(0);
        }

        File blob = join(STAGING_AREA, fileHash);
        byte[] fileContent = readContents(blob);
        writeContents(join(CWD, fileName), fileContent);
    }

    // Display the commit history
    public static void log() {
        Commit currentCommit = loadCurrentCommit();
        while (currentCommit != null) {
            System.out.println("===");
            System.out.println("Commit " + currentCommit.getId());
            System.out.println(currentCommit.getTimestamp());
            System.out.println(currentCommit.getMessage());
            System.out.println();

            String parentId = currentCommit.getParent();
            if (parentId == null) {
                break;
            }
            currentCommit = readObject(join(COMMITS_DIR, parentId), Commit.class);
        }
    }

    // Helper method to save a commit
    private static void saveCommit(Commit commit) {
        File commitFile = join(COMMITS_DIR, commit.getId());
        writeObject(commitFile, commit);
        writeObject(join(GITLET_DIR, "HEAD"), commit);
    }

    // Helper method to load the current commit
    private static Commit loadCurrentCommit() {
        return readObject(join(GITLET_DIR, "HEAD"), Commit.class);
    }

    // Helper method to clear the staging area
    private static void clearStagingArea() {
        stagingArea.clear();
        for (File file : STAGING_AREA.listFiles()) {
            if (!file.isDirectory()) {
                restrictedDelete(file);
            }
        }
    }
}
