package com.hangman.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GameSession {

    private String sessionId;
    private Player player1;
    private Player player2;
    private String player1Word;
    private String player2Word;
    private boolean player1Turn = true;

    public GameSession(String sessionId, Player player1) {
        this.sessionId = sessionId;
        this.player1 = player1;
    }

    public void toggleTurn() {
        this.player1Turn = !this.player1Turn;
    }
}


