package com.word.AutoComplete.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Component
public class LoadWordsInCache {
      public static HashMap<String, ArrayList<String>> words = new HashMap<>();
      public LoadWordsInCache() throws FileNotFoundException, IOException {
          String path = System.getProperty("user.dir") + "/words.txt";
          File file = new File(path);

          BufferedReader br = new BufferedReader(new FileReader(file));

          String st;
          while ((st = br.readLine()) != null) {
              if(words.containsKey(String.valueOf(st.charAt(0)))){
                  ArrayList<String> local = words.get(String.valueOf(st.charAt(0)));
                  local.add(st.toLowerCase());
                  words.put(String.valueOf(st.charAt(0)),local);
              } else {
                  ArrayList<String> local = new ArrayList<>();
                  local.add(st);
                  words.put(String.valueOf(st.charAt(0)),local);
              }
          }
        }
}
