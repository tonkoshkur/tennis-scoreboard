package ua.tonkoshkur.tennis.common.exception;

public class MissedParamValueException extends BadRequestException {
    public MissedParamValueException(String paramName) {
        super(String.format("Missed %s value", paramName));
    }
}
