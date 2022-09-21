package com.similiz.dictionary.entity;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Field;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class SameWordsTest {

    private static final long[] ids = new long[] {
            1, 12, 45, 6, 909, 4
    };
    private static final String[] words = new String[] {
            "apple", "pie", "book", "romance", "fruit", "orange"
    };

    private Word word1;
    private Word word2;
    private static Random idRandomizer;
    private static Random wordRandomizer;
    private SameWords sameWords;

    @BeforeAll
    static void initializeStatic() {
        idRandomizer = new Random(1454564);
        wordRandomizer = new Random(6767993);
    }

    @BeforeEach
    void initializeBeforeEach() {
        word1 = new Word(
                ids[idRandomizer.nextInt(ids.length)],
                words[wordRandomizer.nextInt(words.length)]
        );
        word2 = new Word(
                ids[idRandomizer.nextInt(ids.length)],
                words[wordRandomizer.nextInt(words.length)]
        );
        sameWords = new SameWords();
    }

    @Test
    void noArgsConstructorDoesNotChangeValues() {
        try {
            Field fieldId = sameWords.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            long id = fieldId.getLong(sameWords);
            assertThat(id).isZero();

            Field fieldWord1 = sameWords.getClass().getDeclaredField("word1");
            fieldWord1.setAccessible(true);
            Word actualWord1 = (Word) fieldWord1.get(sameWords);
            assertThat(actualWord1).isNull();

            Field fieldWord2 = sameWords.getClass().getDeclaredField("word2");
            fieldWord2.setAccessible(true);
            Word actualWord2 = (Word) fieldWord2.get(sameWords);
            assertThat(actualWord2).isNull();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void allArgsConstructorDoesNotChangeValues() {
        long newId = 1;
        sameWords = new SameWords(newId, word1, word2);
        try {
            Field fieldId = sameWords.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            long id = fieldId.getLong(sameWords);
            assertThat(id).isEqualTo(newId);

            Field fieldWord1 = sameWords.getClass().getDeclaredField("word1");
            fieldWord1.setAccessible(true);
            Word actualWord1 = (Word) fieldWord1.get(sameWords);
            assertThat(actualWord1).isEqualTo(word1);

            Field fieldWord2 = sameWords.getClass().getDeclaredField("word2");
            fieldWord2.setAccessible(true);
            Word actualWord2 = (Word) fieldWord2.get(sameWords);
            assertThat(actualWord2).isSameAs(word2);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getIdReturnsDefaultValueIfNoOtherWasPassed() {
        assertThat(sameWords.getId()).isZero();
    }

    @Test
    void getIdReturnsUpdatedValueAfterSet() {
        int newId = 4;
        sameWords = new SameWords(newId, null, null);
        assertThat(sameWords.getId()).isEqualTo(newId);
    }

    @Test
    void getWordReturnsDefaultValueIfNoOtherWasPassed() {
        assertThat(sameWords.getWord1()).isNull();
        assertThat(sameWords.getWord2()).isNull();
    }

    @Test
    void getWordReturnsUpdatedValueAfterSet() {
        long newId = 2;
        sameWords = new SameWords(newId, word1, word2);
        assertThat(sameWords.getWord1()).isSameAs(word1);
        assertThat(sameWords.getWord2()).isSameAs(word2);
    }

    @Test
    void setIdSetCorrectValue() {
        long newId = 200;
        sameWords = new SameWords(newId, null, null);
        try {
            Field fieldId = sameWords.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            long id = fieldId.getLong(sameWords);
            assertThat(id).isEqualTo(newId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void setWordSetCorrectValue() {
        long newId = 2;
        sameWords = new SameWords(newId, word1, word2);
        try {
            sameWords.getWord1().setName("aaa");
            Field fieldWord1 = sameWords.getClass().getDeclaredField("word1");
            fieldWord1.setAccessible(true);
            Word actualWord1 = (Word) fieldWord1.get(sameWords);
            assertThat(actualWord1).isEqualTo(word1);

            Field fieldWord2 = sameWords.getClass().getDeclaredField("word2");
            fieldWord2.setAccessible(true);
            Word actualWord2 = (Word) fieldWord2.get(sameWords);
            assertThat(actualWord2).isSameAs(word2);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

