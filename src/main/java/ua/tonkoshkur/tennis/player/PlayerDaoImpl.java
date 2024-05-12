package ua.tonkoshkur.tennis.player;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.tonkoshkur.tennis.common.dao.BaseDao;

import java.util.Optional;

@RequiredArgsConstructor
public class PlayerDaoImpl extends BaseDao implements PlayerDao {

    private final SessionFactory sessionFactory;

    @Override
    public Optional<Player> findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            String sql = "from Player p where p.name = :name";

            return session.createSelectionQuery(sql, Player.class)
                    .setParameter("name", name)
                    .uniqueResultOptional();
        }
    }

    @Override
    public Player save(Player player) {
        try (Session session = sessionFactory.openSession()) {
            executeTransactional(session, () -> session.persist(player));
            return player;
        }
    }
}
