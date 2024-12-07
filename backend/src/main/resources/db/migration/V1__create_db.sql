

CREATE TABLE Players (
    player_id SERIAL PRIMARY KEY,
    player_name VARCHAR(255) NOT NULL,
    player_email VARCHAR(255) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Games (
    game_id SERIAL PRIMARY KEY,
    word_to_guess VARCHAR(255) NOT NULL,
    guessed_word VARCHAR(255), 
    incorrect_guesses VARCHAR(255),
    active_game boolean DEFAULT false,
    player_id BIGINT,
    session_id VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (player_id) REFERENCES Players(player_id)

);