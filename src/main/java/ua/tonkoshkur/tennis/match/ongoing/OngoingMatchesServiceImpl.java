package ua.tonkoshkur.tennis.match.ongoing;

import lombok.RequiredArgsConstructor;
import ua.tonkoshkur.tennis.common.exception.NotFoundException;
import ua.tonkoshkur.tennis.match.MatchDto;
import ua.tonkoshkur.tennis.match.score.MatchScoreCalculationService;
import ua.tonkoshkur.tennis.player.PlayerDto;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class OngoingMatchesServiceImpl implements OngoingMatchesService {

    private final MatchScoreCalculationService matchScoreCalculationService;

    private final Map<UUID, MatchDto> matchMap = new ConcurrentHashMap<>();

    @Override
    public MatchDto findByUuidOrThrow(UUID matchUuid) throws NotFoundException {
        return findByUuid(matchUuid)
                .orElseThrow(() -> new NotFoundException("Match not found"));
    }

    private Optional<MatchDto> findByUuid(UUID matchUuid) {
        return Optional.ofNullable(matchMap.get(matchUuid));
    }

    @Override
    public UUID createForPlayers(PlayerDto player1, PlayerDto player2) {
        UUID uuid = UUID.randomUUID();
        MatchDto match = new MatchDto(player1, player2);
        matchMap.put(uuid, match);
        return uuid;
    }

    @Override
    public MatchDto updateScore(UUID matchUuid, int winnerId) {
        MatchDto match = findByUuidOrThrow(matchUuid);

        matchScoreCalculationService.updateScore(match, winnerId);

        MatchDto updatedMatch = update(matchUuid, match);

        if (updatedMatch.isFinished()) {
            deleteByUuid(matchUuid);
            //TODO save match to db
        }

        return updatedMatch;
    }

    private MatchDto update(UUID matchUuid, MatchDto match) throws NotFoundException {
        matchMap.put(matchUuid, match);
        return match;
    }

    private void deleteByUuid(UUID matchUuid) {
        matchMap.remove(matchUuid);
    }
}
