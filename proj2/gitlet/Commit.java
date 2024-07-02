package gitlet;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/** Represents a gitlet commit object.
 *  This class encapsulates the properties of a commit in a version control system.
 *  Each commit has a message, a timestamp, a reference to its parent commit,
 *  and a map of file blobs representing the state of files at the time of the commit.
 */
public class Commit implements Serializable {
    /** The message of this Commit. */
    private String message;

    /** The timestamp of this Commit. */
    private Date timestamp;

    /** The parent commit's SHA-1 ID. */
    private String parent;

    /** The map of file blobs in this Commit. */
    private Map<String, String> blobs;

    /** Constructs a new Commit object.
     * @param message The commit message.
     * @param parent The SHA-1 ID of the parent commit.
     */
    public Commit(String message, String parent) {
        this.message = message;
        this.timestamp = new Date();  // Current time for new commits
        this.parent = parent;
        this.blobs = new HashMap<>();
    }

    /** Returns the SHA-1 ID of this Commit.
     * @return The SHA-1 ID.
     */
    public String getId() {
        // Using SHA-1 hash to generate a unique ID based on the commit's properties
        return Utils.sha1(message, timestamp.toString(), parent == null ? "" : parent, blobs.toString());
    }

    // Getter and setter methods for instance variables if needed
    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getParent() {
        return parent;
    }

    public Map<String, String> getBlobs() {
        return blobs;
    }

    public void setBlobs(Map<String, String> blobs) {
        this.blobs = blobs;
    }
}
