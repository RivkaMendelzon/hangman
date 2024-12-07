package com.hangman.core.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.hangman.core.entities.Player;


public interface PlayerRepository extends CrudRepository<Player, Long> {


    @Query("SELECT p FROM Player p WHERE p.playerEmail = :playerEmail ")
    Player findByPlayerEmail(@Param("playerEmail") String playerEmail);
    

}
