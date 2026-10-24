// 5.5 Checked vs unchecked exceptions
// Run: java CheckedVsUnchecked.java
import java.io.FileReader;
import java.io.IOException;

public class CheckedVsUnchecked {
    public static void main(String[] args) {
        // CHECKED (e.g. IOException): things outside your control, like a missing file.
        // The compiler FORCES you to handle or declare them.
        try {
            readFirstChar("does-not-exist.txt");
        } catch (IOException e) {
            System.out.println("Checked exception handled: " + e.getClass().getSimpleName());
        }

        // UNCHECKED (RuntimeException and its children): usually programming mistakes.
        // The compiler does not force you to catch them; fix the code instead.
        int[] data = {1, 2, 3};
        int index = 3;
        if (index < data.length) {              // prevent rather than catch
            System.out.println(data[index]);
        } else {
            System.out.println("Index " + index + " is out of range; avoided the crash.");
        }

        System.out.println("Is IOException a RuntimeException? "
                + RuntimeException.class.isAssignableFrom(IOException.class));
        System.out.println("Is NullPointerException a RuntimeException? "
                + RuntimeException.class.isAssignableFrom(NullPointerException.class));
    }

    // 'throws IOException' warns every caller: this method may fail this way
    static char readFirstChar(String path) throws IOException {
        try (FileReader reader = new FileReader(path)) {
            return (char) reader.read();
        }
    }
}
