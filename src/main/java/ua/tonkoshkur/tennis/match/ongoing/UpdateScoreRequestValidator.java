package ua.tonkoshkur.tennis.match.ongoing;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import ua.tonkoshkur.tennis.common.exception.BadRequestException;
import ua.tonkoshkur.tennis.common.validator.RequestValidator;

import java.util.UUID;

@RequiredArgsConstructor
public class UpdateScoreRequestValidator implements RequestValidator {

    private final String uuidParam;
    private final String winnerIdParam;

    @Override
    public void validate(HttpServletRequest request) throws BadRequestException {
        validateUuid(request);
        validateWinnerId(request);
    }

    private void validateUuid(HttpServletRequest request) throws BadRequestException {
        validateParam(request, uuidParam, UUID::fromString);
    }

    private void validateWinnerId(HttpServletRequest request) throws BadRequestException {
        validateParam(request, winnerIdParam, Integer::parseInt);
    }

}
