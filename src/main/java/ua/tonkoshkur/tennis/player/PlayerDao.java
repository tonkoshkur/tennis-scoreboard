package ua.tonkoshkur.tennis.player;

import java.util.Optional;

public interface PlayerDao {
    Optional<Player> findByName(String name);

    Player save(Player player);
}
