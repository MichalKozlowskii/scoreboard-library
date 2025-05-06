package com.sportradar.scoreboard;

import com.sportradar.scoreboard.model.Match;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Scoreboard implements ScoreboardService {
    private final Set<String> activeTeams = ConcurrentHashMap.newKeySet(); // thread safe
    private final Map<String, Match> activeMatches = new ConcurrentHashMap<>();

    @Override
    public void startMatch(String homeTeam, String awayTeam) {

    }
}
