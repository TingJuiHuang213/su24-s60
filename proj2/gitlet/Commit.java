package gitlet;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Commit implements Serializable {
    private String message;
    private String parent;
    private Date timestamp;
    private Map<String, String> blobs;

    public Commit(String message, String parent) {
        this.message = message;
        this.parent = parent;
        this.timestamp = new Date();
        this.blobs = new HashMap<>();
    }

    public String getMessage() {
        return message;
    }

    public String getParent() {
        return parent;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Map<String, String> getBlobs() {
        return blobs;
    }

    public String getId() {
        return null;
    }
}
