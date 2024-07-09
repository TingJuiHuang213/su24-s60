package gitlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Repository {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File GITLET_DIR = new File(CWD, ".gitlet");
    public static final File COMMITS_DIR = new File(GITLET_DIR, "commits");
    public static final File STAGING_DIR = new File(GITLET_DIR, "staging");
    public static final File BLOBS_DIR = new File(GITLET_DIR, "blobs");
    public static final File BRANCHES_DIR = new File(GITLET_DIR, "branches");
    public static final File HEAD = new File(GITLET_DIR, "HEAD");

    public void init() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            return;
        }
        createDirectories(GITLET_DIR, COMMITS_DIR, STAGING_DIR, BLOBS_DIR, BRANCHES_DIR);
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

        String headCommitID = Utils.readContentsAsString(HEAD);
        Commit headCommit = Utils.readObject(new File(COMMITS_DIR, headCommitID), Commit.class);
        String fileID = Utils.sha1(Utils.readContents(fileToAdd));

        // Check if the file is already tracked in the current commit
        if (fileID.equals(headCommit.getFileMap().get(fileName))) {
            return; // No changes added to the staging area
        }

        File stagingFile = new File(STAGING_DIR, fileName);
        try {
            Files.copy(fileToAdd.toPath(), stagingFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void commit(String message) {
        if (message == null || message.trim().isEmpty()) {
            System.out.println("Please enter a commit message.");
            return;
        }

        // Check if there are no changes to commit
        if (STAGING_DIR.listFiles().length == 0) {
            System.out.println("No changes added to the commit.");
            return;
        }

        String parentCommitID = Utils.readContentsAsString(HEAD);
        Commit parentCommit = Utils.readObject(new File(COMMITS_DIR, parentCommitID), Commit.class);
        Map<String, String> fileMap = new HashMap<>(parentCommit.getFileMap());
        processStagingArea(fileMap);
        Commit newCommit = new Commit(message, parentCommitID, fileMap);
        String newCommitID = Utils.sha1(Utils.serialize(newCommit));
        File newCommitFile = new File(COMMITS_DIR, newCommitID);
        Utils.writeObject(newCommitFile, newCommit);
        Utils.writeContents(HEAD, newCommitID);
        clearStagingArea();
    }

    private void processStagingArea(Map<String, String> fileMap) {
        for (File file : STAGING_DIR.listFiles()) {
            String fileName = file.getName();
            String fileID = Utils.sha1(Utils.readContents(file));
            fileMap.put(fileName, fileID);
            File blobFile = new File(BLOBS_DIR, fileID); // 修正路徑
            try {
                Files.copy(file.toPath(), blobFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void restore(String commitID, String fileName) {
        File commitFile = new File(COMMITS_DIR, commitID);
        if (!commitFile.exists()) {
            System.out.println("No commit with that id exists.");
            return;
        }
        Commit commit = Utils.readObject(commitFile, Commit.class);
        if (!commit.getFileMap().containsKey(fileName)) {
            System.out.println("File does not exist in that commit.");
            return;
        }
        String fileID = commit.getFileMap().get(fileName);
        File sourceFile = new File(BLOBS_DIR, fileID); // 修正路徑
        File destFile = new File(CWD, fileName);
        try {
            Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void restore(String fileName) {
        String headCommitID = Utils.readContentsAsString(HEAD);
        restore(headCommitID, fileName);
    }

    public void log() {
        String commitID = Utils.readContentsAsString(HEAD);
        while (commitID != null && !commitID.isEmpty()) {
            File commitFile = new File(COMMITS_DIR, commitID);
            Commit commit = Utils.readObject(commitFile, Commit.class);
            printCommit(commit, commitID);
            commitID = commit.getParent();
        }
    }

    public void rm(String fileName) {
        File fileToRemove = new File(CWD, fileName);
        File stagedFile = new File(STAGING_DIR, fileName);

        // Check if the file exists in the current commit or staging area
        String headCommitID = Utils.readContentsAsString(HEAD);
        Commit headCommit = Utils.readObject(new File(COMMITS_DIR, headCommitID), Commit.class);
        boolean isTracked = headCommit.getFileMap().containsKey(fileName);
        boolean isStaged = stagedFile.exists();

        if (!isTracked && !isStaged) {
            System.out.println("No reason to remove the file.");
            return;
        }

        if (isStaged) {
            stagedFile.delete();
        }

        if (isTracked) {
            Utils.writeContents(new File(STAGING_DIR, "removed_" + fileName), ""); // Mark as removed
            if (fileToRemove.exists()) {
                fileToRemove.delete();
            }
        }
    }


    public void status() {
        System.out.println("=== Branches ===");
        String currentBranch = Utils.readContentsAsString(HEAD);
        for (File branch : Objects.requireNonNull(BRANCHES_DIR.listFiles())) {
            if (branch.getName().equals(currentBranch)) {
                System.out.print("*");
            }
            System.out.println(branch.getName());
        }
        System.out.println();

        System.out.println("=== Staged Files ===");
        for (File file : Objects.requireNonNull(STAGING_DIR.listFiles())) {
            if (!file.getName().startsWith("removed_")) {
                System.out.println(file.getName());
            }
        }
        System.out.println();

        System.out.println("=== Removed Files ===");
        for (File file : Objects.requireNonNull(STAGING_DIR.listFiles())) {
            if (file.getName().startsWith("removed_")) {
                System.out.println(file.getName().substring(8));
            }
        }
        System.out.println();

        System.out.println("=== Modifications Not Staged For Commit ===");
        for (File file : Objects.requireNonNull(CWD.listFiles())) {
            if (!file.isDirectory() && !isStaged(file.getName()) && isModified(file)) {
                System.out.println(file.getName());
            }
        }
        System.out.println();

        System.out.println("=== Untracked Files ===");
        for (File file : Objects.requireNonNull(CWD.listFiles())) {
            if (!file.isDirectory() && !isTracked(file.getName()) && !isStaged(file.getName())) {
                System.out.println(file.getName());
            }
        }
        System.out.println();
    }

    private boolean isStaged(String fileName) {
        File stagedFile = new File(STAGING_DIR, fileName);
        return stagedFile.exists();
    }

    private boolean isTracked(String fileName) {
        String headCommitID = Utils.readContentsAsString(HEAD);
        Commit headCommit = Utils.readObject(new File(COMMITS_DIR, headCommitID), Commit.class);
        return headCommit.getFileMap().containsKey(fileName);
    }

    private boolean isModified(File file) {
        String headCommitID = Utils.readContentsAsString(HEAD);
        Commit headCommit = Utils.readObject(new File(COMMITS_DIR, headCommitID), Commit.class);
        String trackedFileID = headCommit.getFileMap().get(file.getName());
        if (trackedFileID == null) {
            return false;
        }
        String currentFileID = Utils.sha1(Utils.readContents(file));
        return !trackedFileID.equals(currentFileID);
    }

    public void globalLog() {
        for (File commitFile : Objects.requireNonNull(COMMITS_DIR.listFiles())) {
            try {
                Commit commit = Utils.readObject(commitFile, Commit.class);
                printCommit(commit, commitFile.getName());
            } catch (RuntimeException e) {
                System.out.println("Error deserializing commit: " + commitFile.getName());
            }
        }
    }

    public void reset(String commitID) {
        File commitFile = new File(COMMITS_DIR, commitID);
        if (!commitFile.exists()) {
            System.out.println("No commit with that id exists.");
            return;
        }
        Commit commit = Utils.readObject(commitFile, Commit.class);
        for (Map.Entry<String, String> entry : commit.getFileMap().entrySet()) {
            String fileName = entry.getKey();
            String fileID = entry.getValue();
            File sourceFile = new File(BLOBS_DIR, fileID);
            File destFile = new File(CWD, fileName);
            try {
                Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Utils.writeContents(HEAD, commitID);
    }

    public void find(String message) {
        boolean found = false;
        for (File commitFile : Objects.requireNonNull(COMMITS_DIR.listFiles())) {
            Commit commit = Utils.readObject(commitFile, Commit.class);
            if (commit.getMessage().equals(message)) {
                System.out.println(commitFile.getName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Found no commit with that message.");
        }
    }

    public void checkout(String commitId, String fileName) {
        // implement the checkout command logic
        File commitFile = new File(COMMITS_DIR, commitId);
        if (!commitFile.exists()) {
            System.out.println("No commit with that id exists.");
            return;
        }
        Commit commit = Utils.readObject(commitFile, Commit.class);
        if (!commit.getFileMap().containsKey(fileName)) {
            System.out.println("File does not exist in that commit.");
            return;
        }
        String fileID = commit.getFileMap().get(fileName);
        File sourceFile = new File(BLOBS_DIR, fileID);
        File destFile = new File(CWD, fileName);
        try {
            Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkout(String fileName) {
        checkout(Utils.readContentsAsString(HEAD), fileName);
    }

    public void checkoutBranch(String branchName) {
        File branchFile = new File(BRANCHES_DIR, branchName);
        if (!branchFile.exists()) {
            System.out.println("No such branch exists.");
            return;
        }
        String branchCommitID = Utils.readContentsAsString(branchFile);
        reset(branchCommitID);
        Utils.writeContents(HEAD, branchCommitID);
    }

    public void branch(String branchName) {
        File branchFile = new File(BRANCHES_DIR, branchName);
        if (branchFile.exists()) {
            System.out.println("A branch with that name already exists.");
            return;
        }
        String headCommitID = Utils.readContentsAsString(HEAD);
        Utils.writeContents(branchFile, headCommitID);
    }

    public void rmBranch(String branchName) {
        File branchFile = new File(BRANCHES_DIR, branchName);
        if (!branchFile.exists()) {
            System.out.println("A branch with that name does not exist.");
            return;
        }
        if (branchName.equals("main")) {
            System.out.println("Cannot remove the current branch.");
            return;
        }
        branchFile.delete();
    }

    public void merge(String branchName) {
        // implement the merge command logic
    }

    private void printCommit(Commit commit, String commitID) {
        System.out.println("===");
        System.out.println("commit " + commitID);
        System.out.println("Date: " + commit.getTimestamp());
        System.out.println(commit.getMessage());
        System.out.println();
    }

    private void createDirectories(File... dirs) {
        for (File dir : dirs) {
            if (!dir.mkdirs()) {
                throw new RuntimeException("Failed to create directory: " + dir.getPath());
            }
        }
    }

    private void clearStagingArea() {
        for (File file : STAGING_DIR.listFiles()) {
            if (!file.delete()) {
                throw new RuntimeException("Failed to delete file in staging area: " + file.getPath());
            }
        }
    }
}
