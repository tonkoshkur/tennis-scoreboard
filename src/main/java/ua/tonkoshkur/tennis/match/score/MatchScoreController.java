package ua.tonkoshkur.tennis.match.score;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.tonkoshkur.tennis.match.MatchDto;
import ua.tonkoshkur.tennis.match.ongoing.OngoingMatchesService;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    private static final String MATCH_SCORE_PAGE = "jsp/match-score.jsp";

    private transient MatchScoreRequestMapper matchScoreRequestMapper;
    private transient OngoingMatchesService ongoingMatchesService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ongoingMatchesService = (OngoingMatchesService) context.getAttribute(OngoingMatchesService.class.getSimpleName());
        matchScoreRequestMapper = new MatchScoreRequestMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MatchScoreRequest scoreRequest = matchScoreRequestMapper.map(request);

        MatchDto match = ongoingMatchesService.findByUuidOrThrow(scoreRequest.uuid());

        request.setAttribute("match", match);

        request.getRequestDispatcher(MATCH_SCORE_PAGE)
                .forward(request, response);
    }
}