// Project 6: Expense Tracker
// A command-line tool that saves every expense in a CSV file, so data survives between runs.
//   add 2026-10-03 Food 12.50 Lunch with Sam
//   list [YYYY-MM]
//   summary [YYYY-MM]
//   delete N
//   export [YYYY-MM]
// The data file is data/expenses.csv, or the path in the EXPENSES_FILE environment variable.
// The monthly budget comes from config.properties (budget=...).
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Properties;

public class ExpenseTracker {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: add DATE CATEGORY AMOUNT NOTE | list [MONTH] | summary [MONTH] | delete N | export [MONTH]");
            return;
        }
        Path file = Path.of(System.getenv().getOrDefault("EXPENSES_FILE", "data/expenses.csv"));
        ExpenseStore store = new ExpenseStore(file);
        try {
            run(args, store, loadBudget(Path.of("config.properties")));
            store.getWarnings().forEach(w -> System.out.println("Warning: " + w));
        } catch (IOException e) {
            System.out.println("File problem: " + e.getMessage());
            System.exit(1);
        } catch (DateTimeParseException e) {
            System.out.println("Dates look like 2026-10-03");
            System.exit(2);
        } catch (NumberFormatException e) {
            System.out.println("Amounts and numbers must be digits, e.g. 12.50");
            System.exit(2);
        }
    }

    static void run(String[] args, ExpenseStore store, double budget) throws IOException {
        String month = args.length > 1 ? args[1] : null;
        switch (args[0]) {
            case "add" -> {
                if (args.length < 4) throw new NumberFormatException();
                String note = args.length > 4 ? String.join(" ", List.of(args).subList(4, args.length)) : "";
                Expense e = new Expense(LocalDate.parse(args[1]), capitalise(args[2]), Double.parseDouble(args[3]), note);
                store.append(e);
                System.out.printf("Saved: %s %s %.2f %s%n", e.date(), e.category(), e.amount(), e.note());
            }
            case "list" -> {
                List<Expense> all = store.loadAll();
                for (int i = 0; i < all.size(); i++) {
                    Expense e = all.get(i);
                    if (month == null || e.date().toString().startsWith(month)) {
                        System.out.printf("%3d  %s  %-10s %8.2f  %s%n", i + 1, e.date(), e.category(), e.amount(), e.note());
                    }
                }
            }
            case "summary" -> System.out.print(Report.summary(store.loadAll(), month, budget));
            case "delete" -> {
                List<Expense> all = store.loadAll();
                int n = Integer.parseInt(args[1]);
                if (n < 1 || n > all.size()) {
                    System.out.println("No entry number " + n);
                    return;
                }
                Expense removed = all.remove(n - 1);
                store.saveAll(all);
                System.out.println("Deleted: " + removed.toCsv() + " (backup kept as " + store.getFile().getFileName() + ".bak)");
            }
            case "export" -> {
                Path out = Report.export(store.loadAll(), month, budget, store.getFile().resolveSibling("reports"));
                System.out.println("Report written to " + out);
            }
            default -> System.out.println("Unknown command: " + args[0]);
        }
    }

    static double loadBudget(Path config) throws IOException {
        if (!Files.exists(config)) return 0;
        Properties p = new Properties();
        try (Reader r = Files.newBufferedReader(config)) {
            p.load(r);
        }
        return Double.parseDouble(p.getProperty("budget", "0"));
    }

    static String capitalise(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
    }
}
