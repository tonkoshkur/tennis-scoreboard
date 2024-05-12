package ua.tonkoshkur.tennis.match.score;

public class IllegalMatchScoreException extends RuntimeException {
    public IllegalMatchScoreException(MatchScoreDto score) {
        super(String.format("Illegal match score. Sets: %s, games: %s, points: %s.",
                score.getSets(), score.getGames(), score.getPoints()));
    }
}
