package com.hangman.core.services;

import org.springframework.stereotype.Service;

import com.hangman.core.entities.Game;
import com.hangman.core.repositories.GameRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WordService {
    private final GameRepository gameRepository;

    public Game checkGuess(Game currentGame, char guess) {

        guess = Character.toLowerCase(guess);
        if((currentGame.getGuessedWord().contains(guess+"")) || (currentGame.getIncorrectGuesses()!=null && currentGame.getIncorrectGuesses().contains(guess+""))) 
            return currentGame;
 
        StringBuilder updatedProgress = new StringBuilder(currentGame.getGuessedWord()+"");

        if (currentGame.getWordToGuess().indexOf(guess) >= 0) { 
            for (int i = 0; i < currentGame.getWordToGuess().length(); i++) {
                if (currentGame.getWordToGuess().charAt(i) == guess) {
                    updatedProgress.setCharAt(i, guess); 
                }
            }
            currentGame.setGuessedWord(updatedProgress.toString());
            if (currentGame.getWordToGuess().equals(updatedProgress.toString())) {
                currentGame.setActiveGame(false);
            }

        }   
            else{
                currentGame.setIncorrectGuesses(currentGame.getIncorrectGuesses()+", "+ guess);
                if(currentGame.getIncorrectGuesses().length() >= 20)
                    currentGame.setActiveGame(false);
            }
        
        gameRepository.save(currentGame);
        return currentGame;   
    }
}