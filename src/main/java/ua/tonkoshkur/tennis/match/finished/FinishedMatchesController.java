package ua.tonkoshkur.tennis.match.finished;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/matches")
public class FinishedMatchesController extends HttpServlet {

    private static final String MATCHES_PAGE = "jsp/matches.jsp";

    private transient FinishedMatchesRequestMapper finishedMatchesRequestMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        finishedMatchesRequestMapper = new FinishedMatchesRequestMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FinishedMatchesRequest matchesRequest = finishedMatchesRequestMapper.map(request);

        //TODO find matches

        request.setAttribute("playerName", matchesRequest.playerName());

        request.getRequestDispatcher(MATCHES_PAGE)
                .forward(request, response);
    }
}
