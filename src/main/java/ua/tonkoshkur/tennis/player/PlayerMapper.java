package ua.tonkoshkur.tennis.player;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import ua.tonkoshkur.tennis.common.mapper.DtoEntityMapper;

@RequiredArgsConstructor
public class PlayerMapper implements DtoEntityMapper<PlayerDto, Player> {

    private final ModelMapper modelMapper;

    @Override
    public PlayerDto toDto(Player entity) {
        return modelMapper.map(entity, PlayerDto.class);
    }

    @Override
    public Player toEntity(PlayerDto dto) {
        return modelMapper.map(dto, Player.class);
    }
}
