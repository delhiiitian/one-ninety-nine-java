// Project 4: Library Manager
// A command-driven program for a small community library.
// Run: bash run.sh   (or with a scripted session: bash run.sh --sample)
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

public class LibraryApp {

    public static void main(String[] args) {
        Library lib = new Library();
        loadStarterData(lib);
        Scanner in = new Scanner(System.in);

        System.out.println("Community Library. Type 'help' for commands.");
        while (true) {
            System.out.print("> ");
            if (!in.hasNextLine()) break;
            String[] parts = in.nextLine().trim().split("\\s+", 3);   // command + up to 2 arguments
            String cmd = parts[0].toLowerCase();
            if (cmd.equals("quit")) break;

            switch (cmd) {
                case "help" -> System.out.println("list [title|author|year], search TEXT, borrow MEMBER ISBN, return MEMBER ISBN, members, genres, recent, quit");
                case "list" -> printBooks(lib, lib.sortedBy(parts.length > 1 ? parts[1] : "title"));
                case "search" -> {
                    if (parts.length < 2) { System.out.println("search what?"); continue; }
                    String text = parts.length == 3 ? parts[1] + " " + parts[2] : parts[1];
                    List<Book> found = lib.search(text);
                    System.out.println(found.size() + " match(es)");
                    printBooks(lib, found);
                }
                case "borrow", "return" -> {
                    if (parts.length < 3) { System.out.println("usage: " + cmd + " MEMBER ISBN"); continue; }
                    String id = parts[1].toUpperCase(), isbn = parts[2];
                    System.out.println(cmd.equals("borrow") ? lib.borrow(id, isbn) : lib.giveBack(id, isbn));
                }
                case "members" -> {
                    for (Member m : new TreeMap<>(lib.getMembers()).values()) {          // TreeMap sorts by id
                        System.out.printf("%-4s %-8s holds %d: %s%n", m.getId(), m.getName(), m.getLoans().size(), m.getLoans());
                    }
                }
                case "genres" -> lib.countByGenre().forEach((g, n) -> System.out.println(g + ": " + n));
                case "recent" -> lib.recentActivity(3).forEach(h -> System.out.println("  " + h));
                case "" -> { }
                default -> System.out.println("Unknown command '" + cmd + "'. Type help.");
            }
        }
        System.out.println("Library closed. Goodbye!");
    }

    static void printBooks(Library lib, List<Book> books) {
        for (Book b : books) {
            System.out.println((lib.isOnLoan(b.isbn()) ? "[OUT] " : "[IN]  ") + b.shortLine());
        }
    }

    static void loadStarterData(Library lib) {
        lib.addBook(new Book("B1", "To Kill a Mockingbird", "Harper Lee", 1960, "Classic"));
        lib.addBook(new Book("B2", "The Hobbit", "J.R.R. Tolkien", 1937, "Fantasy"));
        lib.addBook(new Book("B3", "Sapiens", "Yuval Noah Harari", 2011, "History"));
        lib.addBook(new Book("B4", "The Lord of the Rings", "J.R.R. Tolkien", 1954, "Fantasy"));
        lib.addBook(new Book("B5", "Atomic Habits", "James Clear", 2018, "Self-help"));
        lib.addBook(new Book("B6", "1984", "George Orwell", 1949, "Classic"));
        lib.addMember(new Member("M1", "Aditi"));
        lib.addMember(new Member("M2", "Bilal"));
        lib.addMember(new Member("M3", "Chloe"));
    }
}
