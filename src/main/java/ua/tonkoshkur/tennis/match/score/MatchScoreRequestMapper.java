package ua.tonkoshkur.tennis.match.score;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import ua.tonkoshkur.tennis.common.mapper.RequestMapper;

import java.util.UUID;

@RequiredArgsConstructor
public class MatchScoreRequestMapper implements RequestMapper<MatchScoreRequest> {

    private final String uuidParam;

    @Override
    public MatchScoreRequest map(HttpServletRequest request) {
        UUID uuid = mapParamOrGetNull(request, uuidParam, UUID::fromString);
        return new MatchScoreRequest(uuid);
    }
}
