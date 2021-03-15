package com.word.AutoComplete.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WordsResponse {
    private String status;
    private ArrayList<String> words;
}
