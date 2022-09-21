package com.similiz.dictionary.repository;

import com.similiz.dictionary.entity.Word;
import com.similiz.dictionary.util.ConnectionManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertSame;


@TestInstance(value = TestInstance.Lifecycle.PER_METHOD)
class WordRepositoryImplementationTest {

//    @Autowired
//    private WordRepository wordRepository;

    @BeforeAll
    static void initAll() {}
    @BeforeEach
    void initEach() {}

    @Test
    void returnNullIfWordsTableIsEmpty() {
        // use reflection to change current database on test database

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
//
//            assertSame(wordRepositoryFindAllMethodResult, wordList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void closeAfterEach() {}

    @AfterAll
    static void closeAfterAll() {}
}
