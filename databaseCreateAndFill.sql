DROP SCHEMA IF EXISTS dictionary;
CREATE SCHEMA IF NOT EXISTS dictionary;
USE dictionary;

# create words table
DROP TABLE IF EXISTS words;
CREATE TABLE IF NOT EXISTS words
(
    id   bigint      not null auto_increment,
    name varchar(45) not null,
    PRIMARY KEY (id)
);

# create same words table
DROP TABLE IF EXISTS same_words;
CREATE TABLE IF NOT EXISTS same_words
(
    id       bigint not null auto_increment,
    word1_id bigint not null,
    word2_id bigint not null,
    PRIMARY KEY (id),
    FOREIGN KEY (word1_id) REFERENCES words (id) ON DELETE CASCADE,
    FOREIGN KEY (word2_id) REFERENCES words (id) ON DELETE CASCADE
);

INSERT INTO words (name)
VALUES ('cat'),
       ('friend'),
       ('companion'),
       ('kitty'),
       ('book'),
       ('novel'),
       ('romance');

INSERT INTO same_words (word1_id, word2_id)
VALUES (1, 4),
       (4, 1),
       (2, 3),
       (3, 2),
       (5, 6),
       (6, 5),
       (6, 7),
       (7, 6);

# get words name for same words id pairs
SELECT w.name as 'first word', w2.name as 'second word'
FROM same_words
         JOIN words w on w.id = same_words.word1_id
         JOIN words w2 on w2.id = same_words.word2_id;

# select all same words to word with id
SELECT w.id as 'id', w.name as 'name'
FROM same_words
         JOIN words w on w.id = same_words.word2_id
WHERE word1_id = 6;

# delete word with id
DELETE
FROM words
WHERE id = 1;
