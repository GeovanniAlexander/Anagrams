package com.ge.anagrams.repository.service;

import com.ge.anagrams.entity.PhraseEntity;
import com.ge.anagrams.repository.IPhraseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PhraseRepositoryImpl implements IPhraseRepositoryService {

    private final IPhraseRepository wordRepository;

    @Override
    public void save(String phrase) {
        PhraseEntity phraseIns = new PhraseEntity(phrase);
        wordRepository.save(phraseIns);
    }

    @Override
    public List<String> findAll() {
        return wordRepository.findAll().stream().map(PhraseEntity::getPhrase).collect(Collectors.toList());
    }
}
