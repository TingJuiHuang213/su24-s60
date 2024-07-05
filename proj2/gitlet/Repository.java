package gitlet;

import java.io.File;
import java.io.IOException;
import static gitlet.Utils.*;

/** Represents a gitlet repository.
 * This class handles all the functionalities related to the gitlet repository.
 * It includes methods for initializing the repository, handling commits, branches, and more.
 */
public class Repository {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");
    public static final File STAGING_DIR = join(GITLET_DIR, "staging");
    public static final File HEAD_FILE = join(GITLET_DIR, "HEAD");

    public static void init() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            return;
        }
        GITLET_DIR.mkdir();
        COMMITS_DIR.mkdir();
        STAGING_DIR.mkdir();
        try {
            HEAD_FILE.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Commit initialCommit = new Commit("initial commit", null);
        String initialCommitID = initialCommit.saveCommit();
        writeContents(HEAD_FILE, initialCommitID);
    }

    public static void add(String fileName) {
        File fileToAdd = join(CWD, fileName);
        if (!fileToAdd.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        byte[] fileContent = readContents(fileToAdd);
        File stagedFile = join(STAGING_DIR, fileName);
        writeContents(stagedFile, fileContent);
    }

    public static void commit(String message) {
        String headCommitID = readContentsAsString(HEAD_FILE);
        Commit headCommit = Commit.fromFile(headCommitID);

        Commit newCommit = new Commit(message, headCommitID);
        for (String fileName : plainFilenamesIn(STAGING_DIR)) {
            File stagedFile = join(STAGING_DIR, fileName);
            byte[] fileContent = readContents(stagedFile);
            newCommit.addFile(fileName, fileContent);
        }

        String newCommitID = newCommit.saveCommit();
        writeContents(HEAD_FILE, newCommitID);

        for (String fileName : plainFilenamesIn(STAGING_DIR)) {
            restrictedDelete(join(STAGING_DIR, fileName));
        }

        // 打印提交 ID
        System.out.println("Commit ID: " + newCommitID);
    }


    public static void restore(String arg, String arg1) {
    }
}
