package gitlet;

import java.io.Serializable;
import java.util.Date;

public class Commit implements Serializable {
    private String message;
    private Date timestamp;

    public Commit(String message) {
        this.message = message;
        this.timestamp = new Date(0);  // Unix Epoch time
    }
}
