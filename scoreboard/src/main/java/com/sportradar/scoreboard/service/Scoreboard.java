package com.sportradar.scoreboard.service;

import com.sportradar.scoreboard.model.Match;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Scoreboard implements ScoreboardService {
    private final Set<String> activeTeams = ConcurrentHashMap.newKeySet(); // thread safe
    private final Map<String, Match> activeMatches = new ConcurrentHashMap<>(); // thread safe

    @Override
    public void startMatch(String homeTeam, String awayTeam) {
        if (homeTeam == null || awayTeam == null || homeTeam.isBlank() || awayTeam.isBlank()) {
            throw new IllegalArgumentException();
        }

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

    private String generateKey(String homeTeam, String awayTeam) {
        return String.format("%s - %s", homeTeam, awayTeam);
    }
}
