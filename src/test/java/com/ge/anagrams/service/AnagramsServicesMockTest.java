package com.ge.anagrams.service;

import com.ge.anagrams.api.request.AnagramRequest;
import com.ge.anagrams.api.request.AnagramSinglePhraseRequest;
import com.ge.anagrams.api.response.AnagramResponse;
import com.ge.anagrams.entity.PhraseEntity;
import com.ge.anagrams.repository.service.IPhraseRepositoryService;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class AnagramsServicesMockTest {

    @Mock
    private IPhraseRepositoryService phraseRepository;

    @InjectMocks
    private AnagramServiceImpl anagramService;

    @Test
    public void whenWordsAreAnagramsThenReturnTrue() {
        AnagramRequest words = new AnagramRequest("abcdefxgxhgigjhkjlkmnopqrstuvwxyz", "vwkjlfxgxoykqrstuzmndepabcxhgigjh");
        Assert.assertTrue(anagramService.validateWords(words));
    }

    @Test
    public void whenWordsAreNotAnagramsThenReturnFalse() {
        AnagramRequest words = new AnagramRequest("Test", "estt");
        Assert.assertFalse(anagramService.validateWords(words));
    }

    @Test
    public void whenPhrasesHasAnagramsReturnCountOfAnagrams() {
        AnagramRequest words = new AnagramRequest("angela es conservadora", "ellos alegan que ella es muy conversadora");
        AnagramResponse wordsValidated = anagramService.validatePhrases(words);
        Assertions.assertThat(wordsValidated.getCount()).isEqualTo(3);
    }

    @Test
    public void whenPhrasesHasNotAnagramsReturnEmptyListWords() {
        AnagramRequest words = new AnagramRequest("Angela le encanta comer", "ellos alegan que ella es muy conversadora");
        AnagramResponse wordsValidated = anagramService.validatePhrases(words);
        Assertions.assertThat(wordsValidated.getWords().size()).isEqualTo(0);
    }

    @Test
    public void whenPersistentPhrasesHasAnagramsReturnListWords() {
        Mockito.when(phraseRepository
                .findByStatusNot(Mockito.anyBoolean()))
                .thenReturn(Arrays.asList(
                        "test solo tres anagramas",
                        "este ttse no mas tsre",
                        "anaaramsg final"
                ));
        AnagramResponse wordsValidated = anagramService.getAnagrams();
        Assertions.assertThat(wordsValidated.getCount()).isEqualTo(3);
    }

    @Test
    public void whenPersistentPhrasesHasNotAnagramsReturnEmptyList() {
        Mockito.when(phraseRepository
                .findByStatusNot(Mockito.anyBoolean()))
                .thenReturn(Arrays.asList(
                        "test no anagramas",
                        "not anagrams",
                        "just testing for anag"
                ));
        AnagramResponse wordsValidated = anagramService.getAnagrams();
        Assertions.assertThat(wordsValidated.getWords().size()).isEqualTo(0);
    }

    @Test
    public void whenPersistentPhrasesExistsReturnError() {
        PhraseEntity phrase = new PhraseEntity("test");
        AnagramSinglePhraseRequest request = new AnagramSinglePhraseRequest();
        request.setPhrase("test");
        Mockito.when(phraseRepository
                .findByPhrase(Mockito.anyString()))
                .thenReturn(phrase);
        try {
            AnagramResponse wordsValidated = anagramService.savePhrase(request);
        }catch (ResponseStatusException err) {
            Assertions.assertThat(err.getStatus()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    public void whenPersistentPhrasesAreInsufficientReturnError() {
        Mockito.when(phraseRepository
                .findByStatusNot(Mockito.anyBoolean()))
                .thenReturn(Arrays.asList(
                        "test no anagramas",
                        "not anagrams"
                ));
        try{
            AnagramResponse wordsValidated = anagramService.getAnagrams();
        }catch (ResponseStatusException err) {
            Assertions.assertThat(err.getStatus()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    public void whenPersistentPhrasesExceededLimitReturnError() {
        AnagramSinglePhraseRequest request = new AnagramSinglePhraseRequest();
        request.setPhrase("test");
        Mockito.when(phraseRepository
                .findByStatusNot(Mockito.anyBoolean()))
                .thenReturn(Arrays.asList(
                        "test no anagramas",
                        "not anagrams",
                        "test for exceed limit",
                        "just spaces"
                ));
        Mockito.when(phraseRepository.findByPhrase("test")).thenReturn(null);
        try{
            AnagramResponse wordsValidated = anagramService.savePhrase(request);
        }catch (ResponseStatusException err) {
            Assertions.assertThat(err.getStatus()).isEqualByComparingTo(HttpStatus.BAD_REQUEST);
        }
    }
}
