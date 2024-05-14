package ua.tonkoshkur.tennis.match.newmatch;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import ua.tonkoshkur.tennis.common.mapper.RequestMapper;

@RequiredArgsConstructor
public class NewMatchRequestMapper implements RequestMapper<NewMatchRequest> {

    private final String player1Param;
    private final String player2Param;

    @Override
    public NewMatchRequest map(HttpServletRequest request) {
        String player1 = request.getParameter(player1Param);
        String player2 = request.getParameter(player2Param);

        return new NewMatchRequest(player1, player2);
    }
}
