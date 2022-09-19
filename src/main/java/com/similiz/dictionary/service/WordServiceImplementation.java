package com.similiz.dictionary.service;

import com.similiz.dictionary.entity.SameWords;
import com.similiz.dictionary.entity.Word;
import com.similiz.dictionary.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class WordServiceImplementation implements WordService {
    @Autowired
    private WordRepository wordRepository;

    @Override
    @Transactional
    public List<Word> findAll() {
        return wordRepository.findAll();
    }

    @Override
    @Transactional
    public Word findById(long id) {
        return wordRepository.findById(id);
    }

    @Override
    @Transactional
    public List<Word> findSameWords(Word word) {
        return wordRepository.findSameWords(word);
    }

    @Override
    @Transactional
    public SameWords findSameWordsById(long id) {
        return wordRepository.findSameWordsById(id);
    }

    @Override
    @Transactional
    public void save(Word word) {
        wordRepository.save(word);
    }

    @Override
    @Transactional
    public void deleteAll() {
        wordRepository.deleteAll();
    }

    @Override
    @Transactional
    public String delete(long id) {
        if (wordRepository.findById(id) != null) {
            wordRepository.deleteById(id);
            return "Word with id = " + id + " was deleted";
        }
        return null;
    }
}
