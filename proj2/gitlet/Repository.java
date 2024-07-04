package gitlet;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import static gitlet.Utils.*;

public class Repository {
    public static final File CWD = new File(System.getProperty("user.dir"));
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    public static final File STAGING_DIR = join(GITLET_DIR, "staging");
    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");
    public static final File BRANCHES_DIR = join(GITLET_DIR, "branches");

    public static void init() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            return;
        }

        GITLET_DIR.mkdir();
        STAGING_DIR.mkdir();
        COMMITS_DIR.mkdir();
        BRANCHES_DIR.mkdir();

        // 进一步初始化设置
        String initialCommitId = generateCommitId("initial commit");
        writeContents(join(BRANCHES_DIR, "master"), initialCommitId);
        writeContents(join(GITLET_DIR, "HEAD"), "master");
    }

    public static void add(String fileName) {
        File fileToAdd = new File(CWD, fileName);
        if (!fileToAdd.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        File stagedFile = new File(STAGING_DIR, fileName);
        writeContents(stagedFile, readContents(fileToAdd));
    }

    public static void commit(String message) {
        File[] stagedFiles = STAGING_DIR.listFiles();
        if (stagedFiles == null || stagedFiles.length == 0) {
            System.out.println("No changes added to the commit.");
            return;
        }

        String commitId = generateCommitId(message);
        File newCommitDir = join(COMMITS_DIR, commitId);
        newCommitDir.mkdir();

        for (File file : stagedFiles) {
            File newFile = join(newCommitDir, file.getName());
            writeContents(newFile, readContents(file));
            file.delete();
        }

        writeContents(join(newCommitDir, "message.txt"), message);
        writeContents(join(newCommitDir, "timestamp.txt"), new Date().toString());

        // 更新当前分支
        String currentBranch = getCurrentBranch();
        writeContents(join(BRANCHES_DIR, currentBranch), commitId);
    }

    private static String generateCommitId(String message) {
        return sha1(message + new Date().toString());
    }

    public static void log() {
        File[] commits = COMMITS_DIR.listFiles();
        if (commits == null || commits.length == 0) {
            System.out.println("No commits yet.");
            return;
        }

        for (File commit : commits) {
            String commitId = commit.getName();
            String message = readContentsAsString(join(commit, "message.txt"));
            String timestamp = readContentsAsString(join(commit, "timestamp.txt"));
            System.out.println("===");
            System.out.println("Commit " + commitId);
            System.out.println(timestamp);
            System.out.println(message);
            System.out.println();
        }
    }

    public static void globalLog() {
        File[] commits = COMMITS_DIR.listFiles();
        if (commits == null || commits.length == 0) {
            System.out.println("No commits yet.");
            return;
        }

        for (File commit : commits) {
            String commitId = commit.getName();
            String message = readContentsAsString(join(commit, "message.txt"));
            String timestamp = readContentsAsString(join(commit, "timestamp.txt"));
            System.out.println("===");
            System.out.println("Commit " + commitId);
            System.out.println(timestamp);
            System.out.println(message);
            System.out.println();
        }
    }

    public static void find(String message) {
        File[] commits = COMMITS_DIR.listFiles();
        if (commits == null || commits.length == 0) {
            System.out.println("No commits yet.");
            return;
        }

        boolean found = false;
        for (File commit : commits) {
            String commitMessage = readContentsAsString(join(commit, "message.txt"));
            if (commitMessage.equals(message)) {
                System.out.println(commit.getName());
                found = true;
            }
        }

        if (!found) {
            System.out.println("Found no commit with that message.");
        }
    }

    public static void checkout(String commitId, String fileName) {
        File commitDir = join(COMMITS_DIR, commitId);
        if (!commitDir.exists()) {
            System.out.println("No commit with that id exists.");
            return;
        }

        File fileInCommit = join(commitDir, fileName);
        if (!fileInCommit.exists()) {
            System.out.println("File does not exist in that commit.");
            return;
        }

        writeContents(join(CWD, fileName), readContents(fileInCommit));
    }

    public static void rm(String fileName) {
        File fileToRemove = new File(CWD, fileName);
        File stagedFile = new File(STAGING_DIR, fileName);
        File trackedFile = join(COMMITS_DIR, getCurrentCommitId(), fileName);

        boolean isStaged = stagedFile.exists();
        boolean isTracked = trackedFile.exists();

        if (!isStaged && !isTracked) {
            System.out.println("No reason to remove the file.");
            return;
        }

        if (isStaged) {
            stagedFile.delete();
        }

        if (isTracked) {
            // 标记文件为已移除
            File removalMarker = new File(STAGING_DIR, fileName + ".remove");
            writeContents(removalMarker, "");

            if (fileToRemove.exists()) {
                fileToRemove.delete();
            }
        }
    }

    private static String getCurrentCommitId() {
        String currentBranch = getCurrentBranch();
        return Utils.readContentsAsString(new File(".gitlet/branches/" + currentBranch)).trim();
    }

    private static String getCurrentBranch() {
        return Utils.readContentsAsString(new File(".gitlet/HEAD")).trim();
    }

    // 获取当前HEAD提交的ID
    public static String getHeadCommitId() {
        String currentBranch = getCurrentBranch();
        return Utils.readContentsAsString(new File(".gitlet/branches/" + currentBranch)).trim();
    }

    public static void status() {
        System.out.println("=== Branches ===");
        // 显示分支信息
        // 这里你可以根据实际需求实现，例如读取一个存储分支信息的文件
        // 比如当前分支是 master:
        System.out.println("* " + getCurrentBranch());

        System.out.println("\n=== Staged Files ===");
        // 显示暂存文件
        List<String> stagedFiles = plainFilenamesIn(STAGING_DIR);
        if (stagedFiles != null) {
            for (String fileName : stagedFiles) {
                System.out.println(fileName);
            }
        }

        System.out.println("\n=== Removed Files ===");
        // 显示已标记为删除的文件
        List<String> removedFiles = new ArrayList<>();
        if (stagedFiles != null) {
            for (String fileName : stagedFiles) {
                if (fileName.endsWith(".remove")) {
                    removedFiles.add(fileName.substring(0, fileName.length() - 7));
                }
            }
        }
        for (String fileName : removedFiles) {
            System.out.println(fileName);
        }

        System.out.println("\n=== Modifications Not Staged For Commit ===");
        // 显示修改但未暂存的文件
        // 你可以根据实际需求实现

        System.out.println("\n=== Untracked Files ===");
        // 显示未跟踪的文件
        // 你可以根据实际需求实现
    }

    // 单参数版本的 restore 方法
    public static void restore(String fileName) {
        String headCommitId = getHeadCommitId();
        restore(headCommitId, fileName);
    }

    // 双参数版本的 restore 方法
    public static void restore(String commitId, String fileName) {
        File commitDir = join(COMMITS_DIR, commitId);
        if (!commitDir.exists()) {
            System.out.println("No commit with that id exists.");
            return;
        }

        File fileInCommit = join(commitDir, fileName);
        if (!fileInCommit.exists()) {
            System.out.println("File does not exist in that commit.");
            return;
        }

        System.out.println("Restoring file: " + fileName + " from commit: " + commitId);
        String content = readContentsAsString(fileInCommit);
        System.out.println("File content: " + content);

        writeContents(join(CWD, fileName), content);
    }

    public static void branch(String branchName) {
        String currentCommitId = getHeadCommitId();
        writeContents(join(BRANCHES_DIR, branchName), currentCommitId);
        System.out.println("Created branch " + branchName + " at commit " + currentCommitId);
    }

    public static void reset(String commitId) {
        File commitDir = join(COMMITS_DIR, commitId);
        if (!commitDir.exists()) {
            System.out.println("No commit with that id exists.");
            return;
        }

        // 更新当前分支的 HEAD 指针
        String currentBranch = getCurrentBranch();
        writeContents(join(BRANCHES_DIR, currentBranch), commitId);

        // 更新工作目录和暂存区
        for (String fileName : plainFilenamesIn(CWD)) {
            restrictedDelete(join(CWD, fileName));
        }
        for (File file : commitDir.listFiles()) {
            if (file.isFile() && !file.getName().equals("message.txt") && !file.getName().equals("timestamp.txt")) {
                writeContents(join(CWD, file.getName()), readContents(file));
            }
        }
        for (File file : STAGING_DIR.listFiles()) {
            file.delete();
        }
    }

    public static void merge(String branchName) {
        // 获取当前分支和目标分支的最新提交ID
        String currentBranch = getCurrentBranch();
        String currentCommitId = getHeadCommitId();
        String targetCommitId = readContentsAsString(join(BRANCHES_DIR, branchName));

        // 获取所有提交文件
        File currentCommitDir = join(COMMITS_DIR, currentCommitId);
        File targetCommitDir = join(COMMITS_DIR, targetCommitId);

        List<String> currentFiles = plainFilenamesIn(currentCommitDir);
        List<String> targetFiles = plainFilenamesIn(targetCommitDir);

        if (currentFiles == null || targetFiles == null) {
            System.out.println("No commits found in one of the branches.");
            return;
        }

        // 检查是否存在冲突文件
        boolean conflict = false;
        for (String fileName : targetFiles) {
            File currentFile = join(currentCommitDir, fileName);
            File targetFile = join(targetCommitDir, fileName);
            File workingFile = join(CWD, fileName);

            if (currentFile.exists() && !readContentsAsString(currentFile).equals(readContentsAsString(targetFile))) {
                conflict = true;
                // 添加冲突标记
                String currentContent = readContentsAsString(currentFile);
                String targetContent = readContentsAsString(targetFile);
                String mergedContent = "<<<<<<< HEAD\n" + currentContent + "=======\n" + targetContent + ">>>>>>>\n";
                writeContents(workingFile, mergedContent);
            } else {
                // 如果没有冲突，直接覆盖工作目录中的文件
                writeContents(workingFile, readContents(targetFile));
            }
        }

        // 更新HEAD指针指向新的提交
        if (!conflict) {
            String newCommitId = generateCommitId("Merged " + branchName + " into " + currentBranch);
            File newCommitDir = join(COMMITS_DIR, newCommitId);
            newCommitDir.mkdir();

            for (File file : currentCommitDir.listFiles()) {
                if (file.isFile()) {
                    writeContents(join(newCommitDir, file.getName()), readContents(file));
                }
            }

            for (File file : targetCommitDir.listFiles()) {
                if (file.isFile()) {
                    writeContents(join(newCommitDir, file.getName()), readContents(file));
                }
            }

            writeContents(join(newCommitDir, "message.txt"), "Merged " + branchName + " into " + currentBranch);
            writeContents(join(newCommitDir, "timestamp.txt"), new Date().toString());

            writeContents(join(BRANCHES_DIR, currentBranch), newCommitId);
        } else {
            System.out.println("Encountered a merge conflict.");
        }
    }
}
