package gitlet;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Commit implements Serializable {
    private String message;
    private String timestamp;
    private String parent;
    private Map<String, String> fileMap;

    public Commit(String message) {
        this.message = message;
        this.timestamp = getCurrentTimestamp();
        this.parent = null;
        this.fileMap = new HashMap<>();
    }

    public Commit(String message, String parent, Map<String, String> fileMap) {
        this.message = message;
        this.timestamp = getCurrentTimestamp();
        this.parent = parent;
        this.fileMap = fileMap;
    }

    private String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy Z", Locale.ENGLISH);
        return sdf.format(new Date());
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getParent() {
        return parent;
    }

    public Map<String, String> getFileMap() {
        return fileMap;
    }
}