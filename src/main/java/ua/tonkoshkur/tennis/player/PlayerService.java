package ua.tonkoshkur.tennis.player;

public interface PlayerService {
    PlayerDto findByNameOrSave(String name);
}
