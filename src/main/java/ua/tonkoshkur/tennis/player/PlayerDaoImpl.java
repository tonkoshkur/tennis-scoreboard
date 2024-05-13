package ua.tonkoshkur.tennis.player;

import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ua.tonkoshkur.tennis.common.dao.BaseDao;

import java.util.Optional;

@RequiredArgsConstructor
public class PlayerDaoImpl extends BaseDao implements PlayerDao {

    private final SessionFactory sessionFactory;

    @Override
    public Player saveOrFindByName(Player player) {
        try (Session session = sessionFactory.openSession()) {
            try {
                return save(session, player);
            } catch (HibernateException e) {
                return findByNameOrGetNull(session, player.getName());
            }
        }
    }

    private Player save(Session session, Player player) {
        String upperCaseName = player.getName().toUpperCase();
        player.setName(upperCaseName);

        executeTransactional(session, () -> session.persist(player));
        return player;
    }

    private Player findByNameOrGetNull(Session session, String name) {
        return findByName(session, name)
                .orElse(null);
    }

    private Optional<Player> findByName(Session session, String name) {
        String sql = "from Player p where upper(p.name) = upper(:name)";

        return session.createSelectionQuery(sql, Player.class)
                .setParameter("name", name)
                .uniqueResultOptional();
    }
}
