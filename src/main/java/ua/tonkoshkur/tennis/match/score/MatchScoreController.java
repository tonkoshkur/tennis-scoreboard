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
import ua.tonkoshkur.tennis.match.ongoing.UpdateScoreRequest;
import ua.tonkoshkur.tennis.match.ongoing.UpdateScoreRequestMapper;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    private static final String MATCH_SCORE_PAGE = "page/match-score.jsp";

    private transient OngoingMatchesService ongoingMatchesService;
    private transient MatchScoreRequestMapper matchScoreRequestMapper;
    private transient UpdateScoreRequestMapper updateScoreRequestMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ongoingMatchesService = (OngoingMatchesService) context.getAttribute(OngoingMatchesService.class.getSimpleName());
        matchScoreRequestMapper = new MatchScoreRequestMapper();
        updateScoreRequestMapper = new UpdateScoreRequestMapper();
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UpdateScoreRequest updateScoreRequest = updateScoreRequestMapper.map(request);

        MatchDto match = ongoingMatchesService.updateScore(updateScoreRequest.uuid(), updateScoreRequest.winnerId());

        request.setAttribute("match", match);

        request.getRequestDispatcher(MATCH_SCORE_PAGE)
                .forward(request, response);
    }
}