package com.ge.anagrams.service;

import com.ge.anagrams.api.request.AnagramRequestDto;
import com.ge.anagrams.repository.service.IWordRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnagramServiceImpl implements IAnagramService {

    private final IWordRepositoryService wordRepository;

    @Override
    public void save(AnagramRequestDto request){
        wordRepository.save(request.getWord1());
    }
}
