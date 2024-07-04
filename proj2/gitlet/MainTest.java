
package gitlet;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static gitlet.Utils.*;

public class MainTest {
    @Test
    public void testInit() {
        File gitletDir = new File(".gitlet");
        if (gitletDir.exists()) {
            deleteDirectory(gitletDir);
        }

        Main.main(new String[]{"init"});
        assertTrue(gitletDir.exists());
        deleteDirectory(gitletDir);
    }

    @Test
    public void testAdd() throws IOException {
        // 初始化 Gitlet
        File gitletDir = new File(".gitlet");
        if (gitletDir.exists()) {
            deleteDirectory(gitletDir);
        }

        Main.main(new String[]{"init"});
        assertTrue(gitletDir.exists());

        // 创建一个文件
        File testFile = new File("test.txt");
        testFile.createNewFile();
        writeContents(testFile, "This is a test file.");

        // 执行 add 命令
        Main.main(new String[]{"add", "test.txt"});
        File stagedFile = new File(".gitlet/staging/test.txt");
        assertTrue(stagedFile.exists());
        assertEquals("This is a test file.", readContentsAsString(stagedFile));

        // 清理
        testFile.delete();
        deleteDirectory(gitletDir);
    }

    @Test
    public void testCommit() throws IOException {
        // 初始化 Gitlet
        File gitletDir = new File(".gitlet");
        if (gitletDir.exists()) {
            deleteDirectory(gitletDir);
        }

        Main.main(new String[]{"init"});
        assertTrue(gitletDir.exists());

        // 创建一个文件
        File testFile = new File("test.txt");
        testFile.createNewFile();
        writeContents(testFile, "This is a test file.");

        // 执行 add 和 commit 命令
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "Initial commit"});
        File commitDir = new File(".gitlet/commits");
        assertTrue(commitDir.exists());
        assertTrue(commitDir.listFiles().length > 0);

        // 检查提交内容
        File[] commits = commitDir.listFiles();
        assertTrue(commits.length == 1);
        File commit = commits[0];
        File committedFile = new File(commit, "test.txt");
        assertTrue(committedFile.exists());
        assertEquals("This is a test file.", readContentsAsString(committedFile));

        // 清理
        testFile.delete();
        deleteDirectory(gitletDir);
    }

    @Test
    public void testLog() throws IOException {
        // 初始化 Gitlet
        File gitletDir = new File(".gitlet");
        if (gitletDir.exists()) {
            deleteDirectory(gitletDir);
        }

        Main.main(new String[]{"init"});
        assertTrue(gitletDir.exists());

        // 创建一个文件
        File testFile = new File("test.txt");
        testFile.createNewFile();
        writeContents(testFile, "This is a test file.");

        // 执行 add 和 commit 命令
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "Initial commit"});

        // 执行 log 命令
        Main.main(new String[]{"log"});

        // 清理
        testFile.delete();
        deleteDirectory(gitletDir);
    }

    @Test
    public void testCheckout() throws IOException {
        // 初始化 Gitlet
        File gitletDir = new File(".gitlet");
        if (gitletDir.exists()) {
            deleteDirectory(gitletDir);
        }

        Main.main(new String[]{"init"});
        assertTrue(gitletDir.exists());

        // 创建一个文件
        File testFile = new File("test.txt");
        testFile.createNewFile();
        writeContents(testFile, "This is a test file.");

        // 执行 add 和 commit 命令
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "Initial commit"});
        File commitDir = new File(".gitlet/commits");
        assertTrue(commitDir.exists());

        // 获取提交ID
        String initialCommitId = commitDir.listFiles()[0].getName();
        System.out.println("Initial commit ID: " + initialCommitId);

        // 修改文件
        writeContents(testFile, "This is an updated test file.");
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "Updated commit"});

        // 检查修改后的文件内容
        assertEquals("This is an updated test file.", readContentsAsString(testFile));

        // 执行 checkout 命令
        Main.main(new String[]{"checkout", initialCommitId, "test.txt"});
        assertEquals("This is a test file.", readContentsAsString(testFile));

        // 清理
        testFile.delete();
        deleteDirectory(gitletDir);
    }

    @Test
    public void testRm() throws IOException {
        // 初始化 Gitlet
        File gitletDir = new File(".gitlet");
        if (gitletDir.exists()) {
            deleteDirectory(gitletDir);
        }

        Main.main(new String[]{"init"});
        assertTrue(gitletDir.exists());

        // 创建一个文件
        File testFile = new File("test.txt");
        testFile.createNewFile();
        writeContents(testFile, "This is a test file.");

        // 执行 add 和 commit 命令
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "Initial commit"});

        // 执行 rm 命令
        Main.main(new String[]{"rm", "test.txt"});

        // 检查文件是否被删除
        File removedFile = new File("test.txt");
        assertFalse(removedFile.exists());

        // 确认文件在 staging area 中被标记为移除
        File removalMarker = new File(".gitlet/staging/test.txt.remove");
        assertTrue(removalMarker.exists());

        // 清理
        deleteDirectory(gitletDir);
    }

    @Test
    public void testGlobalLog() throws IOException {
        // 初始化 Gitlet
        File gitletDir = new File(".gitlet");
        if (gitletDir.exists()) {
            deleteDirectory(gitletDir);
        }

        Main.main(new String[]{"init"});
        assertTrue(gitletDir.exists());

        // 创建一个文件并进行提交
        File testFile = new File("test.txt");
        testFile.createNewFile();
        writeContents(testFile, "This is a test file.");
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "First commit"});

        // 再次提交
        writeContents(testFile, "This is an updated test file.");
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "Second commit"});

        // 捕获global-log命令的输出
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Main.main(new String[]{"global-log"});

        // 验证输出是否包含所有提交的信息
        String output = outContent.toString();
        assertTrue(output.contains("First commit"));
        assertTrue(output.contains("Second commit"));

        // 清理
        testFile.delete();
        deleteDirectory(gitletDir);
    }

    @Test
    public void testFind() throws IOException {
        // 初始化 Gitlet
        File gitletDir = new File(".gitlet");
        if (gitletDir.exists()) {
            deleteDirectory(gitletDir);
        }

        Main.main(new String[]{"init"});
        assertTrue(gitletDir.exists());

        // 创建一个文件并进行提交
        File testFile = new File("test.txt");
        testFile.createNewFile();
        writeContents(testFile, "This is a test file.");
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "First commit"});

        // 再次提交
        writeContents(testFile, "This is an updated test file.");
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "Second commit"});

        // 捕获find命令的输出
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Main.main(new String[]{"find", "First commit"});

        // 验证输出是否包含提交的ID
        String output = outContent.toString().trim();
        assertTrue(output.length() > 0);

        // 捕获find命令的无效输出
        outContent.reset();
        Main.main(new String[]{"find", "Non-existent commit"});
        output = outContent.toString().trim();
        assertEquals("Found no commit with that message.", output);

        // 清理
        testFile.delete();
        deleteDirectory(gitletDir);
    }

    @Test
    public void testStatus() throws IOException {
        // 初始化 Gitlet
        File gitletDir = new File(".gitlet");
        if (gitletDir.exists()) {
            deleteDirectory(gitletDir);
        }

        Main.main(new String[]{"init"});
        assertTrue(gitletDir.exists());

        // 创建一个文件并进行暂存
        File testFile = new File("test.txt");
        testFile.createNewFile();
        writeContents(testFile, "This is a test file.");
        Main.main(new String[]{"add", "test.txt"});

        // 捕获status命令的输出
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Main.main(new String[]{"status"});

        // 验证输出是否包含暂存文件
        String output = outContent.toString();
        assertTrue(output.contains("=== Branches ==="));
        assertTrue(output.contains("* master"));
        assertTrue(output.contains("=== Staged Files ==="));
        assertTrue(output.contains("test.txt"));

        // 清理
        testFile.delete();
        deleteDirectory(gitletDir);
    }

    @Test
    public void testRestore() throws IOException {
        // 初始化 Gitlet
        File gitletDir = new File(".gitlet");
        if (gitletDir.exists()) {
            deleteDirectory(gitletDir);
        }

        Main.main(new String[]{"init"});
        assertTrue(gitletDir.exists());

        // 创建一个文件并进行提交
        File testFile = new File("test.txt");
        testFile.createNewFile();
        writeContents(testFile, "This is a test file.");
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "First commit"});

        // 获取第一个提交的ID
        File commitDir = new File(".gitlet/commits");
        String firstCommitId = commitDir.listFiles()[0].getName();

        // 修改文件
        writeContents(testFile, "This is an updated test file.");
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "Second commit"});

        // 打印调试信息
        System.out.println("First commit ID: " + firstCommitId);
        System.out.println("First commit content: " + readContentsAsString(new File(".gitlet/commits/" + firstCommitId + "/test.txt")));
        System.out.println("Current file content: " + readContentsAsString(testFile));

        // 执行 restore 命令
        Main.main(new String[]{"restore", firstCommitId, "test.txt"});

        // 验证文件内容是否已恢复到第一次提交的内容
        String restoredContent = readContentsAsString(testFile);
        System.out.println("Restored file content: " + restoredContent);
        assertEquals("This is a test file.", restoredContent);

        // 清理
        testFile.delete();
        deleteDirectory(gitletDir);
    }

    @Test
    public void testBranch() throws IOException {
        // 初始化 Gitlet
        File gitletDir = new File(".gitlet");
        if (gitletDir.exists()) {
            deleteDirectory(gitletDir);
        }

        Main.main(new String[]{"init"});
        assertTrue(gitletDir.exists());

        // 创建一个文件并进行提交
        File testFile = new File("test.txt");
        testFile.createNewFile();
        writeContents(testFile, "This is a test file.");
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "First commit"});

        // 执行 branch 命令
        Main.main(new String[]{"branch", "new-branch"});

        // 验证新分支是否存在
        File newBranchFile = new File(".gitlet/branches/new-branch");
        assertTrue(newBranchFile.exists());

        // 检查新分支指向的提交ID是否正确
        String currentCommitId = readContentsAsString(new File(".gitlet/branches/master"));
        String newBranchCommitId = readContentsAsString(newBranchFile);
        assertEquals(currentCommitId, newBranchCommitId);

        // 清理
        testFile.delete();
        deleteDirectory(gitletDir);
    }

    @Test
    public void testReset() throws IOException {
        // 初始化 Gitlet
        File gitletDir = new File(".gitlet");
        if (gitletDir.exists()) {
            deleteDirectory(gitletDir);
        }

        Main.main(new String[]{"init"});
        assertTrue(gitletDir.exists());

        // 创建一个文件并进行提交
        File testFile = new File("test.txt");
        testFile.createNewFile();
        writeContents(testFile, "This is a test file.");
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "First commit"});

        // 获取第一个提交的ID
        File commitDir = new File(".gitlet/commits");
        String firstCommitId = commitDir.listFiles()[0].getName();

        // 修改文件
        writeContents(testFile, "This is an updated test file.");
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "Second commit"});

        // 打印调试信息
        System.out.println("First commit ID: " + firstCommitId);
        System.out.println("First commit content: " + readContentsAsString(new File(".gitlet/commits/" + firstCommitId + "/test.txt")));
        System.out.println("Current file content: " + readContentsAsString(testFile));

        // 执行 reset 命令
        Main.main(new String[]{"reset", firstCommitId});

        // 验证当前提交是否已经重置为第一次提交
        String currentCommitId = readContentsAsString(new File(".gitlet/branches/master"));
        assertEquals(firstCommitId, currentCommitId);

        // 验证文件内容是否已恢复到第一次提交的内容
        String resetFileContent = readContentsAsString(testFile);
        System.out.println("Reset file content: " + resetFileContent);
        assertEquals("This is a test file.", resetFileContent);

        // 清理
        testFile.delete();
        deleteDirectory(gitletDir);
    }

    @Test
    public void testMerge() throws IOException {
        // 初始化 Gitlet
        File gitletDir = new File(".gitlet");
        if (gitletDir.exists()) {
            deleteDirectory(gitletDir);
        }

        Main.main(new String[]{"init"});
        assertTrue(gitletDir.exists());

        // 创建一个文件并进行提交
        File testFile = new File("test.txt");
        testFile.createNewFile();
        writeContents(testFile, "This is a test file.");
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "First commit"});

        // 获取第一个提交的ID
        File commitDir = new File(".gitlet/commits");
        String firstCommitId = commitDir.listFiles()[0].getName();

        // 修改文件
        writeContents(testFile, "This is an updated test file.");
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "Second commit"});

        // 创建新分支
        Main.main(new String[]{"branch", "new-branch"});
        // 切换到新分支
        Main.main(new String[]{"reset", firstCommitId});

        // 修改文件并提交到新分支
        writeContents(testFile, "This is a new branch test file.");
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "New branch commit"});

        // 切换回主分支并合并
        Main.main(new String[]{"reset", firstCommitId});
        Main.main(new String[]{"merge", "new-branch"});

        // 验证合并结果
        File mergedFile = new File("test.txt");
        assertTrue(mergedFile.exists());

        String mergedContent = readContentsAsString(mergedFile);
        System.out.println("Merged file content: " + mergedContent);

        boolean hasConflict = mergedContent.contains("<<<<<<< HEAD") &&
                mergedContent.contains("=======") &&
                mergedContent.contains(">>>>>>>");

        assertTrue(hasConflict);

        // 清理
        testFile.delete();
        deleteDirectory(gitletDir);
    }

    private void deleteDirectory(File directory) {
        File[] allContents = directory.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        directory.delete();
    }
}
