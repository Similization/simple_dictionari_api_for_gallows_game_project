package com.similiz.dictionary.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Entity
@Table(name = "same_words", schema = "dictionary")
public class SameWords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "word1_id", nullable = false)
    private Word word1;

    @ManyToOne
    @JoinColumn(name = "word2_id", nullable = false)
    private Word word2;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SameWords)) return false;
        SameWords sameWords = (SameWords) o;
        return getId() == sameWords.getId() && getWord1().equals(sameWords.getWord1()) && getWord2().equals(sameWords.getWord2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getWord1(), getWord2());
    }
}
