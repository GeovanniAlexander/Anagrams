package com.ge.anagrams.service;

import com.ge.anagrams.api.request.AnagramRequestDto;
import com.ge.anagrams.api.response.AnagramResponseDto;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AnagramsServicesMockTest {

    @InjectMocks
    private AnagramServiceImpl anagramService;

    @Test
    public void whenWordsAreAnagramsThenReturnTrue() {
        AnagramRequestDto words = new AnagramRequestDto("abcdefxgxhgigjhkjlkmnopqrstuvwxyz","vwkjlfxgxoykqrstuzmndepabcxhgigjh");
        Assert.assertTrue(anagramService.validateWords(words));
    }

    @Test
    public void whenWordsAreNotAnagramsThenReturnFalse() {
        AnagramRequestDto words = new AnagramRequestDto("Test","estt");
        Assert.assertFalse(anagramService.validateWords(words));
    }

    @Test
    public void whenPhrasesHasAnagramsReturnCountOfAnagrams() {
        AnagramRequestDto words = new AnagramRequestDto("angela es conservadora","ellos alegan que ella es muy conversadora");
        AnagramResponseDto wordsValidated = anagramService.validatePhrases(words);
        Assertions.assertThat(wordsValidated.getCount()).isEqualTo(3);
    }

    @Test
    public void whenPhrasesHasNotAnagramsReturnEmptyListWords() {
        AnagramRequestDto words = new AnagramRequestDto("Angela le encanta comer","ellos alegan que ella es muy conversadora");
        AnagramResponseDto wordsValidated = anagramService.validatePhrases(words);
        Assertions.assertThat(wordsValidated.getWords().size()).isEqualTo(0);
    }

}
