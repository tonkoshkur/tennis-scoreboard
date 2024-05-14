package ua.tonkoshkur.tennis.match.newmatch;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import ua.tonkoshkur.tennis.common.exception.BadRequestException;
import ua.tonkoshkur.tennis.common.validator.RequestValidator;

import java.util.Optional;

@RequiredArgsConstructor
public class NewMatchRequestValidator implements RequestValidator {

    private final String playerNameRegex;
    private final String player1Param;
    private final String player2Param;

    @Override
    public void validate(HttpServletRequest request) throws BadRequestException {
        String player1 = getPlayer(request, player1Param)
                .orElseThrow(() -> new BadRequestException("First player name is not valid"));
        String player2 = getPlayer(request, player2Param)
                .orElseThrow(() -> new BadRequestException("Second player name is not valid"));

        if (player1.equals(player2)) {
            throw new BadRequestException("You must specify different player names");
        }
    }

    private Optional<String> getPlayer(HttpServletRequest request, String paramName) {
        String parameter = request.getParameter(paramName);
        return Optional.ofNullable(parameter)
                .filter(this::isPlayerNameValid);
    }

    private boolean isPlayerNameValid(String playerName) {
        return playerName.matches(playerNameRegex);
    }
}
