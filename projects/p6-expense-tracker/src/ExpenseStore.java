import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

// Reads and writes expenses to a CSV file. The rest of the program never touches files directly.
public class ExpenseStore {
    private final Path file;
    private final List<String> warnings = new ArrayList<>();

    public ExpenseStore(Path file) {
        this.file = file;
    }

    public List<Expense> loadAll() throws IOException {
        List<Expense> list = new ArrayList<>();
        if (!Files.exists(file)) return list;                    // first run: nothing saved yet
        try (BufferedReader in = Files.newBufferedReader(file)) {
            String line;
            int lineNo = 0;
            while ((line = in.readLine()) != null) {
                lineNo++;
                if (lineNo == 1 || line.isBlank()) continue;     // skip header and empty lines
                try {
                    list.add(Expense.fromCsv(line));
                } catch (RuntimeException e) {                   // bad date, bad number, missing column
                    warnings.add("line " + lineNo + " skipped (" + e.getClass().getSimpleName() + ")");
                }
            }
        }
        return list;
    }

    // Appending is cheap and safe: existing lines are never rewritten (Chapter 6.5)
    public void append(Expense e) throws IOException {
        if (file.getParent() != null) Files.createDirectories(file.getParent());
        if (!Files.exists(file)) {
            Files.writeString(file, Expense.HEADER + System.lineSeparator());
        }
        Files.writeString(file, e.toCsv() + System.lineSeparator(), StandardOpenOption.APPEND);
    }

    // Deleting rewrites the file, so a backup copy is taken first (Chapter 6.8)
    public void saveAll(List<Expense> all) throws IOException {
        if (Files.exists(file)) {
            Files.copy(file, file.resolveSibling(file.getFileName() + ".bak"), StandardCopyOption.REPLACE_EXISTING);
        }
        List<String> lines = new ArrayList<>();
        lines.add(Expense.HEADER);
        for (Expense e : all) lines.add(e.toCsv());
        Files.write(file, lines);
    }

    public List<String> getWarnings() { return warnings; }
    public Path getFile() { return file; }
}
