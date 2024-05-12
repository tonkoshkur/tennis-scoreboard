package ua.tonkoshkur.tennis.match;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.tonkoshkur.tennis.common.dao.BaseDao;

@RequiredArgsConstructor
public class MatchDaoImpl extends BaseDao implements MatchDao {

    private final SessionFactory sessionFactory;

    @Override
    public Match save(Match match) {
        try (Session session = sessionFactory.openSession()) {
            executeTransactional(session, () -> session.persist(match));
            return match;
        }
    }
}
