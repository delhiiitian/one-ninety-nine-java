// 5.9 Optional: an honest "maybe" instead of null
// Run: java OptionalDemo.java
import java.util.List;
import java.util.Optional;

public class OptionalDemo {

    record User(String username, String email) { }

    static final List<User> USERS = List.of(
            new User("ana", "ana@mail.com"),
            new User("raj", null));

    // The return type tells callers: there may be no result
    static Optional<User> findUser(String username) {
        for (User u : USERS) {
            if (u.username().equals(username)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        Optional<User> found = findUser("ana");
        Optional<User> missing = findUser("zed");

        System.out.println("ana present? " + found.isPresent());
        System.out.println("zed present? " + missing.isPresent());

        // Provide a fallback
        User guest = missing.orElse(new User("guest", "none"));
        System.out.println("Logged in as: " + guest.username());

        // Run code only when a value exists
        found.ifPresent(u -> System.out.println("Welcome back, " + u.username()));

        // Transform safely: map does nothing on an empty Optional
        String email = findUser("raj")
                .map(User::email)                     // email is null -> becomes empty
                .orElse("no email on file");
        System.out.println("raj's email: " + email);

        // Fail loudly when a value is required
        try {
            findUser("zed").orElseThrow(() -> new IllegalStateException("User zed not found"));
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
