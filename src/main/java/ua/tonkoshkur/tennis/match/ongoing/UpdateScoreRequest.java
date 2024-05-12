package ua.tonkoshkur.tennis.match.ongoing;

import java.util.UUID;

public record UpdateScoreRequest(UUID uuid, int winnerId) {
}
