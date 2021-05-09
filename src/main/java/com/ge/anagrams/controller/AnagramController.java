package com.ge.anagrams.controller;

import com.ge.anagrams.api.request.AnagramRequest;
import com.ge.anagrams.api.request.AnagramSinglePhraseRequest;
import com.ge.anagrams.api.response.AnagramResponse;
import com.ge.anagrams.service.IAnagramService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "anagrams")
@AllArgsConstructor
public class AnagramController {

    private final IAnagramService anagramService;

    @GetMapping(value = "/validation/words")
    public ResponseEntity<Boolean> validateWords(@Valid @RequestBody AnagramRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, formatMessage(result));
        }

        return ResponseEntity.ok(anagramService.validateWords(request));
    }

    @GetMapping(value = "/validation/phrases")
    public ResponseEntity<AnagramResponse> validatePhrases(@Valid @RequestBody AnagramRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        return ResponseEntity.ok(anagramService.validatePhrases(request));
    }

    @PostMapping(value = "/phrases")
    public ResponseEntity<AnagramResponse> validatePersistentPhrases(@Valid @RequestBody AnagramSinglePhraseRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        return ResponseEntity.ok(anagramService.savePhrase(request));
    }

    @GetMapping(value = "/validation/persistent/phrases")
    public ResponseEntity<AnagramResponse> getAnagrams() {
        return ResponseEntity.ok(anagramService.getAnagrams());
    }

    private String formatMessage(BindingResult result) {
        return result.getFieldErrors()
                .stream()
                .map(item -> {
                    return item.getField() + ": " + item.getDefaultMessage() + "  ";
                }).collect(Collectors.joining());
    }
}
