package com.ge.anagrams.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AnagramResponse {
    private int count;
    private List<String> words;
}
