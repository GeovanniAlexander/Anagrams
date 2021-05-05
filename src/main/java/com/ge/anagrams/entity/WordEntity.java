package com.ge.anagrams.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "words")
@Data
@NoArgsConstructor
public class WordEntity {

    @Id
    private String id;
    private String word;

    public WordEntity(String word) {
        this.word = word;
    }
}
