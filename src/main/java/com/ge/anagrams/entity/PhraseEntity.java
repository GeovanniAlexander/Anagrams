package com.ge.anagrams.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "phrases")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhraseEntity {

    @Id
    private String id;
    private String phrase;
    private Boolean status = true;

    public PhraseEntity(String phrase) {
        this.phrase = phrase;
    }
}
