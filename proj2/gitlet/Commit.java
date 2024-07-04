package gitlet;

import java.util.Date; // TODO: You'll likely use this in this class

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Commit {
    /** The message of this Commit. */
    private String message;
    private Date timestamp;

    public Commit(String message) {
        this.message = message;
        this.timestamp = new Date();
    }

    @Override
    public String toString() {
        return "===\n" +
                "Commit " + this.hashCode() + "\n" +
                "Date: " + timestamp + "\n" +
                message + "\n";
    }
}
