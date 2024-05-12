package ua.tonkoshkur.tennis.match.score;

import ua.tonkoshkur.tennis.match.MatchDto;
import ua.tonkoshkur.tennis.player.PlayerDto;

public class MatchScoreCalculationServiceImpl implements MatchScoreCalculationService {

    private static final int MIN_GAMES_TO_WIN_SET = 6;
    private static final int MAX_GAMES_TO_WIN_SET = 7;
    private static final int ADVANTAGE_TO_WIN_SET = 2;

    private static final int MAX_SETS_TO_WIN_MATCH = 3;
    private static final int ADVANTAGE_TO_WIN_MATCH = 2;

    @Override
    public void updateScore(MatchDto match, int winnerId) {
        MatchScoreDto winnerScore = getWinnerScore(match, winnerId);
        MatchScoreDto looserScore = getLooserScore(match, winnerId);

        updateScores(winnerScore, looserScore);

        if (isMatchOver(match)) {
            finishMatch(match, winnerId);
        }
    }

    private void updateScores(MatchScoreDto winnerScore, MatchScoreDto looserScore) {
        switch (winnerScore.getPoints()) {
            case 0, 15 -> addFifteenPoints(winnerScore);
            case 30 -> addTenPoints(winnerScore);
            case 40 -> handleFortyPoints(winnerScore, looserScore);
            default -> throw new IllegalMatchScoreException(winnerScore);
        }
    }

    private MatchScoreDto getWinnerScore(MatchDto match, int winnerId) {
        return match.getPlayer1().getId() == winnerId
                ? match.getPlayer1Score()
                : match.getPlayer2Score();
    }

    private MatchScoreDto getLooserScore(MatchDto match, int winnerId) {
        return match.getPlayer1().getId() == winnerId
                ? match.getPlayer2Score()
                : match.getPlayer1Score();
    }

    private void addFifteenPoints(MatchScoreDto score) {
        score.addPoints(15);
    }

    private void addTenPoints(MatchScoreDto score) {
        score.addPoints(10);
    }

    private void handleFortyPoints(MatchScoreDto winnerScore, MatchScoreDto looserScore) {
        if (looserScore.isAdvantage()) {
            looserScore.setAdvantage(false);
            return;
        }
        if (!winnerScore.isAdvantage() && looserScore.getPoints() == 40) {
            winnerScore.setAdvantage(true);
            return;
        }
        finishGame(winnerScore, looserScore);
    }

    private void finishGame(MatchScoreDto winnerScore, MatchScoreDto looserScore) {
        winnerScore.incrementGames();
        winnerScore.resetPoints();
        winnerScore.setAdvantage(false);

        looserScore.resetPoints();

        if (isSetOver(winnerScore.getGames(), looserScore.getGames())) {
            finishSet(winnerScore, looserScore);
        }
    }

    private boolean isSetOver(int winnerGames, int looserGames) {
        int advantageInGames = winnerGames - looserGames;
        return winnerGames == MAX_GAMES_TO_WIN_SET
                || (winnerGames == MIN_GAMES_TO_WIN_SET && advantageInGames >= ADVANTAGE_TO_WIN_SET);
    }

    private void finishSet(MatchScoreDto winnerScore, MatchScoreDto looserScore) {
        winnerScore.incrementSets();
        winnerScore.resetGames();
        looserScore.resetGames();
    }

    private boolean isMatchOver(MatchDto match) {
        int player1Sets = match.getPlayer1Score().getSets();
        int player2Sets = match.getPlayer2Score().getSets();
        return Math.abs(player1Sets - player2Sets) >= ADVANTAGE_TO_WIN_MATCH
                || player1Sets == MAX_SETS_TO_WIN_MATCH
                || player2Sets == MAX_SETS_TO_WIN_MATCH;
    }

    private void finishMatch(MatchDto match, int winnerId) {
        PlayerDto winner = match.getPlayer1().getId() == winnerId
                ? match.getPlayer1()
                : match.getPlayer2();

        match.setWinner(winner);
    }
}
