package com.similiz.dictionary.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Field;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class WordTest {
    private Word word;

    @BeforeEach
    void initializeWord() {
        word = new Word();
    }

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

    @Test
    void allArgsConstructorDoesNotChangeValues() {
        long newId = 14;
        String newName = "apple";
        word = new Word(newId, newName);
        try {
            Field fieldId = word.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            long id = fieldId.getLong(word);
            assertThat(id).isEqualTo(newId);

            Field fieldName = word.getClass().getDeclaredField("name");
            fieldName.setAccessible(true);
            String name = (String) fieldName.get(word);
            assertThat(name).isEqualTo(newName);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getIdReturnsDefaultValueIfNoOtherWasPassed() {
        assertThat(word.getId()).isZero();
    }

    @Test
    void getIdReturnsUpdatedValueAfterSet() {
        int newId = 4;
        word = new Word(newId, null);
        assertThat(word.getId()).isEqualTo(newId);
    }

    @Test
    void getNameReturnsDefaultValueIfNoOtherWasPassed() {
        assertThat(word.getName()).isNull();
    }

    @Test
    void getNameReturnsUpdatedValueAfterSet() {
        String newName = "apple";
        word = new Word(0, newName);
        assertThat(word.getName()).isEqualTo(newName);
    }

    @Test
    void setIdSetCorrectValue() {
        long newId = 23;
        word.setId(newId);
        try {
            Field fieldId = word.getClass().getDeclaredField("id");
            fieldId.setAccessible(true);
            long id = fieldId.getLong(word);
            assertThat(id).isEqualTo(newId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void setNameSetCorrectValue() {
        String newName = "pie";
        word.setName(newName);
        try {
            Field fieldName = word.getClass().getDeclaredField("name");
            fieldName.setAccessible(true);
            String name = (String) fieldName.get(word);
            assertThat(name).isEqualTo(newName);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
