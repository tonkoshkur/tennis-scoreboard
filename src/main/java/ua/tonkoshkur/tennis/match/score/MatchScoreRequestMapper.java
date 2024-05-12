package ua.tonkoshkur.tennis.match.score;

import jakarta.servlet.http.HttpServletRequest;
import ua.tonkoshkur.tennis.common.exception.BadRequestException;
import ua.tonkoshkur.tennis.common.mapper.RequestMapper;

import java.util.UUID;

public class MatchScoreRequestMapper implements RequestMapper<MatchScoreRequest> {

    private static final String UUID_PARAM = "uuid";

    @Override
    public MatchScoreRequest map(HttpServletRequest request) throws BadRequestException {
        UUID uuid = mapParam(request, UUID_PARAM, UUID::fromString);
        return new MatchScoreRequest(uuid);
    }
}
