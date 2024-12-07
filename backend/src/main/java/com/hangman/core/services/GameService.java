package com.hangman.core.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hangman.core.entities.Game;
import com.hangman.core.entities.GameSession;
import com.hangman.core.entities.Player;
import com.hangman.core.repositories.GameRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@AllArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final RandomService randomService;
    private final WordService wordService;
    private final PlayerService playerService;

    public Game getGame(Player player)
    {
        Player existPlayer = playerService.findByPlayerEmail(player.getPlayerEmail());
        if(existPlayer != null)
        {            
            Game game = gameRepository.findByPlayerIdAndIsActiveGame(existPlayer.getPlayerId());
            if(game != null)
            {
                return game;
            }
        }
        else
        {
            existPlayer = playerService.createPlayer(player);
        }

        return startNewGame(existPlayer.getPlayerId());
    }

    public Game startNewGame(Long playerId)
    {
        String wordToGuess = randomService.randomWord();
        return creatGame(wordToGuess, playerId, null);
    }
    
    public void guessJoinedGame(GameSession gameSession, char guess){
        List<Game> currentGames = gameRepository.findBySessionId(gameSession.getSessionId());
        if(gameSession.isPlayer1Turn() && gameSession.getPlayer1().getPlayerId().equals(currentGames.get(0).getPlayerId()))
        {
            guess(currentGames.get(0).getGameId(), guess);
        }
        else 
        {
            if(!gameSession.isPlayer1Turn() && !gameSession.getPlayer1().getPlayerId().equals(currentGames.get(0).getPlayerId()))
                guess(currentGames.get(0).getGameId(), guess);
            else            
                guess(currentGames.get(1).getGameId(), guess);
        }
        gameSession.setPlayer1Turn(!gameSession.isPlayer1Turn());
    }
    

   public Game guess(Long gameId, char guess) {

        Game currentGame = gameRepository.findByGameId(gameId);
        if(!currentGame.isActiveGame())
            return currentGame;
        return wordService.checkGuess(currentGame, guess);       
   }

    public String create2games(GameSession gameSession){
        
        List<Game> game = gameRepository.findBySessionId(gameSession.getSessionId());
        if(game.isEmpty())
        {
            return "exist game";
        }
        Player existPlayerOne = playerService.findByPlayerEmail(gameSession.getPlayer1().getPlayerEmail());
        Player existPlayerTwo = playerService.findByPlayerEmail(gameSession.getPlayer2().getPlayerEmail());

        if(existPlayerOne == null)
            existPlayerOne = playerService.createPlayer(gameSession.getPlayer1());
        if(existPlayerTwo == null)
            existPlayerTwo = playerService.createPlayer(gameSession.getPlayer2());
        creatGame(gameSession.getPlayer1Word(), existPlayerOne.getPlayerId(), gameSession.getSessionId());
        creatGame(gameSession.getPlayer1Word(), existPlayerTwo.getPlayerId(), gameSession.getSessionId());

        return " new game created ";
    }
   
    public Game creatGame(String wordToGuess, Long playerId, String sessionGameId){

        Game newGame = new Game();
        newGame.setWordToGuess(wordToGuess);
        newGame.setGuessedWord("_".repeat(wordToGuess.length()));
        newGame.setIncorrectGuesses("");
        newGame.setActiveGame(true);
        newGame.setPlayerId(playerId);
        newGame.setSessionId(sessionGameId);

        return gameRepository.save(newGame);
    }

}
