package ua.tonkoshkur.tennis.match.finished;

import lombok.RequiredArgsConstructor;
import ua.tonkoshkur.tennis.common.pagination.Page;
import ua.tonkoshkur.tennis.match.Match;
import ua.tonkoshkur.tennis.match.MatchDao;
import ua.tonkoshkur.tennis.match.MatchDto;
import ua.tonkoshkur.tennis.match.MatchMapper;

@RequiredArgsConstructor
public class FinishedMatchesServiceImpl implements FinishedMatchesService {

    private final MatchDao matchDao;
    private final MatchMapper matchMapper;

    @Override
    public Page<MatchDto> findAllPageable(int page, int size, String playerName) {
        return matchDao.findAllPageable(page, size, playerName)
                .map(matchMapper::toDto);
    }

    @Override
    public MatchDto save(MatchDto match) {
        Match entity = matchMapper.toEntity(match);
        Match savedEntity = matchDao.save(entity);
        return matchMapper.toDto(savedEntity);
    }
}
