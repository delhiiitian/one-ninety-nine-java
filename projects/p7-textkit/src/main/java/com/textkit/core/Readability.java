package com.textkit.core;

/**
 * Flesch reading-ease score: higher means easier. 60-70 is plain English.
 */
public final class Readability {

    private Readability() { }

    /**
     * @param stats statistics of the text
     * @return score, usually between 0 and 100
     */
    public static double fleschScore(TextStats stats) {
        if (stats.wordCount() == 0 || stats.sentenceCount() == 0) return 0;
        int syllables = 0;
        for (String w : stats.words()) syllables += Tokenizer.syllables(w);
        double wordsPerSentence = (double) stats.wordCount() / stats.sentenceCount();
        double syllablesPerWord = (double) syllables / stats.wordCount();
        return 206.835 - 1.015 * wordsPerSentence - 84.6 * syllablesPerWord;
    }

    /**
     * @param score a Flesch score
     * @return a plain-language label for the score
     */
    public static String label(double score) {
        if (score >= 80) return "very easy";
        if (score >= 60) return "plain English";
        if (score >= 40) return "fairly difficult";
        return "difficult";
    }
}
