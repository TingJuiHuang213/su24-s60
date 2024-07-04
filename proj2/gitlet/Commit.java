package gitlet;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/** Represents a gitlet commit object.
 *  This class manages the commit object, storing information such as the commit message, timestamp, parent commit ID, and a map of file blobs.
 */
public class Commit implements Serializable {

    private static final long serialVersionUID = 1L;
    private String message;
    private String timestamp;
    private String parent;
    private String id;
    private Map<String, String> blobs;

    // Constructor for initial commit
    public Commit(String message, String parent) {
        this.message = message;
        this.parent = parent;
        this.timestamp = generateTimestamp();
        this.blobs = new HashMap<>();
        this.id = generateId();
    }

    // Generate the commit ID
    private String generateId() {
        return Utils.sha1(message, timestamp, parent == null ? "" : parent, blobs.toString());
    }

    // Generate the timestamp
    private String generateTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
        return dateFormat.format(new Date());
    }

    // Getters
    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getParent() {
        return parent;
    }

    public String getId() {
        return id;
    }

    public Map<String, String> getBlobs() {
        return blobs;
    }
}
