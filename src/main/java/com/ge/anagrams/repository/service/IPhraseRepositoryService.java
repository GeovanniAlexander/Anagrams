package com.ge.anagrams.repository.service;

import java.util.List;

public interface IPhraseRepositoryService {
    void save(String phrase);
    List<String> findAll();
}
