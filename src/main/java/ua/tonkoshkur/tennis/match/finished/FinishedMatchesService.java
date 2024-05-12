package ua.tonkoshkur.tennis.match.finished;

import ua.tonkoshkur.tennis.match.MatchDto;

public interface FinishedMatchesService {
    MatchDto save(MatchDto match);
}
