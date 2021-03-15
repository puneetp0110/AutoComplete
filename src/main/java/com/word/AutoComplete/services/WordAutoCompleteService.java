package com.word.AutoComplete.services;

import com.word.AutoComplete.Exceptions.WordException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import static com.word.AutoComplete.utils.LoadWordsInCache.words;

@Service
public class WordAutoCompleteService {

    public ArrayList<String> getAllPossibleWords(String prefix) throws WordException{
        if(prefix.length()<3) { throw new WordException("PrefixError","Length Should be greater than 3 words for prefix"); }
        ArrayList<String> wordsFound = new ArrayList<>();
        for(String st : words.get(String.valueOf(prefix.charAt(0)))){
            if(st.startsWith(prefix)){
                wordsFound.add(st);
            }
        }
        return wordsFound;
    }
}
