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
        String expectedMessage = "Invalid team name.";

        assertAll(
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () ->
                            scoreboard.startMatch(null, null));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () ->
                            scoreboard.startMatch("", ""));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () ->
                            scoreboard.startMatch("team1", null));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () ->
                            scoreboard.startMatch(null, "team1"));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () ->
                            scoreboard.startMatch("", "team2"));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () ->
                            scoreboard.startMatch("team2", ""));
                    assertEquals(expectedMessage, exception.getMessage());
                }
        );
    }

    @Test
    void testStartMatch_ArgumentsNotValid_SameTeamPassedTwice_ShouldThrow() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> scoreboard.startMatch("team1", "team1"));

        assertEquals(exception.getMessage(), "Two team names must not be equal.");
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

    @Test
    void testUpdateScore_Success() {
        String team1 = "team1";
        String team2 = "team2";

        scoreboard.startMatch(team1, team2);

        scoreboard.updateScore(team1, team2, 1, 0);
        // no exception thrown - success
    }

    @Test
    void tesUpdateScore_InvalidScoreArgument_ShouldThrow() {
        String expectedMessage = "Invalid score argument";

        assertAll(
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> scoreboard.updateScore("team1", "team2", -1, -1));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> scoreboard.updateScore("team1", "team2", -1, 0));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> scoreboard.updateScore("team1", "team2", 0, -1));
                    assertEquals(expectedMessage, exception.getMessage());
                }
        );
    }

    @Test
    void testScore_InvalidTeamArgument_ShouldThrow() {
        String expectedMessage = "Invalid team name.";

        assertAll(
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> scoreboard.updateScore(null, null, 0, 0));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> scoreboard.updateScore(" ", "", 0, 0));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> scoreboard.updateScore("team1", "", 0, 0));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> scoreboard.updateScore("team1", null, 0, 0));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> scoreboard.updateScore("", "team1", 0, 0));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, ()
                            -> scoreboard.updateScore(null, "team1", 0, 0));
                    assertEquals(expectedMessage, exception.getMessage());
                }
        );
    }

    @Test
    void testUpdateScore_InvalidTeamArgument_SameTeamPassedTwice_ShouldThrow() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> scoreboard.updateScore("team1", "team1", 0, 0));

        assertEquals(exception.getMessage(), "Two team names must not be equal.");
    }

    @Test
    void testUpdateScore_MatchNotActive_ShouldThrow() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> scoreboard.updateScore("team1", "team2", 1, 0));

        assertEquals(exception.getMessage(), "Match between those teams is currently not in progress.");
    }

    @Test
    void testFinishMatch_Success() {
        scoreboard.startMatch("team1", "team2");

        scoreboard.finishMatch("team1", "team2");

        // no exception thrown - success
    }

    @Test
    void testFinishMatch_ArgumentsNotValid_ShouldThrow() {
        String expectedMessage = "Invalid team name.";

        assertAll(
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () ->
                            scoreboard.finishMatch(null, null));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () ->
                            scoreboard.finishMatch(" ", ""));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () ->
                            scoreboard.finishMatch("team1", null));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () ->
                            scoreboard.finishMatch(null, "team1"));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () ->
                            scoreboard.finishMatch("", "team2"));
                    assertEquals(expectedMessage, exception.getMessage());
                },
                () -> {
                    Exception exception = assertThrows(IllegalArgumentException.class, () ->
                            scoreboard.finishMatch("team2", ""));
                    assertEquals(expectedMessage, exception.getMessage());
                }
        );
    }

    @Test
    void testFinishMatch_ArgumentsNotValid_SameTeamPassedTwice_ShouldThrow() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> scoreboard.finishMatch("team1", "team1"));

        assertEquals(exception.getMessage(), "Two team names must not be equal.");
    }

    @Test
    void testFinishMatch_MatchNotActive_ShouldThrow() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> scoreboard.finishMatch("team1", "team2"));

        assertEquals(exception.getMessage(), "Match between those teams is currently not in progress.");
    }
}