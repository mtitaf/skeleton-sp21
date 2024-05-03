package gitlet;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {

    public static String currentTime() {
        // 创建一个SimpleDateFormat对象，指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy Z");

        // 创建一个Date对象，表示当前时间
        Date date = new Date();

        // 使用SimpleDateFormat对象格式化日期
        String formattedDate = sdf.format(date);

        // 输出格式化后的日期字符串
        return formattedDate;
    }

    public static void main(String[] args) {
        System.out.println(currentTime());
    }
}
