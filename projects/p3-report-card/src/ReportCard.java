// Project 3: Student Report Card
// Main program: reads the class from input, then prints one report per student and a class summary.
// Run: bash run.sh   (or with sample data: bash run.sh --sample)
import java.util.Scanner;

public class ReportCard {

    static final String[] SUBJECTS = {"Maths", "Science", "English", "History"};
    static final String SCHOOL = "Riverside High School";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Student[] students = readClass(in);
        in.close();

        for (Student s : students) {
            printReport(s);
        }
        printClassSummary(students);
    }

    static Student[] readClass(Scanner in) {
        System.out.print("How many students? ");
        int count = in.nextInt();
        Student[] students = new Student[count];
        for (int i = 0; i < count; i++) {
            in.nextLine();
            System.out.print("Student " + (i + 1) + " name: ");
            String name = in.nextLine().strip();
            int[] marks = new int[SUBJECTS.length];
            for (int j = 0; j < SUBJECTS.length; j++) {
                marks[j] = readMark(in, SUBJECTS[j]);
            }
            students[i] = new Student(name, 101 + i, marks);
        }
        System.out.println();
        return students;
    }

    // Keeps asking until the mark is between 0 and 100
    static int readMark(Scanner in, String subject) {
        while (true) {
            System.out.print("  " + subject + " mark: ");
            int mark = in.nextInt();
            if (mark >= 0 && mark <= 100) return mark;
            System.out.println("  Marks must be 0-100, try again.");
        }
    }

    static void printReport(Student s) {
        String line = "-".repeat(44);
        System.out.println(line);
        System.out.println(SCHOOL + " | Report Card");
        System.out.printf("Name: %-20s Roll No: %d%n", s.getName(), s.getRollNumber());
        System.out.println(line);
        for (int j = 0; j < s.subjectCount(); j++) {
            int mark = s.getMark(j);
            String status = GradeCalculator.isPass(mark) ? "" : "  << below pass mark";
            System.out.printf("%-9s %3d  %-11s%s%n", SUBJECTS[j], mark, GradeCalculator.bar(mark), status);
        }
        System.out.println(line);
        System.out.printf("Total %d / %d   Average %.1f   Grade %s%n",
                s.total(), s.subjectCount() * 100, s.average(), s.grade());
        System.out.println("Result: " + (s.hasPassedAll() ? "PROMOTED" : "NEEDS SUPPORT"));
        System.out.println();
    }

    static void printClassSummary(Student[] students) {
        double[] averages = new double[students.length];
        Student top = students[0];
        for (int i = 0; i < students.length; i++) {
            averages[i] = students[i].average();
            if (students[i].average() > top.average()) top = students[i];
        }
        System.out.println("=== Class summary ===");
        System.out.printf("Class average: %.1f%n", GradeCalculator.average(averages));
        System.out.println("Top student:   " + top.getName() + " (" + top.grade() + ")");
        for (int j = 0; j < SUBJECTS.length; j++) {
            int[] column = new int[students.length];
            for (int i = 0; i < students.length; i++) column[i] = students[i].getMark(j);
            System.out.printf("Highest in %-8s %d%n", SUBJECTS[j] + ":", GradeCalculator.highest(column, 0));
        }
    }
}
