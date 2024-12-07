package com.hangman.core.api.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hangman.core.api.controllers.dto.DtoPlayer;
import com.hangman.core.entities.Game;
import com.hangman.core.entities.Player;
import com.hangman.core.services.GameService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/api/game")
public class GameController {

    private final GameService gameService;


    @PostMapping("/start")
    public Game startGame(@Valid @RequestBody DtoPlayer dtoPlayer) {
        ObjectMapper mapper = new ObjectMapper();
        Player player =  mapper.convertValue(dtoPlayer, Player.class);
        return gameService.getGame(player);
    }

    @PostMapping("/guess/{letter}")
    public Game makeGuess(@PathVariable char  letter, @RequestBody Long gameId) {
        return gameService.guess(gameId, letter);
    }
    
}
