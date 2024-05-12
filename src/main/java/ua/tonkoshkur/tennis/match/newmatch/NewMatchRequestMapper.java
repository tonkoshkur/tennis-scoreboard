package ua.tonkoshkur.tennis.match.newmatch;

import jakarta.servlet.http.HttpServletRequest;
import ua.tonkoshkur.tennis.common.exception.BadRequestException;
import ua.tonkoshkur.tennis.common.mapper.RequestMapper;

import java.util.Optional;

public class NewMatchRequestMapper implements RequestMapper<NewMatchRequest> {

    private static final String PLAYER_NAME_REGEX = "^[a-zA-Z0-9]*\\s?[a-zA-Z0-9]+$";
    private static final String PLAYER_1_PARAM = "player1";
    private static final String PLAYER_2_PARAM = "player2";

    @Override
    public NewMatchRequest map(HttpServletRequest request) throws BadRequestException {
        String player1 = getPlayer(request, PLAYER_1_PARAM)
                .orElseThrow(() -> new BadRequestException("First player name is not valid"));
        String player2 = getPlayer(request, PLAYER_2_PARAM)
                .orElseThrow(() -> new BadRequestException("Second player name is not valid"));

        if (player1.equals(player2)) {
            throw new BadRequestException("You must specify different player names");
        }

        return new NewMatchRequest(player1, player2);
    }

    private Optional<String> getPlayer(HttpServletRequest request, String paramName) {
        String parameter = request.getParameter(paramName);
        return Optional.ofNullable(parameter)
                .filter(this::isPlayerNameValid);
    }

    private boolean isPlayerNameValid(String playerName) {
        return playerName.matches(PLAYER_NAME_REGEX);
    }

}
