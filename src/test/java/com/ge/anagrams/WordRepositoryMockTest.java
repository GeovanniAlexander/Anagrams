package com.ge.anagrams;

import com.ge.anagrams.repository.service.IWordRepositoryService;
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
    private IWordRepositoryService wordRepository;

    @Test
    public void whenFindWord_thenReturnListWords(){
        wordRepository.save("testWord");
        List<String> words = wordRepository.findAll();

        Assertions.assertThat(words.size()).isEqualTo(1);
    }

}
