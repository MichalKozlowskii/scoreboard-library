package com.sportradar.scoreboard;

import com.sportradar.scoreboard.service.Scoreboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {
    Scoreboard scoreboard;
    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    void testStartMatch_Success() {
        scoreboard.startMatch("team1", "team2");
        // if no exception is thrown - success
    }

    @Test
    void testStartMatch_ArgumentsNotValid_ShouldThrow() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, ()
                        -> scoreboard.startMatch(null, null)),
                () -> assertThrows(IllegalArgumentException.class, ()
                        -> scoreboard.startMatch("", "")),
                () -> assertThrows(IllegalArgumentException.class, ()
                        -> scoreboard.startMatch("team1", null)),
                () -> assertThrows(IllegalArgumentException.class, ()
                        -> scoreboard.startMatch(null, "team1")),
                () -> assertThrows(IllegalArgumentException.class, ()
                        -> scoreboard.startMatch("", "team2")),
                () -> assertThrows(IllegalArgumentException.class, ()
                        -> scoreboard.startMatch("team2", ""))
        );
    }

    @Test
    void testStartMatch_MatchAlreadyStarted_ShouldThrow() {
        String team1 = "team1";
        String team2 = "team2";

        scoreboard.startMatch(team1, team2);

        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> scoreboard.startMatch(team1, team2));

        assertEquals(exception.getMessage(), "Match already started between those teams.");
    }

    @Test
    void testStartMatch_OneTeamAlreadyInMatch_ShouldThrow() {
        String activeTeam = "team1";
        String team2 = "team2";

        scoreboard.startMatch(activeTeam, "team3");

        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> scoreboard.startMatch(activeTeam, team2));

        assertEquals(exception.getMessage(), "One team is currently playing a match.");
    }
}