package ua.tonkoshkur.tennis.common.factory;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.modelmapper.ModelMapper;
import ua.tonkoshkur.tennis.match.Match;
import ua.tonkoshkur.tennis.match.ongoing.OngoingMatchesService;
import ua.tonkoshkur.tennis.match.ongoing.OngoingMatchesServiceImpl;
import ua.tonkoshkur.tennis.player.*;

@Getter
public final class ComponentFactory {

    private final SessionFactory sessionFactory;
    private final ModelMapper modelMapper;

    private final PlayerDao playerDao;
    private final PlayerMapper playerMapper;
    private final PlayerService playerService;

    private final OngoingMatchesService ongoingMatchesService;


    public ComponentFactory() {
        sessionFactory = createSessionFactory();
        modelMapper = new ModelMapper();

        playerDao = new PlayerDaoImpl(sessionFactory);
        playerMapper = new PlayerMapper(modelMapper);
        playerService = new PlayerServiceImpl(playerDao, playerMapper);

        ongoingMatchesService = new OngoingMatchesServiceImpl();
    }

    private SessionFactory createSessionFactory() {
        return new Configuration()
                .addAnnotatedClass(Player.class)
                .addAnnotatedClass(Match.class)
                .buildSessionFactory();
    }
}
