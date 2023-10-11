package ru.dmzotov.ipcollector.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMapper<E, D> {
    private final Class<E> entityClass;
    private final Class<D> dtoClass;
    @Autowired
    private ModelMapper modelMapper;

    public AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    public E toEntity(D dto) {
        if (dto == null) {
            return null;
        }
        E entity = modelMapper.map(dto, entityClass);
        mapEntity(dto, entity);
        return entity;
    }

    public D toDto(E entity) {
        if (entity == null) {
            return null;
        }
        D dto =  modelMapper.map(entity, dtoClass);
        mapDto(entity, dto);
        return dto;
    }

    protected void mapEntity(D source, E destination) {
    }

    protected void mapDto(E source, D destination) {
    }
}
