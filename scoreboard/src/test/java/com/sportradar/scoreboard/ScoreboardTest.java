package com.sportradar.scoreboard;

import com.sportradar.scoreboard.model.Match;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {
    Scoreboard scoreboard;
    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    void testStartMatch_Success() {

    }
}