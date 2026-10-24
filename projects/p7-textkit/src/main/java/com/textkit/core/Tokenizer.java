package com.textkit.core;

import java.util.ArrayList;
import java.util.List;

/**
 * Splits raw text into words and sentences.
 * Kept separate so every other class agrees on what a "word" is.
 */
public final class Tokenizer {

    private Tokenizer() { }      // utility class: no objects needed

    /**
     * Lower-case words made of letters, digits and apostrophes.
     *
     * @param text any text, may be empty
     * @return the words in order; never null
     */
    public static List<String> words(String text) {
        List<String> words = new ArrayList<>();
        for (String raw : text.toLowerCase().split("[^\\p{L}\\p{N}']+")) {
            String w = raw.replaceAll("^'+|'+$", "");
            if (!w.isEmpty()) words.add(w);
        }
        return words;
    }

    /**
     * Sentences end with '.', '!' or '?'.
     *
     * @param text any text
     * @return number of sentences, at least 1 for non-blank text
     */
    public static int sentenceCount(String text) {
        if (text.isBlank()) return 0;
        int count = text.split("[.!?]+(\\s|$)").length;
        return Math.max(1, count);
    }

    /**
     * Rough syllable count using vowel groups: "reading" -> rea|ding = 2.
     *
     * @param word a single lower-case word
     * @return estimated syllables, at least 1
     */
    public static int syllables(String word) {
        String w = word.replaceAll("e$", "");                    // silent final e
        int groups = w.split("[aeiouy]+", -1).length - 1;
        return Math.max(1, groups);
    }
}
