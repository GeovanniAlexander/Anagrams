package com.ge.anagrams.api.request;

import com.ge.anagrams.commons.constants.IMessagesResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class AnagramRequest {

    @NotNull(message = IMessagesResponse.NOT_NULL)
    @Size(min = 1, message = IMessagesResponse.NOT_EMPTY)
    private String phrase1;
    @NotNull(message = IMessagesResponse.NOT_NULL)
    @Size(min = 1, message = IMessagesResponse.NOT_EMPTY)
    private String phrase2;
}
