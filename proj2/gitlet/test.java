package gitlet;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class test {
    public static void main(String[] args) throws IOException {
        Files f = new Files("gitlet/fun.java");
        System.out.println();
//        f.createNewFile();
    }
}
