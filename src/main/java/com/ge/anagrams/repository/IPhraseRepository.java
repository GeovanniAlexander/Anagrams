package com.ge.anagrams.repository;

import com.ge.anagrams.entity.PhraseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhraseRepository extends MongoRepository<PhraseEntity, String> {
}
