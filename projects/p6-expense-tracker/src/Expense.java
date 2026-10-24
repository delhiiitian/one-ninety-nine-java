import java.time.LocalDate;

// One spending entry, plus the rules for turning it into a CSV line and back (Chapter 6.6).
public record Expense(LocalDate date, String category, double amount, String note) {

    public static final String HEADER = "date,category,amount,note";

    public String toCsv() {
        // Commas inside the note would break the columns, so they are replaced
        return date + "," + category + "," + String.format("%.2f", amount) + "," + note.replace(",", ";");
    }

    public static Expense fromCsv(String line) {
        String[] cols = line.split(",", 4);          // at most 4 pieces: the note keeps everything after
        if (cols.length < 3) throw new IllegalArgumentException("Too few columns: " + line);
        return new Expense(
                LocalDate.parse(cols[0].trim()),
                cols[1].trim(),
                Double.parseDouble(cols[2].trim()),
                cols.length == 4 ? cols[3].trim() : "");
    }
}
