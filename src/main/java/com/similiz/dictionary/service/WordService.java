package com.similiz.dictionary.service;

import com.similiz.dictionary.entity.SameWords;
import com.similiz.dictionary.entity.Word;

import java.util.List;

public interface WordService {
    List<Word> findAll();

    Word findById(long id);

    List<Word> findSameWords(Word word);

    SameWords findSameWordsById(long id);

    void save(Word word);

    String delete(long id);
}
