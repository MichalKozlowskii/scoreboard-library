package com.sportradar.scoreboard.model;

import java.time.LocalDateTime;

public class Match {
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private final LocalDateTime startTime;

    public Match(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.startTime = LocalDateTime.now();
    }

    public void setHomeScore(int score) {
        this.homeScore = score;
    }

    public int getHomeScore() {
        return this.homeScore;
    }

    public void setAwayScore(int score) {
        this.awayScore = score;
    }

    public int getAwayScore() {
        return this.awayScore;
    }

    public String getHomeTeam() {
        return this.homeTeam;
    }

    public String getAwayTeam() {
        return this.awayTeam;
    }

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public String displayScore() {
        return String.format("%s %d - %s %d", homeTeam, homeScore, awayTeam, awayScore);
    }
}
