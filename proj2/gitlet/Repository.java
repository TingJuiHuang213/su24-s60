package gitlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class Repository {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File GITLET_DIR = new File(CWD, ".gitlet");
    public static final File COMMITS_DIR = new File(GITLET_DIR, "commits");
    public static final File STAGING_DIR = new File(GITLET_DIR, "staging");
    public static final File HEAD = new File(GITLET_DIR, "HEAD");

    public void init() {
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

    public void add(String fileName) {
        File fileToAdd = new File(CWD, fileName);
        if (!fileToAdd.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        File stagingFile = new File(STAGING_DIR, fileName);
        try {
            Files.copy(fileToAdd.toPath(), stagingFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void commit(String message) {
        if (message.isEmpty()) {
            System.out.println("Please enter a commit message.");
            return;
        }

        String parentCommitID = Utils.readContentsAsString(HEAD);
        Commit parentCommit = Utils.readObject(new File(COMMITS_DIR, parentCommitID), Commit.class);

        Map<String, String> fileMap = new HashMap<>(parentCommit.getFileMap());

        for (File file : STAGING_DIR.listFiles()) {
            String fileName = file.getName();
            String fileID = Utils.sha1(Utils.readContents(file));
            fileMap.put(fileName, fileID);

            File commitFile = new File(COMMITS_DIR, fileID);
            try {
                Files.copy(file.toPath(), commitFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Commit newCommit = new Commit(message, parentCommitID, fileMap);
        String newCommitID = Utils.sha1(Utils.serialize(newCommit));
        File newCommitFile = new File(COMMITS_DIR, newCommitID);
        Utils.writeObject(newCommitFile, newCommit);

        Utils.writeContents(HEAD, newCommitID);

        for (File file : STAGING_DIR.listFiles()) {
            file.delete();
        }
    }

    public void restore(String fileName) {
        String commitID = Utils.readContentsAsString(HEAD);
        Commit commit = Utils.readObject(new File(COMMITS_DIR, commitID), Commit.class);
        if (!commit.getFileMap().containsKey(fileName)) {
            System.out.println("File does not exist in that commit.");
            return;
        }
        String fileID = commit.getFileMap().get(fileName);
        File sourceFile = new File(COMMITS_DIR, fileID);
        File destFile = new File(CWD, fileName);
        try {
            Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void restore(String commitID, String fileName) {
        Commit commit = Utils.readObject(new File(COMMITS_DIR, commitID), Commit.class);
        if (!commit.getFileMap().containsKey(fileName)) {
            System.out.println("File does not exist in that commit.");
            return;
        }
        String fileID = commit.getFileMap().get(fileName);
        File sourceFile = new File(COMMITS_DIR, fileID);
        File destFile = new File(CWD, fileName);
        try {
            Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log() {
        String commitID = Utils.readContentsAsString(HEAD);
        while (commitID != null && !commitID.isEmpty()) {
            File commitFile = new File(COMMITS_DIR, commitID);
            Commit commit = Utils.readObject(commitFile, Commit.class);

            System.out.println("===");
            System.out.println("commit " + commitID.toLowerCase());
            System.out.println("Date: " + commit.getTimestamp());
            System.out.println(commit.getMessage());
            System.out.println();

            commitID = commit.getParent();
        }
    }

    public void rm(String fileName) {
        File fileToRemove = new File(CWD, fileName);
        File stagedFile = new File(STAGING_DIR, fileName);

        if (!fileToRemove.exists() && !stagedFile.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        if (stagedFile.exists()) {
            stagedFile.delete();
        }

        String commitID = Utils.readContentsAsString(HEAD);
        Commit commit = Utils.readObject(new File(COMMITS_DIR, commitID), Commit.class);

        if (commit.getFileMap().containsKey(fileName)) {
            Utils.writeContents(new File(STAGING_DIR, "removed_" + fileName), ""); // Mark as removed
        }
    }

    public void status() {
        System.out.println("=== Branches ===");
        System.out.println("*main");
        System.out.println();
        System.out.println("=== Staged Files ===");
        for (File file : STAGING_DIR.listFiles()) {
            if (!file.getName().startsWith("removed_")) {
                System.out.println(file.getName());
            }
        }
        System.out.println();
        System.out.println("=== Removed Files ===");
        for (File file : STAGING_DIR.listFiles()) {
            if (file.getName().startsWith("removed_")) {
                System.out.println(file.getName().substring(8));
            }
        }
        System.out.println();
        System.out.println("=== Modifications Not Staged For Commit ===");
        // 这里可以添加逻辑来检查未暂存的修改文件
        System.out.println();
        System.out.println("=== Untracked Files ===");
        // 这里可以添加逻辑来检查未追踪的文件
        System.out.println();
    }

    private void printCommit(Commit commit) {
        System.out.println("===");
        System.out.println("Commit " + Utils.sha1(Utils.serialize(commit)));
        System.out.println(commit.getTimestamp());
        System.out.println(commit.getMessage());
        System.out.println();
    }
}
