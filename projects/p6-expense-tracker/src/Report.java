import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Turns a list of expenses into summaries a person can read.
public class Report {

    public static Map<String, Double> byCategory(List<Expense> list, String month) {
        Map<String, Double> totals = new TreeMap<>();
        for (Expense e : list) {
            if (month == null || e.date().toString().startsWith(month)) {
                totals.merge(e.category(), e.amount(), Double::sum);
            }
        }
        return totals;
    }

    public static String summary(List<Expense> list, String month, double budget) {
        Map<String, Double> totals = byCategory(list, month);
        double sum = 0;
        for (double v : totals.values()) sum += v;

        StringBuilder sb = new StringBuilder();
        sb.append("Summary for ").append(month == null ? "all time" : month).append('\n');
        for (Map.Entry<String, Double> t : totals.entrySet()) {
            int bar = sum == 0 ? 0 : (int) Math.round(t.getValue() / sum * 20);
            sb.append(String.format("  %-10s %8.2f  %s%n", t.getKey(), t.getValue(), "|".repeat(bar)));
        }
        sb.append(String.format("  %-10s %8.2f%n", "TOTAL", sum));
        if (budget > 0) {
            double left = budget - sum;
            sb.append(left >= 0
                    ? String.format("  Within budget: %.2f left of %.2f%n", left, budget)
                    : String.format("  OVER BUDGET by %.2f%n", -left));
        }
        return sb.toString();
    }

    // Writes a small text report file, e.g. to email or print
    public static Path export(List<Expense> list, String month, double budget, Path folder) throws IOException {
        Files.createDirectories(folder);
        Path out = folder.resolve("report-" + (month == null ? "all" : month) + ".txt");
        List<String> lines = new ArrayList<>(List.of(summary(list, month, budget).split("\n")));
        lines.add("");
        lines.add("Entries:");
        for (Expense e : list) {
            if (month == null || e.date().toString().startsWith(month)) {
                lines.add(String.format("  %s  %-10s %8.2f  %s", e.date(), e.category(), e.amount(), e.note()));
            }
        }
        Files.write(out, lines);
        return out;
    }
}
