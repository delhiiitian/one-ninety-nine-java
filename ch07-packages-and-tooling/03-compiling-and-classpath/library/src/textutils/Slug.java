package textutils;

public class Slug {
    /** "Hello World, 2026!" -> "hello-world-2026" (the style used in blog URLs) */
    public static String of(String title) {
        return title.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("(^-|-$)", "");
    }
}
