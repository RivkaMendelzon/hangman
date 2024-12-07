package com.hangman.core.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hangman.core.entities.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {

    @Query("SELECT g FROM Game g WHERE gameId = :gameId ")
    Game findByGameId(@Param("gameId") Long gameId);

    @Query("SELECT g FROM Game g WHERE activeGame = true AND playerId = :playerId ")
    Game findByPlayerIdAndIsActiveGame(@Param("playerId") Long playerId);

    List<Game> findBySessionId(String sessionId);

}
