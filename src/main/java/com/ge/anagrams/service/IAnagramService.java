package com.ge.anagrams.service;

import com.ge.anagrams.api.request.AnagramRequest;
import com.ge.anagrams.api.request.AnagramSinglePhraseRequest;
import com.ge.anagrams.api.response.AnagramResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public interface IAnagramService {
    Boolean validateWords(AnagramRequest request);
    AnagramResponse savePhrase(AnagramSinglePhraseRequest request);
    AnagramResponse getAnagrams();
    HashMap<String, List<String>> validateAnagrams(List<String> words);
    AnagramResponse filterAnagrams(List<String> words);
    AnagramResponse validatePhrases(AnagramRequest request);
}
