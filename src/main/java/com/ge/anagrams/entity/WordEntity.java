package com.ge.anagrams.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "words")
@Data
public class WordEntity {

    @Id
    String id;
    String word;

    public WordEntity(String word) {
        this.word = word;
    }
}