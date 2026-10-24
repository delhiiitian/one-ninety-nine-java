// 9.8 Working with JSON, the language of web APIs
// Run: java JsonDemo.java
// The JDK has no JSON library, so this file builds and reads simple JSON by hand to show the format.
// Real projects use a library: Jackson or Gson (one line each: mapper.writeValueAsString(obj)).
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonDemo {

    record Book(String title, String author, int year, double price, List<String> tags) { }

    public static void main(String[] args) {
        Book book = new Book("The Pragmatic Programmer", "Hunt & Thomas", 1999, 39.95, List.of("career", "craft"));

        // Object -> JSON text (serializing)
        String json = toJson(book);
        System.out.println("Serialized:");
        System.out.println(json);

        // JSON text -> values (parsing). A text block holds a typical API response.
        String response = """
                {"id": 7, "name": "Ravi \\"RJ\\" Joshi", "active": true, "balance": 1520.75}
                """;
        Map<String, String> fields = parseFlatObject(response);
        System.out.println("Parsed fields: " + fields);
        double balance = Double.parseDouble(fields.get("balance"));
        System.out.println(fields.get("name") + " has balance " + balance + ", active=" + Boolean.parseBoolean(fields.get("active")));
    }

    static String toJson(Book b) {
        StringBuilder tags = new StringBuilder("[");
        for (int i = 0; i < b.tags().size(); i++) {
            if (i > 0) tags.append(", ");
            tags.append(quote(b.tags().get(i)));
        }
        tags.append("]");
        return """
                {
                  "title": %s,
                  "author": %s,
                  "year": %d,
                  "price": %.2f,
                  "tags": %s
                }""".formatted(quote(b.title()), quote(b.author()), b.year(), b.price(), tags);
    }

    // Strings in JSON are wrapped in double quotes; quotes and backslashes inside are escaped
    static String quote(String s) {
        return "\"" + s.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
    }

    // Reads {"key": value, ...} with string, number and boolean values (no nesting)
    static Map<String, String> parseFlatObject(String json) {
        Map<String, String> result = new LinkedHashMap<>();
        Pattern pair = Pattern.compile("\"(\\w+)\"\\s*:\\s*(\"((?:[^\"\\\\]|\\\\.)*)\"|[-\\w.]+)");
        Matcher m = pair.matcher(json);
        while (m.find()) {
            String value = m.group(3) != null ? m.group(3).replace("\\\"", "\"").replace("\\\\", "\\") : m.group(2);
            result.put(m.group(1), value);
        }
        return result;
    }
}
