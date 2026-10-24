import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

// The library itself. Each question the librarian asks is answered by the best-suited collection.
public class Library {

    private final Map<String, Book> catalogue = new HashMap<>();           // ISBN -> Book: instant lookup (4.5)
    private final Map<String, Member> members = new HashMap<>();           // member id -> Member
    private final Set<String> onLoan = new HashSet<>();                    // ISBNs currently out: no duplicates (4.6)
    private final Map<String, Deque<String>> waitlists = new HashMap<>();  // ISBN -> queue of member ids (4.4)
    private final Deque<String> history = new ArrayDeque<>();             // recent activity, newest on top (stack)

    public void addBook(Book book) {
        catalogue.put(book.isbn(), book);
    }

    public void addMember(Member m) {
        members.put(m.getId(), m);
    }

    public String borrow(String memberId, String isbn) {
        Member m = members.get(memberId);
        Book b = catalogue.get(isbn);
        if (m == null) return "No member " + memberId;
        if (b == null) return "No book " + isbn;
        if (onLoan.contains(isbn)) {
            Deque<String> queue = waitlists.computeIfAbsent(isbn, k -> new ArrayDeque<>());
            if (!queue.contains(memberId)) queue.offer(memberId);
            return "'" + b.title() + "' is out. " + m.getName() + " is #" + position(queue, memberId) + " on the waitlist.";
        }
        if (!m.canBorrow()) return m.getName() + " already has " + Member.MAX_LOANS + " books.";
        onLoan.add(isbn);
        m.getLoans().add(isbn);
        history.push(m.getName() + " borrowed " + b.title());
        return m.getName() + " borrowed '" + b.title() + "'.";
    }

    public String giveBack(String memberId, String isbn) {
        Member m = members.get(memberId);
        if (m == null || !m.getLoans().remove(isbn)) return "That loan does not exist.";
        onLoan.remove(isbn);
        Book b = catalogue.get(isbn);
        history.push(m.getName() + " returned " + b.title());
        String msg = m.getName() + " returned '" + b.title() + "'.";

        // First person in the queue gets the book automatically
        Deque<String> queue = waitlists.get(isbn);
        if (queue != null && !queue.isEmpty()) {
            String next = queue.poll();
            msg += " " + borrow(next, isbn).replace("borrowed", "(from waitlist) borrowed");
        }
        return msg;
    }

    public List<Book> search(String text) {
        String needle = text.toLowerCase();
        List<Book> found = new ArrayList<>();
        for (Book b : catalogue.values()) {
            if (b.title().toLowerCase().contains(needle) || b.author().toLowerCase().contains(needle)) {
                found.add(b);
            }
        }
        found.sort(Comparator.comparing(Book::title));
        return found;
    }

    public List<Book> sortedBy(String field) {
        List<Book> books = new ArrayList<>(catalogue.values());
        Comparator<Book> order = switch (field) {
            case "year" -> Comparator.comparingInt(Book::year).thenComparing(Book::title);
            case "author" -> Comparator.comparing(Book::author).thenComparing(Book::title);
            default -> Comparator.comparing(Book::title);
        };
        books.sort(order);
        return books;
    }

    public Map<String, Integer> countByGenre() {
        Map<String, Integer> counts = new TreeMap<>();
        for (Book b : catalogue.values()) counts.merge(b.genre(), 1, Integer::sum);
        return counts;
    }

    public boolean isOnLoan(String isbn) { return onLoan.contains(isbn); }
    public Map<String, Member> getMembers() { return members; }
    public Book getBook(String isbn) { return catalogue.get(isbn); }

    public List<String> recentActivity(int n) {
        List<String> recent = new ArrayList<>();
        for (String h : history) {
            if (recent.size() == n) break;
            recent.add(h);
        }
        return recent;
    }

    private int position(Deque<String> queue, String memberId) {
        int i = 1;
        for (String id : queue) {
            if (id.equals(memberId)) return i;
            i++;
        }
        return -1;
    }
}
