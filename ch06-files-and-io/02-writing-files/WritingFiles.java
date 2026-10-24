// 6.2 Writing text files
// Run: java WritingFiles.java   (creates sandbox/shopping.txt)
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WritingFiles {
    public static void main(String[] args) throws IOException {
        Path folder = Path.of("sandbox");
        Files.createDirectories(folder);                 // make the folder if needed

        // Write one string (creates the file, or REPLACES its contents)
        Path note = folder.resolve("note.txt");
        Files.writeString(note, "Remember to water the plants.\n");
        System.out.println("Wrote " + note + " (" + Files.size(note) + " bytes)");

        // Write a list of lines
        Path shopping = folder.resolve("shopping.txt");
        List<String> items = List.of("rice", "lentils", "spinach", "yoghurt");
        Files.write(shopping, items);
        System.out.println("Wrote " + items.size() + " lines to " + shopping);

        // Writing again overwrites the old contents
        Files.writeString(note, "Plants watered.\n");
        System.out.println("note.txt now says: " + Files.readString(note).strip());
    }
}
