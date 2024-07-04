package gitlet;

import static org.junit.Assert.*;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {
    @Test
    public void testInit() throws IOException {
        // 初始化Gitlet
        Main.main(new String[]{"init"});
        File gitletDir = new File(".gitlet");
        assertTrue(gitletDir.exists());
    }

    @Test
    public void testBasicRestore() throws IOException {
        // 初始化Gitlet并提交文件
        Main.main(new String[]{"init"});
        File testFile = new File("wug.txt");
        testFile.createNewFile();
        Utils.writeContents(testFile, "This is a wug.");
        Main.main(new String[]{"add", "wug.txt"});
        Main.main(new String[]{"commit", "added wug"});

        // 修改文件内容并执行restore
        Utils.writeContents(testFile, "This is not a wug.");
        Main.main(new String[]{"restore", "--", "wug.txt"});
        assertEquals("This is a wug.", Utils.readContentsAsString(testFile));

        // 清理
        testFile.delete();
    }

    @Test
    public void testBasicLog() throws IOException {
        // 初始化Gitlet并提交文件
        Main.main(new String[]{"init"});
        File testFile = new File("wug.txt");
        testFile.createNewFile();
        Utils.writeContents(testFile, "This is a wug.");
        Main.main(new String[]{"add", "wug.txt"});
        Main.main(new String[]{"commit", "added wug"});

        // 执行log命令
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Main.main(new String[]{"log"});
        String expectedOutput = "===\n"
                + "Commit "
                + Utils.sha1(Utils.readObject(
                new File(".gitlet/commits/"
                        + Repository.getHeadCommitId()
                        + "/commit"), Commit.class))
                + "\n"
                + Utils.readObject(
                new File(".gitlet/commits/"
                        + Repository.getHeadCommitId()
                        + "/commit"), Commit.class).getTimestamp()
                + "\n"
                + "added wug\n\n";
        assertTrue(outContent.toString().contains("added wug"));

        // 清理
        testFile.delete();
    }

    @Test
    public void testPrevRestore() throws IOException {
        // 初始化Gitlet并提交文件版本1
        Main.main(new String[]{"init"});
        File testFile = new File("wug.txt");
        testFile.createNewFile();
        Utils.writeContents(testFile, "version 1");
        Main.main(new String[]{"add", "wug.txt"});
        Main.main(new String[]{"commit", "version 1 of wug.txt"});

        // 提交文件版本2
        Utils.writeContents(testFile, "version 2");
        Main.main(new String[]{"add", "wug.txt"});
        Main.main(new String[]{"commit", "version 2 of wug.txt"});

        // 执行restore并检查文件内容
        String firstCommitId = Repository.getHeadCommitId(); // 修改这里
        Main.main(new String[]{"restore", firstCommitId, "--", "wug.txt"});
        assertEquals("version 1", Utils.readContentsAsString(testFile));

        // 清理
        testFile.delete();
    }

    private String getCommitIdByMessage(String message) {
        // 实现获取提交ID的方法，根据提交信息获取对应的ID
        return ""; // Placeholder, implement the method to retrieve the commit ID by message.
    }
}
