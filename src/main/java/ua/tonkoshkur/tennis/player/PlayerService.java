package ua.tonkoshkur.tennis.player;

public interface PlayerService {
    PlayerDto createOrFindByName(String name);
}
