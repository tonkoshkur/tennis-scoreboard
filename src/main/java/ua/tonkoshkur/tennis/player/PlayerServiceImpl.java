package ua.tonkoshkur.tennis.player;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDao playerDao;
    private final PlayerMapper playerMapper;

    @Override
    public PlayerDto createOrFindByName(String name) {
        Player player = playerDao.saveOrFindByName(new Player(name));
        return playerMapper.toDto(player);
    }
}
