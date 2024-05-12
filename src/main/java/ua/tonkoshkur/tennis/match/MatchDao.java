package ua.tonkoshkur.tennis.match;

import ua.tonkoshkur.tennis.common.pagination.Page;

public interface MatchDao {
    Page<Match> findAllPageable(int page, int pageSize, String playerName);

    Match save(Match match);
}
