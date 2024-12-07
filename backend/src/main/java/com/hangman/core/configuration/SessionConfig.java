package com.hangman.core.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@Configuration
@EnableJdbcHttpSession
public class SessionConfig {
    @Bean
    public DataSource dataSource() {
        return new org.springframework.jdbc.datasource.DriverManagerDataSource(
                "jdbc:postgresql://localhost:8080/hangman_game",
                "postgres",
                "1234"
        );
    }
}

