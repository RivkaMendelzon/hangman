 package com.hangman.core.api.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.hangman.core.entities.GameSession;
import com.hangman.core.services.GameService;

@Controller
public class GameSessionController {
 private GameService gameService;
 
    @MessageMapping("/join")
    @SendTo("/topic/join}")
    public String invitePlayer(@RequestBody GameSession gameSession) {
        return gameService.create2games(gameSession);
    }

    @MessageMapping("/guess/{gameId}")
    @SendTo("/topic/guesses")
    public void  handleGuess(@PathVariable GameSession game, @RequestBody char guess) {
        gameService.guessJoinedGame(game, guess);
    }
}
