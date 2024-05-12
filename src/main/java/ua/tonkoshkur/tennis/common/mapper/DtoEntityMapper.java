package ua.tonkoshkur.tennis.common.mapper;

public interface DtoEntityMapper<D, E> {
    D toDto(E entity);

    E toEntity(D dto);
}
