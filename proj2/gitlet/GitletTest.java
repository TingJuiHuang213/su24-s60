package gitlet;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;

public class GitletTest {

    @Test
    public void testInit() throws IOException {
        // 清理环境
        File gitletDir = new File(".gitlet");
        deleteDirectory(gitletDir);

        // 执行 init 命令
        Main.main(new String[]{"init"});

        // 验证结果
        assertTrue(gitletDir.exists());

        // 验证 commits 目录
        File commitsDir = new File(".gitlet/commits");
        assertTrue(commitsDir.exists());
        File[] commitFiles = commitsDir.listFiles();
        assertNotNull(commitFiles);
        assertEquals(1, commitFiles.length);

        // 验证 HEAD 文件
        File head = new File(".gitlet/HEAD");
        assertTrue(head.exists());

        // 验证初始提交的内容
        Commit headCommit = Utils.readObject(head, Commit.class);
        assertEquals("initial commit", headCommit.getMessage());
        assertTrue(headCommit.getBlobs().isEmpty());
    }

    // 删除目录的方法
    private void deleteDirectory(File dir) {
        if (dir.exists()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteDirectory(file);
                    } else {
                        file.delete();
                    }
                }
            }
            dir.delete();
        }
    }

    // 其他测试用例可以按照类似的方式编写

    @Test
    public void testAdd() throws IOException {
        // 初始化环境
        File gitletDir = new File(".gitlet");
        deleteDirectory(gitletDir);
        if (!gitletDir.exists()) {
            Main.main(new String[]{"init"});
        }

        // 创建一个文件并添加内容
        File testFile = new File("test.txt");
        Utils.writeContents(testFile, "This is a test file.".getBytes());
        Main.main(new String[]{"add", "test.txt"});

        // 验证文件是否已添加到暂存区
        File stagingArea = new File(".gitlet/staging");
        File[] stagedFiles = stagingArea.listFiles();
        assertNotNull(stagedFiles);
        assertEquals(1, stagedFiles.length);
        assertTrue(stagedFiles[0].getName().matches("[0-9a-f]{40}"));
    }

    @Test
    public void testCommit() throws IOException {
        // 初始化环境
        File gitletDir = new File(".gitlet");
        deleteDirectory(gitletDir);
        if (!gitletDir.exists()) {
            Main.main(new String[]{"init"});
        }

        // 创建一个文件并添加内容
        File testFile = new File("test.txt");
        Utils.writeContents(testFile, "This is a test file.".getBytes());
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "Add test.txt"});

        // 验证新的提交是否已创建
        File commitsDir = new File(".gitlet/commits");
        File[] commitFiles = commitsDir.listFiles();
        assertNotNull(commitFiles);
        assertEquals(2, commitFiles.length);

        // 验证HEAD是否更新
        File head = new File(".gitlet/HEAD");
        assertTrue(head.exists());

        Commit headCommit = Utils.readObject(head, Commit.class);
        assertEquals("Add test.txt", headCommit.getMessage());
        assertTrue(headCommit.getBlobs().containsKey("test.txt"));
    }

    @Test
    public void testRestore() throws IOException {
        // 初始化环境
        File gitletDir = new File(".gitlet");
        deleteDirectory(gitletDir);
        if (!gitletDir.exists()) {
            Main.main(new String[]{"init"});
        }

        // 创建一个文件并添加内容
        File testFile = new File("test.txt");
        Utils.writeContents(testFile, "This is a test file.".getBytes());
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "Add test.txt"});

        // 修改文件内容
        Utils.writeContents(testFile, "This is modified content.".getBytes());
        Main.main(new String[]{"restore", "--", "test.txt"});

        // 验证文件内容是否恢复到初始提交的状态
        String fileContent = Utils.readContentsAsString(testFile);
        assertEquals("This is a test file.", fileContent);
    }

    @Test
    public void testLog() throws IOException {
        // 初始化环境
        File gitletDir = new File(".gitlet");
        deleteDirectory(gitletDir);
        if (!gitletDir.exists()) {
            Main.main(new String[]{"init"});
        }

        // 创建一个文件并添加内容
        File testFile = new File("test.txt");
        Utils.writeContents(testFile, "This is a test file.".getBytes());
        Main.main(new String[]{"add", "test.txt"});
        Main.main(new String[]{"commit", "Add test.txt"});

        // 执行log命令并捕获输出
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Main.main(new String[]{"log"});

        System.setOut(originalOut);

        // 验证log输出
        String logOutput = outContent.toString();
        assertTrue(logOutput.contains("Add test.txt"));
        assertTrue(logOutput.contains("initial commit"));
    }
}
