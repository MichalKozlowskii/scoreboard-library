package com.sportradar.scoreboard.service;

import com.sportradar.scoreboard.model.Match;

import java.util.List;

public interface ScoreboardService {
    void startMatch(String homeTeam, String awayTeam);
    void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore);
    void finishMatch(String homeTeam, String awayTeam);
    List<String> getSummary();
}
