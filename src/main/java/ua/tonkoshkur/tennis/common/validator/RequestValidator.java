package ua.tonkoshkur.tennis.common.validator;

import jakarta.servlet.http.HttpServletRequest;
import ua.tonkoshkur.tennis.common.exception.BadRequestException;
import ua.tonkoshkur.tennis.common.exception.InvalidParamValueException;
import ua.tonkoshkur.tennis.common.exception.MissedParamValueException;

import java.util.function.Function;

public interface RequestValidator {
    void validate(HttpServletRequest request) throws BadRequestException;

    default <R> void validateParam(HttpServletRequest request, String paramName, Function<String, R> mapper)
            throws BadRequestException {
        throwIfMissedParam(request, paramName);
        throwIfInvalidParam(request, paramName, mapper);
    }

    private void throwIfMissedParam(HttpServletRequest request, String paramName) throws MissedParamValueException {
        if (request.getParameter(paramName) == null) {
            throw new MissedParamValueException(paramName);
        }
    }

    private <R> void throwIfInvalidParam(HttpServletRequest request, String paramName, Function<String, R> mapper)
            throws InvalidParamValueException {

        String value = request.getParameter(paramName);
        try {
            mapper.apply(value);
        } catch (Exception e) {
            throw new InvalidParamValueException(paramName);
        }
    }
}
