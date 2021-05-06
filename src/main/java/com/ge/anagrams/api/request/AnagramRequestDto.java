package com.ge.anagrams.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class AnagramRequestDto {

    @NotNull(message = "El campo no puede ser nulo")
    @Size(min = 1, message = "El campo no puede ser vacio")
    private String phrase1;
    @NotNull(message = "El campo no puede ser nulo")
    @Size(min = 1, message = "El campo no puede ser vacio")
    private String phrase2;
}
