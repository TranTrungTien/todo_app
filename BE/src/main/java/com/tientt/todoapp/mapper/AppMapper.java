package com.tientt.todoapp.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AppMapper {

    private final ModelMapper modelMapper;

    public AppMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Method to map from entity to DTO
    public <T, U> U mapEntityToDTO(T entity, Class<U> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    // Method to map from DTO to entity
    public <T, U> T mapDTOToEntity(U dto, Class<T> entityClass) {
        return modelMapper.map(dto, entityClass);
    }

}
