// 6.5 Appending to a file: a simple diary/log
// Run: java AppendingFiles.java   (run it twice and watch the file grow)
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class AppendingFiles {
    public static void main(String[] args) throws IOException {
        Path folder = Path.of("sandbox");
        Files.createDirectories(folder);
        Path diary = folder.resolve("diary.txt");
        Files.deleteIfExists(diary);            // start fresh for this demo

        addEntry(diary, "Mon: started learning file I/O");
        addEntry(diary, "Tue: wrote my first log file");
        addEntry(diary, "Wed: appended without losing old lines");

        List<String> all = Files.readAllLines(diary);
        System.out.println("Diary has " + all.size() + " entries:");
        all.forEach(e -> System.out.println("  " + e));
    }

    static void addEntry(Path file, String entry) throws IOException {
        // CREATE: make the file if missing; APPEND: add to the end instead of replacing
        Files.writeString(file, entry + System.lineSeparator(),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }
}
