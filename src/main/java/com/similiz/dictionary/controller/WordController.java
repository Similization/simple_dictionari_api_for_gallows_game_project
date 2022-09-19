package com.similiz.dictionary.controller;

import com.similiz.dictionary.entity.SameWords;
import com.similiz.dictionary.entity.Word;
import com.similiz.dictionary.exception_handler.NoSuchWordException;
import com.similiz.dictionary.service.WordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WordController {
    @Autowired
    private WordService wordService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/words")
    public List<Word> getAllWords() {
        log.info("Get all words from database");
        return wordService.findAll();
    }

    @GetMapping("/words/{id}")
    public Word getWordById(@PathVariable long id) {
        Word word = wordService.findById(id);
        if (word == null) {
            log.error("No such word with id = " + id);
            throw new NoSuchWordException("No word with id = " + id);
        }
        log.info("Get word with id = " + id);
        return word;
    }

    @GetMapping("/words/{id}/same_words")
    public List<Word> getSameWordsByWordId(@PathVariable long id) {
        Word word = wordService.findById(id);
        if (word == null) {
            log.error("No such word with id = " + id);
            throw new NoSuchWordException("No word with id = " + id);
        }
        List<Word> sameWords = wordService.findSameWords(word);
        log.info("Get same words to word with id = " + id);
        return sameWords;
    }

    @GetMapping("/same_words/{id}")
    public SameWords getSameWordsById(@PathVariable long id) {
        return wordService.findSameWordsById(id);
    }

    @PostMapping("/words")
    public Word addWord(@RequestBody Word word) {
        wordService.save(word);
        log.info("Add new word " + word + " in database");
        return word;
    }

    @PutMapping("/words")
    public Word updateWord(@RequestBody Word word) {
        wordService.save(word);
        log.info("Update word with id = " + word.getName());
        return word;
    }

    @DeleteMapping("/words")
    public String deleteAllWords() {
        wordService.deleteAll();
        log.info("All words were deleted");
        return "All words were deleted";
    }

    @DeleteMapping("/words/{id}")
    public String deleteWordById(@PathVariable long id) {
        if (wordService.findById(id) == null) {
            log.error("No such word with id = " + id);
            throw new NoSuchWordException("No word with id = " + id);
        }
        log.info("Delete word with id = " + id);
        return wordService.delete(id);
    }
}
