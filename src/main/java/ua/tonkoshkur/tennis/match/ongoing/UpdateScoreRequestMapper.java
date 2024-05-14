package ua.tonkoshkur.tennis.match.ongoing;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import ua.tonkoshkur.tennis.common.mapper.RequestMapper;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateScoreRequestMapper implements RequestMapper<UpdateScoreRequest> {

    private final String uuidParam;
    private final String winnerIdParam;

    @Override
    public UpdateScoreRequest map(HttpServletRequest request) {
        UUID uuid = getUuid(request);
        int winnerId = getWinnerId(request);
        return new UpdateScoreRequest(uuid, winnerId);
    }

    private UUID getUuid(HttpServletRequest request) {
        return mapParamOrGetNull(request, uuidParam, UUID::fromString);
    }

    private int getWinnerId(HttpServletRequest request) {
        return mapParamOrGetNull(request, winnerIdParam, Integer::parseInt);
    }

}
