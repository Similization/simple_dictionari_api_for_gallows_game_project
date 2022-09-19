package com.similiz.dictionary.repository;

import com.similiz.dictionary.entity.SameWords;
import com.similiz.dictionary.entity.Word;

import java.util.List;

public interface WordRepository {
    List<Word> findAll();

    Word findById(long id);

    List<Word> findSameWords(Word word);

    SameWords findSameWordsById(long id);

    <T extends Word> List<T> saveAll(Iterable<T> words);

    void save(Word word);

    void deleteAll();

    void deleteById(long id);
}
