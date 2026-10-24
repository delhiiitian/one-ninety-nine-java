// 6.4 Buffered reading and writing: line by line, for big files
// Run: java BufferedIO.java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BufferedIO {
    public static void main(String[] args) throws IOException {
        Path folder = Path.of("sandbox");
        Files.createDirectories(folder);
        Path log = folder.resolve("server.log");

        // Write 1,000 lines through a buffer: like filling a bucket and carrying it, not one cup at a time
        try (BufferedWriter out = Files.newBufferedWriter(log)) {
            for (int i = 1; i <= 1000; i++) {
                String level = (i % 250 == 0) ? "ERROR" : "INFO";
                out.write(level + " request " + i + " handled");
                out.newLine();
            }
        }   // try-with-resources flushes and closes the file

        // Read one line at a time: only one line is in memory, so it works for huge files
        int lineCount = 0, errors = 0;
        try (BufferedReader in = Files.newBufferedReader(log)) {
            String line;
            while ((line = in.readLine()) != null) {     // null means end of file
                lineCount++;
                if (line.startsWith("ERROR")) {
                    errors++;
                    System.out.println("Found: " + line);
                }
            }
        }
        System.out.println("Scanned " + lineCount + " lines, " + errors + " errors.");

        // Files.lines gives a stream of lines (more in Chapter 8)
        try (var lines = Files.lines(log)) {
            System.out.println("INFO lines: " + lines.filter(l -> l.startsWith("INFO")).count());
        }
    }
}
