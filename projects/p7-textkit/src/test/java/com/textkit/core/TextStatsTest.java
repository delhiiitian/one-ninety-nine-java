package com.textkit.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class TextStatsTest {

    private final TextStats stats = new TextStats("The cat sat. The cat ran to the dog! A dog barked.");

    @Test
    void basicCounts() {
        assertEquals(12, stats.wordCount());
        assertEquals(3, stats.sentenceCount());
        assertEquals(8, stats.uniqueWordCount());
    }

    @Test
    void topWordsSkipStopWordsAndBreakTiesAlphabetically() {
        assertEquals(List.of("cat", "dog", "barked"), List.copyOf(stats.topWords(3).keySet()));
        assertEquals(2, stats.topWords(1).get("cat"));
    }

    @Test
    void readingTimeRoundsUp() {
        assertEquals(1, stats.readingMinutes());
        assertEquals(0, new TextStats("").readingMinutes());
    }

    @Test
    void simpleTextIsEasyToRead() {
        assertTrue(Readability.fleschScore(stats) > 80);
        assertEquals("very easy", Readability.label(90));
    }
}
