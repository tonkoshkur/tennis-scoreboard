package ua.tonkoshkur.tennis.match.finished;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.tonkoshkur.tennis.common.pagination.Page;
import ua.tonkoshkur.tennis.match.MatchDto;

import java.io.IOException;

@WebServlet("/matches")
public class FinishedMatchesController extends HttpServlet {

    private static final String MATCHES_PAGE = "page/matches.jsp";

    private transient FinishedMatchesService finishedMatchesService;
    private transient FinishedMatchesRequestMapper finishedMatchesRequestMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        finishedMatchesService = (FinishedMatchesService) context.getAttribute(FinishedMatchesService.class.getSimpleName());
        finishedMatchesRequestMapper = new FinishedMatchesRequestMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FinishedMatchesRequest matchesRequest = finishedMatchesRequestMapper.map(request);

        Page<MatchDto> matchesPage = finishedMatchesService.findAllPageable(matchesRequest.page(), matchesRequest.size(),
                matchesRequest.playerName());

        request.setAttribute("matchesPage", matchesPage);
        request.setAttribute("playerName", matchesRequest.playerName());

        request.getRequestDispatcher(MATCHES_PAGE)
                .forward(request, response);
    }
}
