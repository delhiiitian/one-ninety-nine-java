// A book in the catalogue. A record: it only carries data and never changes (Chapter 4.9).
public record Book(String isbn, String title, String author, int year, String genre) {

    public String shortLine() {
        return String.format("%-6s %-28s %-20s %d", isbn, title, author, year);
    }
}
