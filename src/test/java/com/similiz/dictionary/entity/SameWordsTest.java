package com.similiz.dictionary.entity;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class SameWordsTest {

    private static final long[] ids = new long[] {
            1, 12, 45, 6, 909, 4, 32, 100000, 2
    };
    private static final String[] words = new String[] {
            "apple", "pie", "book", "romance", "fruit", "orange"
    };

    private long id;
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
        id = ids[idRandomizer.nextInt(ids.length)];
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
    @Order(1)
    @DisplayName(value = "check correct work of no args constructor")
    void noArgsConstructorDoesNotChangeValues() {
        try {
            Field fieldId = sameWords.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            long actualId = fieldId.getLong(sameWords);

            Field fieldWord1 = sameWords.getClass().getDeclaredField("word1");
            fieldWord1.setAccessible(true);
            Word actualWord1 = (Word) fieldWord1.get(sameWords);

            Field fieldWord2 = sameWords.getClass().getDeclaredField("word2");
            fieldWord2.setAccessible(true);
            Word actualWord2 = (Word) fieldWord2.get(sameWords);

            assertAll(
                    () -> assertThat(actualId).isZero(),
                    () -> assertThat(actualWord1).isNull(),
                    () -> assertThat(actualWord2).isNull()
            );
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(1)
    void allArgsConstructorDoesNotChangeValues() {
        sameWords = new SameWords(id, word1, word2);
        try {
            Field fieldId = sameWords.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            long actualId = fieldId.getLong(sameWords);

            Field fieldWord1 = sameWords.getClass().getDeclaredField("word1");
            fieldWord1.setAccessible(true);
            Word actualWord1 = (Word) fieldWord1.get(sameWords);

            Field fieldWord2 = sameWords.getClass().getDeclaredField("word2");
            fieldWord2.setAccessible(true);
            Word actualWord2 = (Word) fieldWord2.get(sameWords);

            assertAll(
                    () -> assertThat(actualId).isEqualTo(id),
                    () -> assertThat(actualWord1).isEqualTo(word1),
                    () -> assertThat(actualWord2).isEqualTo(word2)
            );
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(1)
    void builderInsertCorrectValues() {
        SameWords sameWords = SameWords.builder()
                .id(id)
                .word1(word1)
                .word2(word2)
                .build();
        try {
            Field fieldId = sameWords.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            long actualId = fieldId.getLong(sameWords);
            assertThat(actualId).isEqualTo(id);

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

    @Nested
    @DisplayName(value = "test same words getter functionality")
    @Tag("getter")
    class GetterTest {
        @Test
        @Order(2)
        void getIdReturnsDefaultValueIfNoOtherWasPassed() {
            assertThat(sameWords.getId()).isZero();
        }

        @Test
        @Order(2)
        void getIdReturnsUpdatedValueAfterSet() {
            sameWords = new SameWords(id, null, null);
            assertThat(sameWords.getId()).isEqualTo(id);
        }

        @Test
        @Order(2)
        void getWordReturnsDefaultValueIfNoOtherWasPassed() {
            assertThat(sameWords.getWord1()).isNull();
            assertThat(sameWords.getWord2()).isNull();
        }

        @Test
        @Order(2)
        void getWordReturnsUpdatedValueAfterSet() {
            sameWords = new SameWords(id, word1, word2);
            assertThat(sameWords.getWord1()).isSameAs(word1);
            assertThat(sameWords.getWord2()).isSameAs(word2);
        }
    }

    @Test
    @Order(3)
    void setIdSetCorrectValue() {
        sameWords.setId(id);
        try {
            Field fieldId = sameWords.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            long actualId = fieldId.getLong(sameWords);
            assertThat(actualId).isEqualTo(id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @Order(3)
    void setWordSetCorrectValue() {
        sameWords.setWord1(word1);
        sameWords.setWord2(word2);
        try {
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
