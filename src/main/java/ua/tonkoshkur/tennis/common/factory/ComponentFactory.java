package ua.tonkoshkur.tennis.common.factory;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import ua.tonkoshkur.tennis.match.Match;
import ua.tonkoshkur.tennis.match.MatchDao;
import ua.tonkoshkur.tennis.match.MatchDaoImpl;
import ua.tonkoshkur.tennis.match.MatchMapper;
import ua.tonkoshkur.tennis.match.finished.FinishedMatchesService;
import ua.tonkoshkur.tennis.match.finished.FinishedMatchesServiceImpl;
import ua.tonkoshkur.tennis.match.ongoing.OngoingMatchesService;
import ua.tonkoshkur.tennis.match.ongoing.OngoingMatchesServiceImpl;
import ua.tonkoshkur.tennis.match.score.MatchScoreCalculationService;
import ua.tonkoshkur.tennis.match.score.MatchScoreCalculationServiceImpl;
import ua.tonkoshkur.tennis.player.*;

@Getter
public final class ComponentFactory {

    private final SessionFactory sessionFactory;
    private final ModelMapper modelMapper;

    private final PlayerDao playerDao;
    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    private final MatchDao matchDao;
    private final MatchMapper matchMapper;
    private final FinishedMatchesService finishedMatchesService;
    private final MatchScoreCalculationService matchScoreCalculationService;
    private final OngoingMatchesService ongoingMatchesService;


    public ComponentFactory() {
        sessionFactory = createSessionFactory();
        modelMapper = new ModelMapper();

        playerDao = new PlayerDaoImpl(sessionFactory);
        playerMapper = new PlayerMapper(modelMapper);
        playerService = new PlayerServiceImpl(playerDao, playerMapper);

        matchDao = new MatchDaoImpl(sessionFactory);
        matchMapper = new MatchMapper(modelMapper);
        finishedMatchesService = new FinishedMatchesServiceImpl(matchDao, matchMapper);
        matchScoreCalculationService = new MatchScoreCalculationServiceImpl();
        ongoingMatchesService = new OngoingMatchesServiceImpl(matchScoreCalculationService, finishedMatchesService);
    }

    private SessionFactory createSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(Player.class)
                .addAnnotatedClass(Match.class)
                .buildSessionFactory();
    }
}
