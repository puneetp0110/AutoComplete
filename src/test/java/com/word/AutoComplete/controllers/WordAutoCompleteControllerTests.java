package com.word.AutoComplete.controllers;

import com.word.AutoComplete.Exceptions.WordException;
import com.word.AutoComplete.models.WordsResponse;
import com.word.AutoComplete.services.WordAutoCompleteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordAutoCompleteControllerTests {

    @InjectMocks
    private  WordAutoCompleteController wordAutoCompleteController;

    @Mock
    private WordAutoCompleteService wordAutoCompleteService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testWords() throws WordException {
        ArrayList<String> wordResponse = new ArrayList<>();
        wordResponse.add("test");
        wordResponse.add("test2");
        WordsResponse wordsResponse = new WordsResponse("SUCCESS",wordResponse);
        ResponseEntity expectedResponse = ResponseEntity.ok(wordsResponse);
        Mockito.when(wordAutoCompleteService.getAllPossibleWords(Mockito.anyString())).thenReturn(wordResponse);
        ResponseEntity actualResponse = wordAutoCompleteController.getAutoComplete(Mockito.anyString());
        Assert.assertEquals(expectedResponse.getStatusCode(),actualResponse.getStatusCode());
        Assert.assertEquals(expectedResponse.getBody(),actualResponse.getBody());
    }

    @Test
    public void testWordsInvalidPrefix() throws WordException {
        ResponseEntity expectedResponse = ResponseEntity.badRequest().body(new WordException("prefixError","Length Should be greater than 3 words for prefix"));
        Mockito.when(wordAutoCompleteService.getAllPossibleWords(Mockito.anyString())).thenThrow(new WordException("prefixError","Length Should be greater than 3 words for prefix"));
        ResponseEntity actualResponse = wordAutoCompleteController.getAutoComplete(Mockito.anyString());
        Assert.assertEquals(expectedResponse.getStatusCode(),actualResponse.getStatusCode());
    }
}
