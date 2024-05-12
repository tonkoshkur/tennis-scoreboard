package ua.tonkoshkur.tennis.common.mapper;

import jakarta.servlet.http.HttpServletRequest;
import ua.tonkoshkur.tennis.common.exception.BadRequestException;

import java.util.Optional;
import java.util.function.Function;

public interface RequestMapper<T> {
    T map(HttpServletRequest request) throws BadRequestException;

    default <R> R mapParam(HttpServletRequest request, String name, Function<String, R> mapper)
            throws BadRequestException {

        String value = request.getParameter(name);
        if (value == null) {
            throw new BadRequestException(String.format("Missed %s value", name));
        }
        return mapParamValue(value, mapper)
                .orElseThrow(() -> new BadRequestException(String.format("Invalid %s value", name)));
    }

    default <R> R mapParamOrDefault(HttpServletRequest request, String name, Function<String, R> mapper, R defaultValue) {
        String value = request.getParameter(name);
        if (value == null) {
            return defaultValue;
        }
        return mapParamValue(value, mapper)
                .orElse(defaultValue);
    }

    private <R> Optional<R> mapParamValue(String value, Function<String, R> mapper) {
        try {
            R result = mapper.apply(value);
            return Optional.ofNullable(result);
        } catch (Exception exception) {
            return Optional.empty();
        }
    }
}
