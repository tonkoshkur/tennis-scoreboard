package ua.tonkoshkur.tennis.common.mapper;

import jakarta.servlet.http.HttpServletRequest;
import ua.tonkoshkur.tennis.common.exception.BadRequestException;

public interface RequestMapper<T> {
    T map(HttpServletRequest request) throws BadRequestException;
}
