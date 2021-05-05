package com.ge.anagrams.service;

import com.ge.anagrams.api.request.AnagramRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface IAnagramService {
    void save(AnagramRequestDto request);
}
