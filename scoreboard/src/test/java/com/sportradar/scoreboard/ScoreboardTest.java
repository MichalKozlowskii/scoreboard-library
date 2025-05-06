package com.sportradar.scoreboard;

import com.sportradar.scoreboard.service.Scoreboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreboardTest {
    Scoreboard scoreboard;
    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    void testStartMatch_Success() {
        assertDoesNotThrow(() -> scoreboard.startMatch("team1", "team2"));
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

        assertEquals("Two team names must not be equal.", exception.getMessage());
    }

    @Test
    void testStartMatch_MatchAlreadyStarted_ShouldThrow() {
        String team1 = "team1";
        String team2 = "team2";

        scoreboard.startMatch(team1, team2);

        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> scoreboard.startMatch(team1, team2));

        assertEquals("Match already started between those teams.", exception.getMessage());
    }

    @Test
    void testStartMatch_OneTeamAlreadyInMatch_ShouldThrow() {
        String activeTeam = "team1";
        String team2 = "team2";

        scoreboard.startMatch(activeTeam, "team3");

        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> scoreboard.startMatch(activeTeam, team2));

        assertEquals("One team is currently playing a match.", exception.getMessage());
    }

    @Test
    void testUpdateScore_Success() {
        String team1 = "team1";
        String team2 = "team2";

        scoreboard.startMatch(team1, team2);

        assertDoesNotThrow(() -> scoreboard.updateScore(team1, team2, 1, 0));
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

        assertEquals("Two team names must not be equal.", exception.getMessage());
    }

    @Test
    void testUpdateScore_MatchNotActive_ShouldThrow() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> scoreboard.updateScore("team1", "team2", 1, 0));

        assertEquals("Match between those teams is currently not in progress.", exception.getMessage());
    }

    @Test
    void testFinishMatch_Success() {
        scoreboard.startMatch("team1", "team2");

        assertDoesNotThrow(() -> scoreboard.finishMatch("team1", "team2"));
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

        assertEquals("Two team names must not be equal.", exception.getMessage());
    }

    @Test
    void testFinishMatch_MatchNotActive_ShouldThrow() {
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> scoreboard.finishMatch("team1", "team2"));

        assertEquals("Match between those teams is currently not in progress.", exception.getMessage());
    }

    @Test
    void testGetSummary() throws InterruptedException {
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.updateScore("Spain", "Brazil", 10, 2);

        Thread.sleep(100); // sleep so the LocalDateTime is different on next one, where total score is equal.

        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);

        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.updateScore("Mexico", "Canada", 0, 5);


        scoreboard.startMatch("Germany", "France");
        scoreboard.updateScore("Germany", "France", 2, 2);

        Thread.sleep(100);

        scoreboard.startMatch("Argentina", "Australia");
        scoreboard.updateScore("Argentina", "Australia", 3, 1);

        // check if match is finished properly.
        scoreboard.startMatch("Lithuania", "Latvia");
        scoreboard.finishMatch("Lithuania", "Latvia");

        List<String> summary = scoreboard.getSummary();

        assertNotNull(summary);

        assertEquals(5, summary.size());
        assertEquals("Uruguay 6 - Italy 6", summary.get(0));
        assertEquals("Spain 10 - Brazil 2", summary.get(1));
        assertEquals("Mexico 0 - Canada 5", summary.get(2));
        assertEquals("Argentina 3 - Australia 1", summary.get(3));
        assertEquals("Germany 2 - France 2", summary.get(4));
    }

    @Test
    void testGetSummary_Empty() {
        assertTrue(scoreboard.getSummary().isEmpty());
    }
}