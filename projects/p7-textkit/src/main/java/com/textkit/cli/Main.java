package com.textkit.cli;

import com.textkit.core.Readability;
import com.textkit.core.TextStats;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Command-line front end: {@code java -jar textkit.jar [--top N] [--verbose] FILE...}
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        int top = 5;
        boolean verbose = false;
        java.util.List<Path> files = new java.util.ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--top" -> top = Integer.parseInt(args[++i]);
                case "--verbose" -> verbose = true;
                default -> files.add(Path.of(args[i]));
            }
        }
        Logger.getLogger("").setLevel(verbose ? Level.FINE : Level.WARNING);
        Logger.getLogger("").getHandlers()[0].setLevel(verbose ? Level.FINE : Level.WARNING);

        if (files.isEmpty()) {
            System.out.println("Usage: java -jar textkit.jar [--top N] [--verbose] FILE...");
            System.exit(1);
        }
        int failures = 0;
        for (Path file : files) {
            try {
                LOG.fine("Reading " + file);
                String text = Files.readString(file);
                System.out.print(report(file.getFileName().toString(), new TextStats(text), top));
            } catch (IOException e) {
                LOG.warning("Cannot read " + file + ": " + e.getMessage());
                failures++;
            }
        }
        System.exit(failures == 0 ? 0 : 2);     // non-zero exit lets scripts notice failures
    }

    static String report(String name, TextStats s, int top) {
        double score = Readability.fleschScore(s);
        StringBuilder sb = new StringBuilder();
        sb.append("== ").append(name).append(" ==\n");
        sb.append(String.format("Words: %d (unique %d)   Sentences: %d   Characters: %d%n",
                s.wordCount(), s.uniqueWordCount(), s.sentenceCount(), s.characterCount()));
        sb.append(String.format("Average word length: %.1f   Longest word: %s%n", s.averageWordLength(), s.longestWord()));
        sb.append(String.format("Reading time: %d min   Readability: %.0f (%s)%n", s.readingMinutes(), score, Readability.label(score)));
        sb.append("Top words: ");
        s.topWords(top).forEach((w, c) -> sb.append(w).append('=').append(c).append(' '));
        sb.append('\n');
        return sb.toString();
    }
}
