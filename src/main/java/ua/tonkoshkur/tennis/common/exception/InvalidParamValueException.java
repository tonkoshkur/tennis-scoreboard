package ua.tonkoshkur.tennis.common.exception;

public class InvalidParamValueException extends BadRequestException {
    public InvalidParamValueException(String paramName) {
        super(String.format("Invalid %s value", paramName));
    }
}
