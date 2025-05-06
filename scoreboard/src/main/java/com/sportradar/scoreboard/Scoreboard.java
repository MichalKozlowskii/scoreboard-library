package com.sportradar.scoreboard;

import com.sportradar.scoreboard.model.Match;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Scoreboard {
    private final Set<String> activeTeams = ConcurrentHashMap.newKeySet(); // thread safe
    private final Map<String, Match> activeMatches = new ConcurrentHashMap<>();

    public void startMatch(String homeTeam, String awayTeam) {

    }
}
