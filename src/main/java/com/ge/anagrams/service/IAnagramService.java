package com.ge.anagrams.service;

import com.ge.anagrams.api.request.AnagramRequestDto;
import com.ge.anagrams.api.response.AnagramResponseDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface IAnagramService {
    void save(AnagramRequestDto request);
    Boolean validateWords(AnagramRequestDto request);
    HashMap<String, List<String>> validateAnagrams(List<String> words);
    AnagramResponseDto filterAnagrams(List<String> words);
    AnagramResponseDto validatePhrases(AnagramRequestDto request);
}
