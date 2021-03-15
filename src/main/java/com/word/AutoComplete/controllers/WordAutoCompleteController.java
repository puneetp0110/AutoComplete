package com.word.AutoComplete.controllers;

import com.word.AutoComplete.Exceptions.WordException;
import com.word.AutoComplete.models.WordsResponse;
import com.word.AutoComplete.services.WordAutoCompleteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value="/api/autoComplete")
public class WordAutoCompleteController {

    @Autowired
    WordAutoCompleteService wordAutoCompleteService;

    @RequestMapping(value="/word", method= RequestMethod.GET, produces = "application/json")
    public ResponseEntity getAutoComplete(@RequestParam String prefix) throws WordException {
        try {
            return ResponseEntity.ok(new WordsResponse("SUCCESS",wordAutoCompleteService.getAllPossibleWords(prefix.toLowerCase())));
        } catch (WordException e){
            return ResponseEntity.badRequest().body(new WordException(e.getErrorCode(),e.getErrorMessage()));
        } catch (Exception ex){
            return ResponseEntity.badRequest().body(new WordException("Error",ex.getLocalizedMessage()));
        }
    }

}
