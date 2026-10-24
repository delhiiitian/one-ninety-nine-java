// 9.2 Command-line arguments and environment variables
// Run: java ArgsAndEnvironment.java greet --name Ana --times 2
//      APP_MODE=production java ArgsAndEnvironment.java greet
import java.util.HashMap;
import java.util.Map;

public class ArgsAndEnvironment {
    public static void main(String[] args) {
        // args holds the words typed after the program name, like options on a coffee order
        System.out.println("Received " + args.length + " argument(s)");
        for (int i = 0; i < args.length; i++) {
            System.out.println("  args[" + i + "] = " + args[i]);
        }

        String command = args.length > 0 ? args[0] : "help";
        Map<String, String> options = parseOptions(args);
        String name = options.getOrDefault("name", "friend");
        int times = Integer.parseInt(options.getOrDefault("times", "1"));

        // Environment variables: settings from the machine, not the command line.
        // Servers use them for passwords and modes so secrets stay out of the code.
        String mode = System.getenv().getOrDefault("APP_MODE", "development");
        System.out.println("Mode: " + mode);

        switch (command) {
            case "greet" -> {
                for (int i = 0; i < times; i++) System.out.println("Hello, " + name + "!");
            }
            case "help" -> System.out.println("Usage: java ArgsAndEnvironment.java greet [--name N] [--times T]");
            default -> {
                System.out.println("Unknown command: " + command);
                System.exit(2);           // non-zero exit code signals failure to scripts
            }
        }
    }

    // Turns ["greet", "--name", "Ana"] into {name=Ana}
    static Map<String, String> parseOptions(String[] args) {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < args.length - 1; i++) {
            if (args[i].startsWith("--")) {
                map.put(args[i].substring(2), args[i + 1]);
            }
        }
        return map;
    }
}
