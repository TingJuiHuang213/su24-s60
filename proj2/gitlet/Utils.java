package gitlet;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    public static String sha1(Object... objs) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            for (Object obj : objs) {
                md.update(obj.toString().getBytes());
            }
            return byteArrayToHex(md.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static String byteArrayToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static byte[] serialize(Serializable obj) {
        try {
            ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
            ObjectOutputStream objStream = new ObjectOutputStream(byteStream);
            objStream.writeObject(obj);
            objStream.close();
            return byteStream.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static <T extends Serializable> T readObject(File file, Class<T> expectedClass) {
        try {
            ObjectInputStream objStream = new ObjectInputStream(new FileInputStream(file));
            T obj = expectedClass.cast(objStream.readObject());
            objStream.close();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void writeObject(File file, Serializable obj) {
        try {
            ObjectOutputStream objStream = new ObjectOutputStream(new FileOutputStream(file));
            objStream.writeObject(obj);
            objStream.close();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static File join(File first, String... others) {
        File result = first;
        for (String other : others) {
            result = new File(result, other);
        }
        return result;
    }

    public static byte[] readContents(File file) {
        try {
            return java.nio.file.Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static void writeContents(File file, byte[] bytes) {
        try {
            java.nio.file.Files.write(file.toPath(), bytes);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static boolean restrictedDelete(File file) {
        if (!file.delete()) {
            throw new IllegalArgumentException("Failed to delete file: " + file);
        }
        return true;
    }

    public static String readContentsAsString(File file) {
        return new String(readContents(file), StandardCharsets.UTF_8);
    }
}
