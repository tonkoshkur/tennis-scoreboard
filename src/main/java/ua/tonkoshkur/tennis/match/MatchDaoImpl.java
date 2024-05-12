package ua.tonkoshkur.tennis.match;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.tonkoshkur.tennis.common.dao.BaseDao;
import ua.tonkoshkur.tennis.common.pagination.Page;

import java.util.List;

@RequiredArgsConstructor
public class MatchDaoImpl extends BaseDao implements MatchDao {

    private static final String PLAYER_NAME_FILTER_QUERY = "where lower(player1.name) like lower(:playerName) " +
            "or lower(player2.name) like lower(:playerName) " +
            "or lower(winner.name) like lower(:playerName)";

    private final SessionFactory sessionFactory;

    @Override
    public Page<Match> findAllPageable(int page, int size, String playerName) {
        String playerNameParam = "%" + (playerName == null ? "" : playerName) + "%";
        try (Session session = sessionFactory.openSession()) {
            List<Match> matches = findAllPageable(session, page, size, playerNameParam);
            Long totalElements = findAllCount(session, playerNameParam);
            int totalPages = (int) Math.ceil((double) totalElements / size);
            return new Page<>(page, totalPages, matches);
        }
    }

    private List<Match> findAllPageable(Session session, int page, int size, String playerName) {
        String sql = "from Match m " +
                "join fetch m.player1 player1 " +
                "join fetch m.player2 player2 " +
                "join fetch m.winner winner " +
                PLAYER_NAME_FILTER_QUERY;
        return session.createSelectionQuery(sql, Match.class)
                .setParameter("playerName", playerName)
                .setPage(org.hibernate.query.Page.page(size, page))
                .list();
    }

    private Long findAllCount(Session session, String playerName) {
        String sql = "select count(*) from Match " + PLAYER_NAME_FILTER_QUERY;
        return session.createSelectionQuery(sql, Long.class)
                .setParameter("playerName", playerName)
                .uniqueResult();
    }

    @Override
    public Match save(Match match) {
        try (Session session = sessionFactory.openSession()) {
            executeTransactional(session, () -> session.persist(match));
            return match;
        }
    }
}
