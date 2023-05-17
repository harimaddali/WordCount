package com.word.api;

import com.word.WordCounter;
import org.springframework.web.bind.annotation.*;

@RestController
public class WordCounterController {
    private final WordCounter wordCounter;

    public WordCounterController(WordCounter wordCounter) {
        this.wordCounter = wordCounter;
    }

    @PostMapping("/words")
    public void addWords(@RequestBody String[] words) {
        wordCounter.addWords(words);
    }

    @GetMapping("/count/{word}")
    public int getCount(@PathVariable String word) {
        return wordCounter.getWordCount(word);
    }
}
