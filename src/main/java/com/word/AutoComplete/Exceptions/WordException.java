package com.word.AutoComplete.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class WordException extends Exception {
    private String errorMessage;
    private String errorCode;
}
