package com.textkit.core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class TokenizerTest {

    @Test
    void splitsOnPunctuationAndLowerCases() {
        assertEquals(List.of("hello", "world", "it's", "2026"), Tokenizer.words("Hello, WORLD! It's 2026."));
    }

    @Test
    void emptyTextHasNoWords() {
        assertEquals(List.of(), Tokenizer.words("  ...  "));
    }

    @Test
    void countsSentences() {
        assertEquals(3, Tokenizer.sentenceCount("One. Two! Three?"));
        assertEquals(1, Tokenizer.sentenceCount("No ending punctuation"));
        assertEquals(0, Tokenizer.sentenceCount("   "));
    }

    @Test
    void estimatesSyllables() {
        assertEquals(1, Tokenizer.syllables("cat"));
        assertEquals(2, Tokenizer.syllables("reading"));
        assertEquals(1, Tokenizer.syllables("make"));
    }
}
