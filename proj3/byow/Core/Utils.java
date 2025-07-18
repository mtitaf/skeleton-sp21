package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

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
}
