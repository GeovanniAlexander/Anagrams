package com.ge.anagrams.repository.service;

import com.ge.anagrams.entity.PhraseEntity;
import com.ge.anagrams.repository.IPhraseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PhraseRepositoryImpl implements IPhraseRepositoryService {

    private final IPhraseRepository phraseRepository;

    @Override
    public void save(String phrase) {
        PhraseEntity phraseIns = new PhraseEntity(phrase);
        phraseRepository.save(phraseIns);
    }

    @Override
    public List<String> findAll() {
        return phraseRepository.findAll().stream().map(PhraseEntity::getPhrase).collect(Collectors.toList());
    }

    @Override
    public PhraseEntity findByPhrase(String phrase) {
        return phraseRepository.findByPhrase(phrase);
    }

    @Override
    public List<String> findByStatusNot(boolean status) {
        return phraseRepository.findByStatusNot(status).stream().map(PhraseEntity::getPhrase).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateAllStatus(boolean status) {
        List<PhraseEntity> phrases = phraseRepository.findByStatusNot(status);
        phrases.forEach(phrase ->{
            phrase.setStatus(status);
            phraseRepository.save(phrase);
        });
    }
}
