package ua.tonkoshkur.tennis.match.newmatch;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.tonkoshkur.tennis.common.exception.BadRequestException;
import ua.tonkoshkur.tennis.match.ongoing.OngoingMatchesService;
import ua.tonkoshkur.tennis.player.PlayerDto;
import ua.tonkoshkur.tennis.player.PlayerService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchController extends HttpServlet {

    private static final String NEW_MATCH_PAGE = "jsp/new-match.jsp";

    private transient PlayerService playerService;
    private transient OngoingMatchesService ongoingMatchesService;
    private transient NewMatchRequestMapper newMatchRequestMapper;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext context = config.getServletContext();
        playerService = (PlayerService) context.getAttribute(PlayerService.class.getSimpleName());
        ongoingMatchesService = (OngoingMatchesService) context.getAttribute(OngoingMatchesService.class.getSimpleName());
        newMatchRequestMapper = new NewMatchRequestMapper();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(NEW_MATCH_PAGE)
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            NewMatchRequest newMatchRequest = newMatchRequestMapper.map(request);

            PlayerDto player1 = playerService.findByNameOrSave(newMatchRequest.player1());
            PlayerDto player2 = playerService.findByNameOrSave(newMatchRequest.player2());

            UUID uuid = ongoingMatchesService.createForPlayers(player1, player2);

            redirectToMatchScore(response, uuid);
        } catch (BadRequestException exception) {
            handleError(request, response, exception.getMessage());
        }
    }

    private void redirectToMatchScore(HttpServletResponse response, UUID uuid) throws IOException {
        response.sendRedirect("/match-score?uuid=" + uuid);
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute("error", message);
        request.getRequestDispatcher(NEW_MATCH_PAGE)
                .forward(request, response);
    }

}
