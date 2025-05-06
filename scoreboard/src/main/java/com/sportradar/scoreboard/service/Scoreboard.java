package com.sportradar.scoreboard.service;

import com.sportradar.scoreboard.model.Match;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Scoreboard implements ScoreboardService {
    private final Set<String> activeTeams = ConcurrentHashMap.newKeySet(); // thread safe
    private final Map<String, Match> activeMatches = new ConcurrentHashMap<>(); // thread safe

    @Override
    public void startMatch(String homeTeam, String awayTeam) {
        validateTeams(homeTeam, awayTeam);

        String matchKey = generateKey(homeTeam, awayTeam);

        if (activeMatches.containsKey(matchKey)) {
            throw new IllegalArgumentException("Match already started between those teams.");
        }

        if (activeTeams.contains(homeTeam) || activeTeams.contains(awayTeam)) {
            throw new IllegalArgumentException("One team is currently playing a match.");
        }

        Match match = new Match(homeTeam, awayTeam);

        activeMatches.put(matchKey, match);
        activeTeams.add(homeTeam);
        activeTeams.add(awayTeam);
    }

    @Override
    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        validateTeams(homeTeam, awayTeam);

        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Invalid score argument");
        }

        String matchKey = generateKey(homeTeam, awayTeam);

        if (!activeMatches.containsKey(matchKey)) {
            throw new IllegalArgumentException("Match between those teams is currently not in progress.");
        }

        Match match = activeMatches.get(matchKey);

        match.setHomeScore(homeScore);
        match.setAwayScore(awayScore);
    }

    @Override
    public void finishMatch(String homeTeam, String awayTeam) {
        validateTeams(homeTeam, awayTeam);

        String matchKey = generateKey(homeTeam, awayTeam);

        if (!activeMatches.containsKey(matchKey)) {
            throw new IllegalArgumentException("Match between those teams is currently not in progress.");
        }

        activeMatches.remove(matchKey);
        activeTeams.remove(homeTeam);
        activeTeams.remove(awayTeam);
    }

    @Override
    public List<String> getSummary() {
        return activeMatches.values().stream()
                .sorted((match1, match2) -> {
                    int scoreCompare = Integer.compare(match2.getTotalScore(), match1.getTotalScore());

                    if (scoreCompare != 0) {
                        return scoreCompare;
                    } else {
                        return match2.getStartTime().compareTo(match1.getStartTime());
                    }
                })
                .map(Match::displayScore)
                .toList();
    }

    private String generateKey(String homeTeam, String awayTeam) {
        return String.format("%s - %s", homeTeam, awayTeam);
    }

    private void validateTeams(String homeTeam, String awayTeam) {
        if (homeTeam == null || awayTeam == null || homeTeam.isBlank() || awayTeam.isBlank()) {
            throw new IllegalArgumentException("Invalid team name.");
        }

        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Two team names must not be equal.");
        }
    }
}
