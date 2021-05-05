package com.ge.anagrams.repository.service;

import java.util.List;

public interface IWordRepositoryService {
    void save(String word);
    List<String> findAll();
}
