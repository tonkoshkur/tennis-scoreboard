package ua.tonkoshkur.tennis.match;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import ua.tonkoshkur.tennis.common.mapper.DtoEntityMapper;

@RequiredArgsConstructor
public class MatchMapper implements DtoEntityMapper<MatchDto, Match> {

    private final ModelMapper modelMapper;

    @Override
    public MatchDto toDto(Match entity) {
        return modelMapper.map(entity, MatchDto.class);
    }

    @Override
    public Match toEntity(MatchDto dto) {
        return modelMapper.map(dto, Match.class);
    }
}
