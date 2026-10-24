// 8.4 Abstract classes: a partly finished blueprint
// Run: java AbstractClasses.java
import java.util.List;

public class AbstractClasses {
    public static void main(String[] args) {
        // Employee e = new Employee("x");   // compile error: cannot create an abstract class
        List<Employee> staff = List.of(
                new SalariedEmployee("Ines", 60_000),
                new HourlyEmployee("Kwame", 25.0, 120),
                new HourlyEmployee("Lucy", 30.0, 80));

        double payroll = 0;
        for (Employee e : staff) {
            e.printPayslip();
            payroll += e.monthlyPay();
        }
        System.out.printf("Total monthly payroll: $%.2f%n", payroll);
    }
}

abstract class Employee {
    protected final String name;

    Employee(String name) { this.name = name; }

    // Abstract: every subclass MUST provide this
    abstract double monthlyPay();

    // Concrete: shared by everyone, and it uses the abstract method
    void printPayslip() {
        System.out.printf("%-6s %-9s $%9.2f%n", name, getClass().getSimpleName().replace("Employee", ""), monthlyPay());
    }
}

class SalariedEmployee extends Employee {
    private final double annualSalary;

    SalariedEmployee(String name, double annualSalary) {
        super(name);
        this.annualSalary = annualSalary;
    }

    @Override
    double monthlyPay() { return annualSalary / 12; }
}

class HourlyEmployee extends Employee {
    private final double rate;
    private final int hours;

    HourlyEmployee(String name, double rate, int hours) {
        super(name);
        this.rate = rate;
        this.hours = hours;
    }

    @Override
    double monthlyPay() { return rate * hours; }
}
