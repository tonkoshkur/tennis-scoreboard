package ua.tonkoshkur.tennis.common.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebFilter("/*")
public class ExceptionHandlingFilter implements Filter {

    private static final Logger LOGGER = Logger.getLogger(ExceptionHandlingFilter.class.getName());
    private static final String ERROR_PAGE = "jsp/error.jsp";
    private static final String UNKNOWN_ERROR_MESSAGE = "Oops.. Error occurred. Details: ";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            handleException((HttpServletRequest) request, (HttpServletResponse) response, e);
        }
    }

    private void handleException(HttpServletRequest request, HttpServletResponse response, Exception exception)
            throws IOException, ServletException {
        LOGGER.log(Level.SEVERE, exception.getMessage(), exception);
        redirectToErrorPage(request, response, exception);
    }

    private void redirectToErrorPage(HttpServletRequest request, HttpServletResponse response, Exception exception)
            throws IOException, ServletException {
        request.setAttribute("error", UNKNOWN_ERROR_MESSAGE + exception.getMessage());
        request.getRequestDispatcher(ERROR_PAGE)
                .forward(request, response);
    }
}
