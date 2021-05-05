package com.ge.anagrams.controller;

import com.ge.anagrams.api.request.AnagramRequestDto;
import com.ge.anagrams.service.AnagramServiceImpl;
import com.ge.anagrams.service.IAnagramService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "anagrams")
@AllArgsConstructor
public class AnagramController {

    private final IAnagramService anagramService;

    @PostMapping
    public void saveWord(@RequestBody AnagramRequestDto request){
        anagramService.save(request);
    }
}
