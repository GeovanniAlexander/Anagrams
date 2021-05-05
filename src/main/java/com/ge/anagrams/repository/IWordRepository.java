package com.ge.anagrams.repository;

import com.ge.anagrams.entity.WordEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWordRepository extends MongoRepository<WordEntity, String> {
}
