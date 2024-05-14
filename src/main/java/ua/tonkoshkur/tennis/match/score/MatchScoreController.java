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
import ua.tonkoshkur.tennis.match.ongoing.UpdateScoreRequestValidator;

import java.io.IOException;

@WebServlet("/match-score")
public class MatchScoreController extends HttpServlet {

    private static final String MATCH_SCORE_PAGE = "page/match-score.jsp";
    private static final String UUID_PARAM = "uuid";
    private static final String WINNER_ID_PARAM = "winnerId";

    private transient OngoingMatchesService ongoingMatchesService;
    private transient MatchScoreRequestMapper matchScoreRequestMapper;
    private transient MatchScoreRequestValidator matchScoreRequestValidator;
    private transient UpdateScoreRequestMapper updateScoreRequestMapper;
    private transient UpdateScoreRequestValidator updateScoreRequestValidator;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        ongoingMatchesService = (OngoingMatchesService) context.getAttribute(OngoingMatchesService.class.getSimpleName());
        matchScoreRequestMapper = new MatchScoreRequestMapper(UUID_PARAM);
        matchScoreRequestValidator = new MatchScoreRequestValidator(UUID_PARAM);
        updateScoreRequestMapper = new UpdateScoreRequestMapper(UUID_PARAM, WINNER_ID_PARAM);
        updateScoreRequestValidator = new UpdateScoreRequestValidator(UUID_PARAM, WINNER_ID_PARAM);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        matchScoreRequestValidator.validate(request);
        MatchScoreRequest scoreRequest = matchScoreRequestMapper.map(request);

        MatchDto match = ongoingMatchesService.findByUuidOrThrow(scoreRequest.uuid());

        request.setAttribute("match", match);
        request.getRequestDispatcher(MATCH_SCORE_PAGE)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        updateScoreRequestValidator.validate(request);
        UpdateScoreRequest updateScoreRequest = updateScoreRequestMapper.map(request);

        MatchDto match = ongoingMatchesService.updateScore(updateScoreRequest.uuid(), updateScoreRequest.winnerId());

        request.setAttribute("match", match);
        request.getRequestDispatcher(MATCH_SCORE_PAGE)
                .forward(request, response);
    }
}