package gitlet;

import java.io.File;

public class Repository {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File GITLET_DIR = new File(CWD, ".gitlet");
    public static final File COMMITS_DIR = new File(GITLET_DIR, "commits");
    public static final File STAGING_DIR = new File(GITLET_DIR, "staging");
    public static final File HEAD = new File(GITLET_DIR, "HEAD");

    public static void init() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            return;
        }

        GITLET_DIR.mkdir();
        COMMITS_DIR.mkdir();
        STAGING_DIR.mkdir();

        Commit initialCommit = new Commit("initial commit");
        String initialCommitID = Utils.sha1(Utils.serialize(initialCommit));
        File initialCommitFile = new File(COMMITS_DIR, initialCommitID);
        Utils.writeObject(initialCommitFile, initialCommit);

        Utils.writeContents(HEAD, initialCommitID);
    }
}
