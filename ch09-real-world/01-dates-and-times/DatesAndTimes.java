// 9.1 Dates and times with java.time
// Run: java DatesAndTimes.java
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DatesAndTimes {
    public static void main(String[] args) {
        // Fixed dates keep the output repeatable; use LocalDate.now() in real apps
        LocalDate today = LocalDate.of(2026, 10, 24);
        LocalDate birthday = LocalDate.of(1995, 3, 14);

        System.out.println("Today: " + today + " (" + today.getDayOfWeek() + ")");
        Period age = Period.between(birthday, today);
        System.out.println("Age: " + age.getYears() + " years, " + age.getMonths() + " months");

        // Date arithmetic: like flipping pages on a calendar
        LocalDate libraryDue = today.plusWeeks(2);
        System.out.println("Library book due: " + libraryDue);
        System.out.println("Days until New Year: " + ChronoUnit.DAYS.between(today, LocalDate.of(2027, 1, 1)));
        System.out.println("2028 is a leap year? " + LocalDate.of(2028, 1, 1).isLeapYear());

        // Times and durations
        LocalTime shiftStart = LocalTime.of(9, 0);
        LocalTime shiftEnd = LocalTime.of(17, 30);
        Duration worked = Duration.between(shiftStart, shiftEnd);
        System.out.println("Shift length: " + worked.toHours() + "h " + worked.toMinutesPart() + "m");

        // Formatting and parsing
        LocalDateTime meeting = LocalDateTime.of(2026, 11, 3, 14, 30);
        DateTimeFormatter pretty = DateTimeFormatter.ofPattern("EEE d MMM yyyy, HH:mm", java.util.Locale.ENGLISH);
        System.out.println("Meeting: " + meeting.format(pretty));
        LocalDate parsed = LocalDate.parse("25/12/2026", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.println("Parsed Christmas: " + parsed);

        // Time zones: the same moment shows a different clock time in each city
        ZonedDateTime call = ZonedDateTime.of(meeting, ZoneId.of("Asia/Kolkata"));
        System.out.println("Call in Kolkata:  " + call.toLocalTime());
        System.out.println("Same in New York: " + call.withZoneSameInstant(ZoneId.of("America/New_York")).toLocalTime());
        System.out.println("Same in London:   " + call.withZoneSameInstant(ZoneId.of("Europe/London")).toLocalTime());
    }
}
