// 6.9 Configuration files with Properties
// Run: java PropertiesConfig.java   (reads app.properties)
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesConfig {
    public static void main(String[] args) throws IOException {
        Properties config = new Properties();
        try (Reader in = Files.newBufferedReader(Path.of("app.properties"))) {
            config.load(in);                                   // key=value lines
        }

        // Values are always text: convert when needed
        String appName = config.getProperty("app.name");
        int port = Integer.parseInt(config.getProperty("server.port"));
        boolean darkMode = Boolean.parseBoolean(config.getProperty("ui.darkMode"));
        String language = config.getProperty("ui.language", "en");   // default if missing

        System.out.println("App: " + appName);
        System.out.println("Port: " + port);
        System.out.println("Dark mode: " + darkMode);
        System.out.println("Language: " + language);

        // Change a setting and save a user copy, like a "Settings > Save" button
        config.setProperty("ui.darkMode", "false");
        Files.createDirectories(Path.of("sandbox"));
        try (Writer out = Files.newBufferedWriter(Path.of("sandbox", "user.properties"))) {
            config.store(out, "User settings");
        }
        System.out.println("Saved sandbox/user.properties with " + config.size() + " settings");
    }
}
