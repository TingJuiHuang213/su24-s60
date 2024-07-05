package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import static gitlet.Utils.*;

/** Represents a gitlet commit object.
 *  This class handles commit creation, saving, and retrieval.
 */
public class Commit implements Serializable {
    private String message;
    private String parent;
    private Map<String, byte[]> files;

    public Commit(String message, String parent) {
        this.message = message;
        this.parent = parent;
        this.files = new HashMap<>();
    }

    public String getMessage() {
        return message;
    }

    public Map<String, byte[]> getFiles() {
        return files;
    }

    public void addFile(String fileName, byte[] fileContent) {
        files.put(fileName, fileContent);
    }

    public String saveCommit() {
        String commitID = sha1(serialize(this));
        File commitFile = join(Repository.COMMITS_DIR, commitID);
        writeObject(commitFile, this);
        return commitID;
    }

    public static Commit fromFile(String commitID) {
        File commitFile = join(Repository.COMMITS_DIR, commitID);
        return readObject(commitFile, Commit.class);
    }
}
