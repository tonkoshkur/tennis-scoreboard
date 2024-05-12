package ua.tonkoshkur.tennis.match.ongoing;

import jakarta.servlet.http.HttpServletRequest;
import ua.tonkoshkur.tennis.common.exception.BadRequestException;
import ua.tonkoshkur.tennis.common.mapper.RequestMapper;

import java.util.UUID;

public class UpdateScoreRequestMapper implements RequestMapper<UpdateScoreRequest> {

    private static final String UUID_PARAM = "uuid";
    private static final String WINNER_ID_PARAM = "winnerId";

    @Override
    public UpdateScoreRequest map(HttpServletRequest request) throws BadRequestException {
        UUID uuid = getUuid(request);
        int winnerId = getWinnerId(request);
        return new UpdateScoreRequest(uuid, winnerId);
    }

    private UUID getUuid(HttpServletRequest request) throws BadRequestException {
        return mapParam(request, UUID_PARAM, UUID::fromString);
    }

    private int getWinnerId(HttpServletRequest request) throws BadRequestException {
        return mapParam(request, WINNER_ID_PARAM, Integer::parseInt);
    }

}
