package com.ge.anagrams.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnagramRequestDto {
    String word1;
    String word2;
}
