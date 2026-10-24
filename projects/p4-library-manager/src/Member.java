import java.util.ArrayList;
import java.util.List;

// A library member and the books they currently hold.
public class Member {
    public static final int MAX_LOANS = 3;

    private final String id;
    private final String name;
    private final List<String> loans = new ArrayList<>();     // ISBNs, in borrowing order (Chapter 4.3)

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public List<String> getLoans() { return loans; }
    public boolean canBorrow() { return loans.size() < MAX_LOANS; }
}
