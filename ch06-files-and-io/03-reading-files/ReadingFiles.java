// 6.3 Reading text files
// Run: java ReadingFiles.java   (reads poem.txt in this folder)
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

public class ReadingFiles {
    public static void main(String[] args) throws IOException {
        Path poem = Path.of("poem.txt");

        // Whole file as one String
        String text = Files.readString(poem);
        System.out.println("Characters: " + text.length());

        // Whole file as a list of lines
        List<String> lines = Files.readAllLines(poem);
        System.out.println("Lines: " + lines.size());
        for (int i = 0; i < lines.size(); i++) {
            System.out.printf("%2d | %s%n", i + 1, lines.get(i));
        }

        // Count words
        int words = 0;
        for (String line : lines) {
            if (!line.isBlank()) {
                words += line.trim().split("\\s+").length;
            }
        }
        System.out.println("Words: " + words);

        // A missing file throws NoSuchFileException (a kind of IOException)
        try {
            Files.readString(Path.of("missing.txt"));
        } catch (NoSuchFileException e) {
            System.out.println("Could not find: " + e.getMessage());
        }
    }
}
