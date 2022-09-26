package com.similiz.dictionary.entity;

import org.junit.jupiter.api.*;

import java.lang.reflect.Field;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Tag("fast")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class WordTest {

    private static final long[] ids = new long[]{
            1, 12, 45, 6, 909, 4, 32, 100000, 2
    };
    private static final String[] words = new String[]{
            "apple", "pie", "book", "romance", "fruit", "orange"
    };
    private long id;
    private String name;
    private Word word;
    private static Random idRandomizer;
    private static Random wordRandomizer;

    @BeforeAll
    static void initializeStatic() {
        idRandomizer = new Random(2854035);
        wordRandomizer = new Random(1795347);
    }

    @BeforeEach
    void initializeWord() {
        id = ids[idRandomizer.nextInt(ids.length)];
        name = words[wordRandomizer.nextInt(words.length)];
        word = new Word();
    }

    @Tag("constructor")
    @Test
    void noArgsConstructorDoesNotChangeValues() {
        try {
            Field fieldId = word.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            long id = fieldId.getLong(word);
            assertThat(id).isZero();

            Field fieldName = word.getClass().getDeclaredField("name");
            fieldName.setAccessible(true);
            String name = (String) fieldName.get(word);
            assertThat(name).isNull();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Tag("constructor")
    @Test
    void allArgsConstructorDoesNotChangeValues() {
        word = new Word(id, name);
        try {
            Field fieldId = word.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            long actualId = fieldId.getLong(word);
            assertThat(actualId).isEqualTo(id);

            Field fieldName = word.getClass().getDeclaredField("name");
            fieldName.setAccessible(true);
            String actualName = (String) fieldName.get(word);
            assertThat(actualName).isEqualTo(name);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Tag("builder")
    @Test
    void checkBuilderOnCorrectInsertValues() {
        word = Word.builder().id(id).name(name).build();
        try {
            Field fieldId = word.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            long actualId = fieldId.getLong(word);

            Field fieldName = word.getClass().getDeclaredField("name");
            fieldName.setAccessible(true);
            String actualName = (String) fieldName.get(word);

            assertAll(
                    () -> assertThat(actualId).isEqualTo(id),
                    () -> assertThat(actualName).isEqualTo(name)
            );
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Tag("getter")
    @Test
    void getIdReturnsDefaultValueIfNoOtherWasPassed() {
        assertThat(word.getId()).isZero();
    }

    @Tag("getter")
    @Test
    void getIdReturnsUpdatedValueAfterSet() {
        word = new Word(id, null);
        assertThat(word.getId()).isEqualTo(id);
    }

    @Tag("getter")
    @Test
    void getNameReturnsDefaultValueIfNoOtherWasPassed() {
        assertThat(word.getName()).isNull();
    }

    @Tag("getter")
    @Test
    void getNameReturnsUpdatedValueAfterSet() {
        word = new Word(0, name);
        assertThat(word.getName()).isEqualTo(name);
    }

    @Tag("setter")
    @Test
    void setIdSetCorrectValue() {
        word.setId(id);
        try {
            Field fieldId = word.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            long actualId = fieldId.getLong(word);
            assertThat(actualId).isEqualTo(id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Tag("setter")
    @Test
    void setNameSetCorrectValue() {
        word.setName(name);
        try {
            Field fieldName = word.getClass().getDeclaredField("name");
            fieldName.setAccessible(true);
            String actualName = (String) fieldName.get(word);
            assertThat(actualName).isEqualTo(name);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
