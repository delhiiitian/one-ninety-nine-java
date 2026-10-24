package com.textkit.core;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Statistics about one piece of text. Compute once, then ask questions.
 */
public class TextStats {

    /** Very common words that say little about a text's topic. */
    static final Set<String> STOP_WORDS = Set.of(
            "the", "a", "an", "and", "or", "of", "to", "in", "is", "it", "that", "for", "on", "with", "as", "was", "be", "are", "this", "at", "by", "but", "not", "did", "do", "its");

    private final List<String> words;
    private final int sentences;
    private final int characters;

    public TextStats(String text) {
        this.words = Tokenizer.words(text);
        this.sentences = Tokenizer.sentenceCount(text);
        this.characters = text.length();
    }

    public int wordCount() { return words.size(); }
    public int sentenceCount() { return sentences; }
    public int characterCount() { return characters; }

    public int uniqueWordCount() {
        return (int) words.stream().distinct().count();
    }

    public double averageWordLength() {
        return words.isEmpty() ? 0 : words.stream().mapToInt(String::length).average().orElse(0);
    }

    /** Minutes to read at 200 words per minute, rounded up. */
    public int readingMinutes() {
        return (int) Math.ceil(words.size() / 200.0);
    }

    /**
     * The most frequent words, skipping stop words.
     *
     * @param n how many to return
     * @return word to count, most frequent first (ties alphabetical)
     */
    public Map<String, Integer> topWords(int n) {
        Map<String, Integer> counts = new HashMap<>();
        for (String w : words) {
            if (!STOP_WORDS.contains(w) && w.length() > 1) counts.merge(w, 1, Integer::sum);
        }
        Map<String, Integer> top = new LinkedHashMap<>();
        counts.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .limit(n)
                .forEach(e -> top.put(e.getKey(), e.getValue()));
        return top;
    }

    /** The longest word (first one if several tie). */
    public String longestWord() {
        return words.stream().max(Comparator.comparingInt(String::length)).orElse("");
    }

    List<String> words() { return words; }   // package-private: shared with Readability only
}
