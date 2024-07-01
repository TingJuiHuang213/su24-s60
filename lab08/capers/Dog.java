package capers;

import java.io.File;
import java.io.Serializable;

/** Represents a dog that can be serialized.
 * This class provides functionality to save and load a dog's state.
 * @authon Sean Dooher
 */
public class Dog implements Serializable { // 添加 implements Serializable

    /** Folder that dogs live in. */
    static final File DOG_DIRECTORY = new File(".capers/dogs"); // 初始化DOG_DIRECTORY

    /** Age of dog. */
    private int age;
    /** Breed of dog. */
    private String breed;
    /** Name of dog. */
    private String name;

    /**
     * Creates a dog object with the specified parameters.
     * @param name Name of dog
     * @param breed Breed of dog
     * @param age Age of dog
     */
    public Dog(String name, String breed, int age) {
        this.age = age;
        this.breed = breed;
        this.name = name;
    }

    /**
     * Reads in and deserializes a dog from a file with name NAME in DOG_DIRECTORY.
     *
     * @param name Name of dog to load
     * @return Dog read from file
     */
    public static Dog loadFromFile(String name) {
        File dogFile = new File(DOG_DIRECTORY, name); // 构造狗文件路径
        if (!dogFile.exists()) {
            return null; // 如果文件不存在，返回null
        }
        return Utils.readObject(dogFile, Dog.class); // 读取对象
    }

    /**
     * Increases a dog's age and celebrates!
     */
    public void haveBirthday() {
        age++;
        System.out.println(this);
        System.out.println("Happy birthday! Woof! Woof!");
    }

    /**
     * Saves a dog to a file for future use.
     */
    public void save() {
        File dogFile = new File(DOG_DIRECTORY, this.name); // 构造狗文件路径
        Utils.writeObject(dogFile, this); // 保存对象
    }

    @Override
    public String toString() {
        return String.format(
                "Woof! My name is %s and I am a %s! I am %d years old! Woof!",
                name, breed, age);
    }
}
