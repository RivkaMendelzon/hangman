package com.hangman.core.services;

import org.springframework.stereotype.Service;

import com.hangman.core.entities.Player;
import com.hangman.core.repositories.PlayerRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class PlayerService {
    
    private final PlayerRepository playerRepository;

    public Player createPlayer(Player player){
        return playerRepository.save(player);
    }

    public Player findByPlayerEmail(String playerEmail) {
       return  playerRepository.findByPlayerEmail(playerEmail);
    }
}
