package com.similiz.dictionary.repository;

import com.similiz.dictionary.entity.Word;
import com.similiz.dictionary.util.ConnectionManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;

class WordRepositoryImplementationTest {

//    @Autowired
//    private WordRepository wordRepository;

    @Test
    void returnNullIfWordsTableIsEmpty() {
        String sql = "select * from dictionary.words";
        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {
            System.out.println(connection.getTransactionIsolation());
            ResultSet resultSet = statement.executeQuery(sql);

            List<Word> wordList = new ArrayList<>();
            while (resultSet.next()) {
                Word word = new Word();
                word.setId(resultSet.getLong("id"));
                word.setName(resultSet.getString("name"));
                wordList.add(word);
            }

//            List<Word> wordRepositoryFindAllMethodResult = wordRepository.findAll();

//            assertSame(wordRepositoryFindAllMethodResult, wordList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
