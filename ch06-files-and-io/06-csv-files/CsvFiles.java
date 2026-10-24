// 6.6 CSV files: spreadsheets as plain text
// Run: java CsvFiles.java   (reads students.csv, writes sandbox/report.csv)
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvFiles {

    record Student(String name, int math, int science) {
        double average() {
            return (math + science) / 2.0;
        }
    }

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("students.csv"));
        List<Student> students = new ArrayList<>();

        // Skip the header row (index 0), split each line on commas
        for (int i = 1; i < lines.size(); i++) {
            String[] cols = lines.get(i).split(",");
            try {
                students.add(new Student(cols[0].trim(), Integer.parseInt(cols[1].trim()), Integer.parseInt(cols[2].trim())));
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Skipping bad row " + (i + 1) + ": " + lines.get(i));
            }
        }

        // Write a new CSV with a computed column
        List<String> out = new ArrayList<>();
        out.add("name,average,result");
        for (Student s : students) {
            String result = s.average() >= 60 ? "PASS" : "FAIL";
            out.add(String.format("%s,%.1f,%s", s.name(), s.average(), result));
        }
        Files.createDirectories(Path.of("sandbox"));
        Files.write(Path.of("sandbox", "report.csv"), out);

        out.forEach(System.out::println);
        // Real CSV can contain quoted commas ("Smith, John"). For that, use a library such as OpenCSV.
    }
}
