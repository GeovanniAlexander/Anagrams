package com.ge.anagrams.repository.service;

import com.ge.anagrams.entity.PhraseEntity;

import java.util.List;

public interface IPhraseRepositoryService {
    void save(String phrase);
    List<String> findAll();
    PhraseEntity findByPhrase(String phrase);
    List<String> findByStatusNot(boolean status);
}
