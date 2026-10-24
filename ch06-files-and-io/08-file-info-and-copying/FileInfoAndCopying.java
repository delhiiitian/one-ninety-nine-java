// 6.8 File information, copying, moving and renaming
// Run: java FileInfoAndCopying.java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class FileInfoAndCopying {
    public static void main(String[] args) throws IOException {
        Path folder = Path.of("sandbox");
        Files.createDirectories(folder.resolve("backup"));
        Path original = folder.resolve("contract.txt");
        Files.writeString(original, "This agreement is between Alice and Bob.\n");

        // Information about a file
        System.out.println("Size: " + Files.size(original) + " bytes");
        System.out.println("Readable? " + Files.isReadable(original));
        System.out.println("Regular file? " + Files.isRegularFile(original));
        System.out.println("Has a modified time? " + (Files.getLastModifiedTime(original) != null));

        // Copy: like photocopying a document; REPLACE_EXISTING overwrites an older copy
        Path copy = folder.resolve("backup/contract-copy.txt");
        Files.copy(original, copy, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Copied. Same contents? " + (Files.mismatch(original, copy) == -1));

        // Move is also how you rename
        Path renamed = folder.resolve("contract-signed.txt");
        Files.move(original, renamed, StandardCopyOption.REPLACE_EXISTING);
        System.out.println("Old name exists? " + Files.exists(original) + ", new name exists? " + Files.exists(renamed));

        // Delete safely
        boolean deleted = Files.deleteIfExists(copy);
        System.out.println("Backup deleted? " + deleted);
    }
}
