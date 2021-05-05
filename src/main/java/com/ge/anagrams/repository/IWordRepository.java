package com.ge.anagrams.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface IWordRepository extends MongoRepository<IWordRepository, String> {
}
