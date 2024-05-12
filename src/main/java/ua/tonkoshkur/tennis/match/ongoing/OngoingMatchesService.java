package ua.tonkoshkur.tennis.match.ongoing;

import ua.tonkoshkur.tennis.common.exception.NotFoundException;
import ua.tonkoshkur.tennis.match.MatchDto;
import ua.tonkoshkur.tennis.player.PlayerDto;

import java.util.UUID;

public interface OngoingMatchesService {
    MatchDto findByUuidOrThrow(UUID matchUuid) throws NotFoundException;

    UUID createForPlayers(PlayerDto player1, PlayerDto player2);

    MatchDto updateScore(UUID matchUuid, int winnerId);
}
