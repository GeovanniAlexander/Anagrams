package com.ge.anagrams.api.request;

import com.ge.anagrams.commons.constants.IMessagesResponse;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class AnagramSinglePhraseRequest {

    @NotNull(message = IMessagesResponse.NOT_NULL)
    @Size(min = 1, message = IMessagesResponse.NOT_EMPTY)
    private String phrase;
}
