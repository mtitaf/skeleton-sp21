package gitlet;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class fun {
    public static void main(String[] args) throws IOException {
        File f = new File("gitlet/fun.java");
        System.out.println(f);
        f.createNewFile();
    }
}
