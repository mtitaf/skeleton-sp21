package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;



public class Utils {

    /** 将字符串转成long数字 */
    public static long safeParseLong(String str, long defaultValue) {
        if (str == null || str.trim().isEmpty()) {
            return defaultValue;
        }

        try {
            // 移除数字中的逗号（如 "1,000,000"）
            String cleaned = str.replace(",", "").trim();
            return Long.parseLong(cleaned);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /** 返回一个随机砖块 */
    public TETile randomTile(Random rand) {
        int tileNum = rand.nextInt(6);
        return switch (tileNum) {
            case 0 -> Tileset.WALL;
            case 1 -> Tileset.FLOWER;
            case 2 -> Tileset.GRASS;
            case 4 -> Tileset.AVATAR;
            case 5 -> Tileset.SAND;
            default -> Tileset.UNLOCKED_DOOR;
        };
    }

    public  void  sleep(float second) {
        int millis = (int) second * 1000;
        System.out.println("sleep" + millis + "ms");
        try {
            // 让当前线程睡眠1000毫秒（1秒）
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            // 当线程被中断时需要处理的异常
            Thread.currentThread().interrupt(); // 重新设置中断状态
            System.err.println("睡眠被中断: " + e.getMessage());
        }
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
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    public static byte[] serialize(Serializable obj) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            ObjectOutputStream objectStream = new ObjectOutputStream(stream);
            objectStream.writeObject(obj);
            objectStream.close();
            return stream.toByteArray();
        } catch (IOException excp) {
//            throw error("Internal error serializing commit.");
            return null;
        }
    }

    public static void writeObject(File file, Serializable obj) {
        writeContents(file, serialize(obj));
    }

    public static <T extends Serializable> T readObject(File file,
                                                 Class<T> expectedClass) {
        try {
            ObjectInputStream in =
                    new ObjectInputStream(new FileInputStream(file));
            T result = expectedClass.cast(in.readObject());
            in.close();
            return result;
        } catch (IOException | ClassCastException
                 | ClassNotFoundException excp) {
            throw new IllegalArgumentException(excp.getMessage());
        }
    }

    public static void saveGame(TETile[][] world, int width, int height) {
        File saveFile = new File("game.sav");
        SaveGame save = new SaveGame(world ,width, height);
        save.save();
    }

    /** Return the concatentation of FIRST and OTHERS into a File designator,
     *  analogous to the {@link java.nio.file.Paths.#get(String, String[])}
     *  method. */
    public static File join(String first, String... others) {
        return Paths.get(first, others).toFile();
    }

    /** Return the concatentation of FIRST and OTHERS into a File designator,
     *  analogous to the {@link java.nio.file.Paths.#get(String, String[])}
     *  method. */
    public static  File join(File first, String... others) {
        return Paths.get(first.getPath(), others).toFile();
    }

}
