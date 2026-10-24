// 1.7 Reading input from the keyboard with Scanner
// Run: java ReadingInput.java
// (Type your answers and press Enter. Or pipe a file: java ReadingInput.java < input.txt)
import java.util.Scanner;

public class ReadingInput {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("What is your name? ");
        String name = in.nextLine();             // reads a whole line of text

        System.out.print("How old are you? ");
        int age = in.nextInt();                  // reads one whole number

        System.out.print("Your height in metres? ");
        double height = in.nextDouble();         // reads one decimal number
        in.nextLine();                           // eat the leftover Enter key

        System.out.print("Favourite food? ");
        String food = in.nextLine();

        System.out.println();
        System.out.println("Nice to meet you, " + name + "!");
        System.out.println("In 10 years you will be " + (age + 10) + ".");
        System.out.println("You are " + (int) (height * 100) + " cm tall.");
        System.out.println("Let's grab some " + food + " sometime.");
        in.close();
    }
}
