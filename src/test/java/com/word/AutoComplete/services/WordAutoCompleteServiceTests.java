package com.word.AutoComplete.services;

import com.word.AutoComplete.Exceptions.WordException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordAutoCompleteServiceTests {
    @InjectMocks
    private WordAutoCompleteService wordAutoCompleteService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void regularWordAutoCompleteFlow() throws WordException {
        ArrayList<String> expectedResponse = new ArrayList<>();
        expectedResponse.add("abaxial");
        expectedResponse.add("abaxile");
        String prefix = "abaxi";
        ArrayList<String> actualResponse = wordAutoCompleteService.getAllPossibleWords(prefix);
        Assert.assertEquals(expectedResponse,actualResponse);
    }
    @Test(expected = WordException.class)
    public void invalidPrefixText() throws WordException {
        String prefix = "ab";
        ArrayList<String> actualResponse = wordAutoCompleteService.getAllPossibleWords(prefix);
    }
}
