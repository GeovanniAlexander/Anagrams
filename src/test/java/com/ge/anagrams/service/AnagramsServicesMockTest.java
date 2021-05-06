package com.ge.anagrams.service;

import com.ge.anagrams.api.request.AnagramRequestDto;
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
    public void whenAreAnagramsThenReturnTrue() {
        AnagramRequestDto words = new AnagramRequestDto("abcdefghijklmnopqrstuvwxyz","aflmzgbcnvwxhistyopqrdeujk");
        Assert.assertTrue(anagramService.validateWords(words));
    }

    @Test
    public void whenAreNotAnagramsThenReturnFalse() {
        AnagramRequestDto words = new AnagramRequestDto("Test","estt");
        Assert.assertFalse(anagramService.validateWords(words));
    }

}
