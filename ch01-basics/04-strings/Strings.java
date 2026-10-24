// 1.4 Strings: text values
// Run: java Strings.java
public class Strings {
    public static void main(String[] args) {
        String first = "Ada";
        String last = "Lovelace";

        // Joining strings (concatenation)
        String full = first + " " + last;
        System.out.println("Full name: " + full);

        // Useful methods
        System.out.println("Length: " + full.length());
        System.out.println("Upper: " + full.toUpperCase());
        System.out.println("Lower: " + full.toLowerCase());
        System.out.println("First letter: " + full.charAt(0));
        System.out.println("Surname via substring: " + full.substring(4));
        System.out.println("Contains 'Love'? " + full.contains("Love"));
        System.out.println("Index of 'L': " + full.indexOf('L'));
        System.out.println("Replace: " + full.replace("Ada", "Augusta Ada"));

        // Cleaning user input
        String typed = "   ada@example.com  ";
        System.out.println("Trimmed: [" + typed.strip() + "]");

        // Comparing strings: use equals, never ==
        String a = "java";
        String b = new String("java");
        System.out.println("a.equals(b): " + a.equals(b));             // true: same text
        System.out.println("a == b: " + (a == b));                     // false: different objects
        System.out.println("equalsIgnoreCase: " + "JAVA".equalsIgnoreCase(a));

        // Strings are immutable: methods return a NEW string
        String city = "paris";
        city.toUpperCase();                     // result thrown away
        System.out.println("Still: " + city);
        city = city.toUpperCase();              // keep the result
        System.out.println("Now: " + city);

        // Text blocks (Java 15+) for multi-line text
        String receipt = """
                Bean There Cafe
                2 x Latte   $7.00
                """;
        System.out.print(receipt);
    }
}
