# Project 3: Student Report Card

Reads a class of students and their marks in four subjects, then prints a report card per student (bars, pass/fail flags, grade) and a class summary.

```
p3-report-card/
├── src/
│   ├── ReportCard.java       main: reading input, printing reports
│   ├── Student.java          one student: name, roll number, marks, average, grade
│   └── GradeCalculator.java  pure helper methods: averages, grades, recursion
├── sample-input.txt
├── run.sh                    compiles src/ into out/ and runs
└── test.sh
```

Run: `bash run.sh` or `bash run.sh --sample`. Test: `bash test.sh`.

**Uses (Chapter 3):** methods with parameters and return values, overloading, recursion, input-validation loops, classes, constructors, `private` fields with getters.
