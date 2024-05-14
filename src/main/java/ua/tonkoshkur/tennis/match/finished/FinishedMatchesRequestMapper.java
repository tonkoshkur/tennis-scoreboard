package ua.tonkoshkur.tennis.match.finished;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import ua.tonkoshkur.tennis.common.mapper.RequestMapper;

@RequiredArgsConstructor
public class FinishedMatchesRequestMapper implements RequestMapper<FinishedMatchesRequest> {

    private static final String PAGE_PARAM = "page";
    private static final String PLAYER_NAME_PARAM = "filter_by_player_name";

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 10;

    @Override
    public FinishedMatchesRequest map(HttpServletRequest request) {
        int page = mapParamOrDefault(request, PAGE_PARAM, Integer::parseInt, DEFAULT_PAGE);
        String playerName = request.getParameter(PLAYER_NAME_PARAM);
        return new FinishedMatchesRequest(page, DEFAULT_SIZE, playerName);
    }
}
