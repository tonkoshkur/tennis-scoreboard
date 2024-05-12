package ua.tonkoshkur.tennis.player;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerDao playerDao;
    private final PlayerMapper playerMapper;

    @Override
    public PlayerDto findByNameOrSave(String name) {
        return findByName(name)
                .orElseGet(() -> save(new PlayerDto(name)));
    }

    private Optional<PlayerDto> findByName(String name) {
        return playerDao.findByName(name)
                .map(playerMapper::toDto);
    }

    private PlayerDto save(PlayerDto playerDto) {
        Player player = playerMapper.toEntity(playerDto);
        Player savedPlayer = playerDao.save(player);
        return playerMapper.toDto(savedPlayer);
    }
}
