package com.shane.game.load;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;

public class ResourceManager {
    public static void save(Serializable data, String fileName) throws Throwable {
        Throwable throwable = null;
        Object var3_4 = null;
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName, new String[0]), new OpenOption[0]));){
            oos.writeObject(data);
        }
        catch (Throwable throwable2) {
            if (throwable == null) {
                throwable = throwable2;
            } else if (throwable != throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
    }

    public static Object load(String fileName) throws Throwable {
        Throwable throwable = null;
        Object var2_3 = null;
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName, new String[0]), new OpenOption[0]));){
            return ois.readObject();
        }
        catch (Throwable throwable2) {
            if (throwable == null) {
                throwable = throwable2;
            } else if (throwable != throwable2) {
                throwable.addSuppressed(throwable2);
            }
            throw throwable;
        }
    }
}