package com.similiz.dictionary.repository;

import com.similiz.dictionary.entity.SameWords;
import com.similiz.dictionary.entity.Word;

import java.util.List;

public interface WordRepository {
    List<Word> findAll();

    List<Word> findSameWords(Word word);

    SameWords findSameWordsById(long id);

    Word findById(long id);

    void save(Word word);

    void deleteById(long id);
}
