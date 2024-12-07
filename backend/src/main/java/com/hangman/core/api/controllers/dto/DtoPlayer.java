package com.hangman.core.api.controllers.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class DtoPlayer {

    private String playerName;

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }
    @Email
    private String playerEmail;

    public String getPlayerEmail() {
        return this.playerEmail;
    }

    public void setPlayerEmail(String playerEmail){
        this.playerEmail = playerEmail;
    }
}
