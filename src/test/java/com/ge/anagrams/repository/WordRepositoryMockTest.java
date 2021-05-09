package com.ge.anagrams.repository;

import com.ge.anagrams.repository.service.IPhraseRepositoryService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@AutoConfigureDataMongo
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class WordRepositoryMockTest {

    @Autowired
    private IPhraseRepositoryService wordRepository;

    @Test
    public void whenFindWord_thenReturnListWords(){
        wordRepository.save("testWord");
        List<String> phrase = wordRepository.findAll();

        Assertions.assertThat(phrase.size()).isEqualTo(1);
    }

}
