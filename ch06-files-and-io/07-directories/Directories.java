// 6.7 Working with folders: create, list, walk, delete
// Run: java Directories.java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Directories {
    public static void main(String[] args) throws IOException {
        Path photos = Path.of("sandbox", "photos");
        // createDirectories makes every missing folder in the chain (like mkdir -p)
        Files.createDirectories(photos.resolve("2025"));
        Files.createDirectories(photos.resolve("2026"));
        Files.writeString(photos.resolve("2025/beach.jpg"), "fake image");
        Files.writeString(photos.resolve("2026/hike.jpg"), "fake image");
        Files.writeString(photos.resolve("2026/notes.txt"), "trip notes");

        // List only the direct children
        System.out.println("Inside photos/:");
        try (Stream<Path> children = Files.list(photos)) {
            children.sorted().forEach(p -> System.out.println("  " + p.getFileName() + (Files.isDirectory(p) ? "/" : "")));
        }

        // Walk the whole tree and find all .jpg files
        try (Stream<Path> all = Files.walk(photos)) {
            List<Path> jpgs = all.filter(p -> p.toString().endsWith(".jpg")).sorted().toList();
            System.out.println("JPG files found: " + jpgs.size());
            jpgs.forEach(p -> System.out.println("  " + photos.relativize(p)));
        }

        // Delete a folder: it must be empty, so delete deepest items first
        try (Stream<Path> all = Files.walk(photos)) {
            all.sorted(Comparator.reverseOrder()).forEach(p -> {
                try {
                    Files.delete(p);
                } catch (IOException e) {
                    System.out.println("Could not delete " + p);
                }
            });
        }
        System.out.println("photos/ still exists? " + Files.exists(photos));
    }
}
