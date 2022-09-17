package com.similiz.dictionary.repository;

import com.similiz.dictionary.entity.SameWords;
import com.similiz.dictionary.entity.Word;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WordRepositoryImplementation implements WordRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Word> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query<Word> wordQuery = session.createQuery("select w from Word w", Word.class);
        return wordQuery.getResultList();
    }

    @Override
    public List<Word> findSameWords(Word word) {
        Session session = sessionFactory.getCurrentSession();
        Query<Word> wordQuery = session
                .createQuery("select sw.word2 from SameWords sw where sw.word1.id = :id", Word.class)
                .setParameter("id", word.getId());
        return wordQuery.getResultList();
    }

    @Override
    public SameWords findSameWordsById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(SameWords.class, id);
    }

    @Override
    public Word findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Word.class, id);
    }

    @Override
    public void save(Word word) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(word);
    }

    @Override
    public void deleteById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.createQuery("delete from Word w where w.id = :id")
                .setParameter("id", id).executeUpdate();
    }
}
