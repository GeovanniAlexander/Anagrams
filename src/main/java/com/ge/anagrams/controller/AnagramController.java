package com.ge.anagrams.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.anagrams.api.request.AnagramRequestDto;
import com.ge.anagrams.api.request.AnagramSingle;
import com.ge.anagrams.api.response.AnagramResponseDto;
import com.ge.anagrams.api.response.ErrorMessage;
import com.ge.anagrams.service.IAnagramService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "anagrams")
@AllArgsConstructor
public class AnagramController {

    private final IAnagramService anagramService;

    @PostMapping(value = "/validate/words")
    public ResponseEntity<Boolean> validateWords(@Valid @RequestBody AnagramRequestDto request, BindingResult result) {
        if(result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        return ResponseEntity.ok(anagramService.validateWords(request));
    }

    @PostMapping(value = "/validate/phrases")
    public ResponseEntity<AnagramResponseDto> validatePhrases(@Valid @RequestBody AnagramRequestDto request, BindingResult result) {
        if(result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }

        return ResponseEntity.ok(anagramService.validatePhrases(request));
    }

    @PostMapping(value = "/validate/persistent/phrases")
    public ResponseEntity<AnagramResponseDto> validatePersistentPhrases(@Valid @RequestBody AnagramSingle request, BindingResult result) {
        return ResponseEntity.ok(anagramService.savePhrase(request));
    }

    @PostMapping
    public void saveWord(@RequestBody AnagramRequestDto request){
        anagramService.save(request);
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors()
                .stream()
                .map(item -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(item.getField(), item.getDefaultMessage());
                    return error;
                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .message(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        }catch (JsonProcessingException err) {
            err.printStackTrace();
        }

        return jsonString;
    }
}
