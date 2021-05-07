package com.ge.anagrams.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AnagramSingle {

    @NotNull(message = "El campo no puede ser nulo")
    @Size(min = 1, message = "El campo no puede ser vacio")
    private String phrase;
}
