package com.ge.anagrams.repository.service;

import com.ge.anagrams.entity.WordEntity;
import com.ge.anagrams.repository.IWordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WordRepositoryImpl implements IWordRepositoryService{

    private final IWordRepository wordRepository;

    @Override
    public void save(String word) {
        WordEntity wordIns = new WordEntity(word);
        wordRepository.save(wordIns);
    }

    @Override
    public List<String> findAll() {
        return wordRepository.findAll().stream().map(WordEntity::getWord).collect(Collectors.toList());
    }
}
