// 6.1 Paths and the Files class: addresses for your data
// Run: java PathsAndFiles.java
import java.nio.file.Files;
import java.nio.file.Path;

public class PathsAndFiles {
    public static void main(String[] args) {
        // A Path is like a postal address: it describes WHERE, it is not the house itself
        Path report = Path.of("reports", "2026", "october.txt");
        System.out.println("Relative path: " + report);
        System.out.println("File name:     " + report.getFileName());
        System.out.println("Parent folder: " + report.getParent());
        System.out.println("Parts:         " + report.getNameCount());

        // Absolute = full address starting from the root of the disk
        Path here = Path.of("").toAbsolutePath();
        System.out.println("Working directory ends with: " + here.getFileName());

        // resolve joins paths, like adding "Flat 4" to a street address
        Path base = Path.of("sandbox");
        Path notes = base.resolve("notes.txt");
        System.out.println("Resolved: " + notes);

        // A path can point to something that does not exist yet
        System.out.println("Exists? " + Files.exists(notes));
        System.out.println("This source file exists? " + Files.exists(Path.of("PathsAndFiles.java")));
        System.out.println("Is a directory? " + Files.isDirectory(Path.of(".")));

        // Clean up "." and ".." segments
        Path messy = Path.of("docs/./drafts/../final/plan.txt");
        System.out.println("Normalized: " + messy.normalize());
    }
}
