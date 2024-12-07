package com.hangman.core.services;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class RandomService {

    public String randomWord() {
        try {
            URL url = new URL("https://random-word-api.herokuapp.com/word?lang=en");
            HttpURLConnection connection;

                connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            String[] newWord = mapper.readValue(responseStream, String[].class);
            return newWord[0];
            } 
        catch (IOException ex) {
            return null;
        }
    }
}
