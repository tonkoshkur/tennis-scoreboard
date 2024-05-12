package ua.tonkoshkur.tennis.match.score;

import lombok.Data;

@Data
public class MatchScoreDto {

    private int points;
    private int games;
    private int sets;
    private boolean advantage;

    public void addPoints(int points) {
        this.points += points;
    }

    public void resetPoints() {
        points = 0;
    }

    public void incrementGames() {
        games++;
    }

    public void resetGames() {
        games = 0;
    }

    public void incrementSets() {
        sets++;
    }

}
