package com.ge.anagrams.service;

import com.ge.anagrams.api.request.AnagramRequest;
import com.ge.anagrams.api.request.AnagramSinglePhraseRequest;
import com.ge.anagrams.api.response.AnagramResponse;
import com.ge.anagrams.commons.constants.IMessagesResponse;
import com.ge.anagrams.entity.PhraseEntity;
import com.ge.anagrams.repository.service.IPhraseRepositoryService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnagramServiceImpl implements IAnagramService {

    private final IPhraseRepositoryService phraseRepository;

    @Override
    public Boolean validateWords(AnagramRequest request) {
        List<String> words = Arrays.asList(
                request.getPhrase1(),
                request.getPhrase2()
        );
        HashMap<String, List<String>> wordsMap = validateAnagrams(words);

        return wordsMap.size() <= 1;
    }

    @Override
    public AnagramResponse validatePhrases(AnagramRequest request) {
        List<String> words = Arrays.asList(
                request.getPhrase1().concat(IMessagesResponse.BLANK_SPACE).concat(request.getPhrase2())
                .split(IMessagesResponse.BLANK_SPACE)
        );

        return filterAnagrams(words);
    }

    @Override
    public AnagramResponse savePhrase(AnagramSinglePhraseRequest request) {
        PhraseEntity phrase = phraseRepository.findByPhrase(request.getPhrase());
        if(phrase != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, IMessagesResponse.PHRASE_ALREADY_EXISTS);
        }
        List<String> persistentPhrases = phraseRepository.findByStatusNot(false);
        if(persistentPhrases.size() >= 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, IMessagesResponse.PHRASES_LIMIT_EXCEEDED);
        }
        phraseRepository.save(request.getPhrase());
        return null;
    }

    @Override
    public AnagramResponse getAnagrams() {
        List<String> persistentPhrases = phraseRepository.findByStatusNot(false);
        if( persistentPhrases.size() < 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, IMessagesResponse.INSUFFICIENT_PHRASES);
        }
        String phrases = String.join(IMessagesResponse.BLANK_SPACE, persistentPhrases);
        List<String> words = Arrays.asList(
                phrases.split(IMessagesResponse.BLANK_SPACE)
        );
        phraseRepository.updateAllStatus(false);
        return filterAnagrams(words);
    }

    @Override
    public AnagramResponse filterAnagrams(List<String> words) {
        List<String> anagrams = new ArrayList<>();
        List<List<String>> wordsMap = validateAnagrams(words).values().stream()
                .filter(strings -> strings.size() > 1)
                .peek(anagrams::addAll).collect(Collectors.toList());

        return new AnagramResponse(wordsMap.size(), anagrams);
    }

    @Override
    public HashMap<String, List<String>> validateAnagrams(List<String> words) {
        HashMap<String, List<String>> wordsMap = new HashMap<>();
        words.forEach(word -> {
            String sortedWord = Arrays.stream(word.split(Strings.EMPTY)).sorted().collect(Collectors.joining());
            if (!wordsMap.containsKey(sortedWord)) {
                wordsMap.put(sortedWord, new ArrayList<>());
            }
            wordsMap.get(sortedWord).add(word);
        });

        return wordsMap;
    }
}