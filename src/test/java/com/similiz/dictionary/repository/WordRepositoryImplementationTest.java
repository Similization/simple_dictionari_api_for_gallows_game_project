package com.similiz.dictionary.repository;

import com.similiz.dictionary.configuration.Config;
import com.similiz.dictionary.entity.SameWords;
import com.similiz.dictionary.entity.Word;
import com.similiz.dictionary.testUtils.ConnectionManager;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {Config.class})
@Transactional
@TestInstance(value = TestInstance.Lifecycle.PER_METHOD)
class WordRepositoryImplementationTest {

    @Autowired
    private WordRepository wordRepository;

    @BeforeAll
    static void initAll() {
    }

    @BeforeEach
    void initEach() {
    }

    @Test
    @DisplayName(value = "check if method returns all words in database")
    void returnNullIfWordsTableIsEmptyOtherwiseReturnAllWords() {
        String sql = "select id, name from dictionary.words";
        try (Connection connection = ConnectionManager.open();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);

            List<Word> wordList = new ArrayList<>();
            while (resultSet.next()) {
                Word word = new Word();
                word.setId(resultSet.getLong("id"));
                word.setName(resultSet.getString("name"));
                wordList.add(word);
            }

            List<Word> wordRepositoryFindAllMethodResult = wordRepository.findAll();
            assertThat(wordList).isEqualTo(wordRepositoryFindAllMethodResult);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest(name = "{index} - query with id = {0} works correctly")
    @ValueSource(longs = {-1, 2, 5})
    @DisplayName(value = "check if method returns all words in database")
    void returnNullIfNoWordsWithSuchIdOtherwiseReturnWord(long id) {
        String sql = "select id, name from dictionary.words where id = ?";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            Word word = null;
            if (resultSet.next()) {
                word = new Word(resultSet.getLong("id"),
                        resultSet.getString("name"));
            }

            Word wordRepositoryFindByIdMethodResult = wordRepository.findById(id);
            assertThat(word).isEqualTo(wordRepositoryFindByIdMethodResult);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest(name = "{index} - find all words same to {0}")
    @ArgumentsSource(value = WordArgumentsProvider.class)
    @DisplayName(value = "check if method returns all same words to given")
    void returnNullIfNoWordsWithSuchIdOtherwiseReturnWord(@NotNull Word word) {
        String sql = "select w.id as 'id', w.name as 'name' " +
                "from dictionary.same_words " +
                "join dictionary.words w on w.id = same_words.word2_id " +
                "where word1_id = ?";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, word.getId());
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Word> actualWords = new ArrayList<>();
            while (resultSet.next()) {
                actualWords.add(new Word(resultSet.getLong("id"),
                        resultSet.getString("name")));
            }

            List<Word> wordRepositoryFindSameWordsResult = wordRepository.findSameWords(word);
            assertThat(actualWords).isEqualTo(wordRepositoryFindSameWordsResult);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @ParameterizedTest(name = "{index} - pair with id = {0} defined correctly")
    @ValueSource(longs = {-1, 3, 20})
    @DisplayName(value = "check if method returns pair of same words in database")
    void returnPairOfSameWordsByIdOtherwiseReturnNull(long id) {
        String sql = "select sw.id as 'id', " +
                "w.id as 'w1_id', w.name as 'w1_name', " +
                "w2.id as 'w2_id', w2.name as 'w2_name' " +
                "from dictionary.same_words sw " +
                "join dictionary.words w on w.id = sw.word1_id " +
                "join dictionary.words w2 on w2.id = sw.word2_id " +
                "where sw.id = ?";
        try (Connection connection = ConnectionManager.open();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            SameWords actualSameWords = null;
            if (resultSet.next()) {
                actualSameWords = new SameWords(
                        resultSet.getLong("id"),
                        new Word(resultSet.getLong("w1_id"), resultSet.getString("w1_name")),
                        new Word(resultSet.getLong("w2_id"), resultSet.getString("w2_name"))
                );
            }

            SameWords wordRepositoryFindSameWordsByIdResult = wordRepository.findSameWordsById(id);
            System.out.println(wordRepositoryFindSameWordsByIdResult);
            assertThat(actualSameWords).isEqualTo(wordRepositoryFindSameWordsByIdResult);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    void closeAfterEach() {
    }

    @AfterAll
    static void closeAfterAll() {
    }
}
