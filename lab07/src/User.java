import java.util.Arrays;
import java.util.Objects;

/** Represents an example of how a website might model a user. */
public class User implements Comparable<User> {
    private static int nextId = 1;

    private static final int AGE_MODULUS = 13;
    private static final int AGE_CONSTANT = 20;

    private int id;
    private int age;
    private String name;
    private String email;

    public User(String name, String email) {
        this(generateNextId(), name, email);
    }

    /** Force assign an id to a created user **/
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        setAge();
    }

    /** Generate the next user ID **/
    private static int generateNextId() {
        int currentId = nextId;
        nextId += 1;
        return currentId;
    }

    /** For this assignment, age is just an automatically assigned field. */
    private void setAge() {
        this.age = calculateAge(this.id);
    }

    /** Calculate age based on the user ID */
    private int calculateAge(int id) {
        return (id % AGE_MODULUS) + AGE_CONSTANT;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + "}";
    }

    /** Returns whether or not two Users are considered equal to each other. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User other = (User) o;
        return id == other.id &&
                Objects.equals(name, other.name) &&
                Objects.equals(email, other.email);
    }

    @Override
    public int compareTo(User other) {
        int idComparison = Integer.compare(this.id, other.id);
        return (idComparison != 0) ? idComparison : this.name.compareTo(other.name);
    }

    public static void main(String[] args) {
        User[] users = {
                new User(2, "Erik", ""),
                new User(4, "Vanessa", ""),
                new User(5, "Natalia", ""),
                new User(1, "Alex", ""),
                new User(1, "Circle", "")
        };
        Arrays.sort(users);
        // 调整输出循环的顺序
        for (User user : users) {
            System.out.println(user);
        }
    }
}
