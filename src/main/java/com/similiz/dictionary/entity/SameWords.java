package com.similiz.dictionary.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "same_words", schema = "dictionary")
public class SameWords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @ManyToOne
    @JoinColumn(name = "word1_id")
    private Word word1;

    @ManyToOne
    @JoinColumn(name = "word2_id")
    private Word word2;
}
