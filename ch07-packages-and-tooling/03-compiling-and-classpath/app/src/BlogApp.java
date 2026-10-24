// 7.3 Compiling with javac and running with a classpath
// Run: bash run.sh
import textutils.Slug;

public class BlogApp {
    public static void main(String[] args) {
        String[] titles = {"Hello World, 2026!", "Java in 199 Pages", "  Tips & Tricks  "};
        for (String t : titles) {
            System.out.println("https://myblog.example/" + Slug.of(t));
        }
    }
}
