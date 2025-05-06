package com.sportradar.scoreboard;

import com.sportradar.scoreboard.model.Match;
import org.junit.jupiter.api.BeforeEach;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {
    Set<String> activeTeams;
    Map<String, Match> activeMatches;

    @BeforeEach
    void setUp() {
        activeMatches = new ConcurrentHashMap<>();
        activeTeams = ConcurrentHashMap.newKeySet();
    }
}