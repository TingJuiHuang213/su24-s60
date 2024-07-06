package gitlet;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Utils {
    public static byte[] serialize(Serializable obj) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(obj);
            objectStream.close();
            return stream.toByteArray();
        } catch (IOException excp) {
            throw new RuntimeException("Internal error serializing commit.");
        }
    }

    public static <T extends Serializable> T readObject(File file, Class<T> expectedClass) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
            T result = expectedClass.cast(in.readObject());
            in.close();
            return result;
        } catch (IOException | ClassNotFoundException excp) {
            throw new RuntimeException("Internal error deserializing commit.");
        }
    }

    public static void writeObject(File file, Serializable obj) {
        writeContents(file, serialize(obj));
    }

    public static void writeContents(File file, Object... contents) {
        try {
            if (file.isDirectory()) {
                throw new IllegalArgumentException("cannot overwrite directory");
            }
            BufferedOutputStream str = new BufferedOutputStream(Files.newOutputStream(file.toPath()));
            for (Object obj : contents) {
                if (obj instanceof byte[]) {
                    str.write((byte[]) obj);
                } else {
                    str.write(((String) obj).getBytes(StandardCharsets.UTF_8));
                }
            }
            str.close();
        } catch (IOException | ClassCastException excp) {
            throw new RuntimeException(excp);
        }
    }

    public static String sha1(Object... vals) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            for (Object val : vals) {
                if (val instanceof byte[]) {
                    md.update((byte[]) val);
                } else if (val instanceof String) {
                    md.update(((String) val).getBytes(StandardCharsets.UTF_8));
                } else {
                    throw new IllegalArgumentException("improper type to sha1");
                }
            }
            Formatter result = new Formatter();
            for (byte b : md.digest()) {
                result.format("%02x", b);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException excp) {
            throw new RuntimeException("System does not support SHA-1");
        }
    }

    public static void writeContents(File file, String contents) {
        try {
            Files.write(file.toPath(), contents.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
