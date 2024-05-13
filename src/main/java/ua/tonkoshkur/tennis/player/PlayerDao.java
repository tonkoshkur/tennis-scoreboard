package ua.tonkoshkur.tennis.player;

public interface PlayerDao {
    Player saveOrFindByName(Player player);
}
