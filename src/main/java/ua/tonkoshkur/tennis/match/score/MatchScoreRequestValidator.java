package ua.tonkoshkur.tennis.match.score;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import ua.tonkoshkur.tennis.common.exception.BadRequestException;
import ua.tonkoshkur.tennis.common.validator.RequestValidator;

import java.util.UUID;

@RequiredArgsConstructor
public class MatchScoreRequestValidator implements RequestValidator {

    private final String uuidParam;

    @Override
    public void validate(HttpServletRequest request) throws BadRequestException {
        validateParam(request, uuidParam, UUID::fromString);
    }
}