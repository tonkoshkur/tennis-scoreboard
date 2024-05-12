package ua.tonkoshkur.tennis.match.score;

import ua.tonkoshkur.tennis.match.MatchDto;

public interface MatchScoreCalculationService {
    void updateScore(MatchDto match, int winnerId);
}
