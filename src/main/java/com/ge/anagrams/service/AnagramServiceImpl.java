package com.ge.anagrams.service;

import com.ge.anagrams.api.request.AnagramRequestDto;
import com.ge.anagrams.api.request.AnagramSingle;
import com.ge.anagrams.api.response.AnagramResponseDto;
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
    public void save(AnagramRequestDto request){
        phraseRepository.save(request.getPhrase1());
    }

    @Override
    public Boolean validateWords(AnagramRequestDto request) {
        List<String> words = Arrays.asList(
                request.getPhrase1(),
                request.getPhrase2()
        );
        HashMap<String, List<String>> wordsMap = validateAnagrams(words);

        return wordsMap.size() <= 1;
    }

    @Override
    public AnagramResponseDto validatePhrases(AnagramRequestDto request) {
        List<String> words = Arrays.asList(
                request.getPhrase1().concat(" ").concat(request.getPhrase2())
                .split(" ")
        );

        return filterAnagrams(words);
    }

    @Override
    public AnagramResponseDto savePhrase(AnagramSingle request) {
        PhraseEntity phrase = phraseRepository.findByPhrase(request.getPhrase());
        if(phrase != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La frase ya ha sido almacenada");
        }
        List<String> persistentPhrases = phraseRepository.findByStatusNot(false);
        if(persistentPhrases.size() == 3) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya fueron almacenadas 3 frases");
        }
        phraseRepository.save(request.getPhrase());
        return null;
    }

    @Override
    public AnagramResponseDto getAnagrams() {
        List<String> persistentPhrases = phraseRepository.findByStatusNot(false);
        String phrases = String.join(" ", persistentPhrases);
        List<String> words = Arrays.asList(
                phrases.split(" ")
        );
        phraseRepository.updateAllStatus(false);
        return filterAnagrams(words);
    }

    @Override
    public AnagramResponseDto filterAnagrams(List<String> words) {
        List<String> anagrams = new ArrayList<>();
        List<List<String>> wordsMap = validateAnagrams(words).values().stream()
                .filter(strings -> strings.size() > 1)
                .peek(anagrams::addAll).collect(Collectors.toList());

        return new AnagramResponseDto(wordsMap.size(), anagrams);
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