package com.word;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WordCounterTest {
    @Mock
    private ExTranslator translator;

    private WordCounter wordCounter;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        wordCounter = new WordCounter(translator);
    }

    @Test
    public void testAddWords() {
        String[] words = {"flower", "flor", "blume"};

        // Mock the translation for all words to "flower"
        when(translator.translate(anyString())).thenReturn("flower");

        wordCounter.addWords(words);

        // Verify that the translator was called for each word
        verify(translator, times(3)).translate(anyString());

        // Verify the count of "flower"
        assertEquals(3, wordCounter.getWordCount("flower"));
    }

    @Test
    public void testGetCount() {
        String word = "flower";

        // Mock the translation for the word to "flower"
        when(translator.translate(anyString())).thenReturn("flower");

        // Verify the count is initially 0
        assertEquals(0, wordCounter.getWordCount(word));

        // Add the word multiple times
        wordCounter.addWords(word, word, word);

        // Verify the count is now 3
        assertEquals(3, wordCounter.getWordCount(word));
    }

    @Test
    public void testTranslation() {
        when(translator.translate("blume")).thenReturn("flower");

        wordCounter.addWords("blume");

        assertEquals(1, wordCounter.getWordCount("flower"));
    }

    @Test
    public void testTranslationCalledMultipleTimes() {
        when(translator.translate("blume")).thenReturn("flower");

        wordCounter.addWords("blume", "blume");

        verify(translator, times(2)).translate("blume");
        assertEquals(2, wordCounter.getWordCount("flower"));
    }
}
