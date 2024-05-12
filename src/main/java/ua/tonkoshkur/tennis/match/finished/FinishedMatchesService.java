package ua.tonkoshkur.tennis.match.finished;

import ua.tonkoshkur.tennis.common.pagination.Page;
import ua.tonkoshkur.tennis.match.MatchDto;

public interface FinishedMatchesService {
    Page<MatchDto> findAllPageable(int page, int size, String playerName);

    MatchDto save(MatchDto match);
}
