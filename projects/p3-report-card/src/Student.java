// One student and their marks. Fields + constructor + methods (Chapter 3.8 and 3.9).
public class Student {
    private final String name;
    private final int rollNumber;
    private final int[] marks;          // one mark (0-100) per subject, same order as ReportCard.SUBJECTS

    public Student(String name, int rollNumber, int[] marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
    }

    public String getName() { return name; }
    public int getRollNumber() { return rollNumber; }
    public int getMark(int subjectIndex) { return marks[subjectIndex]; }
    public int subjectCount() { return marks.length; }

    public int total() {
        int sum = 0;
        for (int m : marks) sum += m;
        return sum;
    }

    public double average() {
        return GradeCalculator.average(marks);
    }

    public String grade() {
        return GradeCalculator.letterGrade(average());
    }

    public boolean hasPassedAll() {
        for (int m : marks) {
            if (!GradeCalculator.isPass(m)) return false;
        }
        return true;
    }
}
