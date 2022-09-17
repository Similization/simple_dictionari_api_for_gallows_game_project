package com.similiz.dictionary.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "words", schema = "dictionary")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;
    @Column(name = "name")
    private String name;
}
