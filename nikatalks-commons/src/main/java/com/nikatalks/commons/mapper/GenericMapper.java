package com.nikatalks.commons.mapper;

import org.modelmapper.ModelMapper;

public class GenericMapper {
    private static ModelMapper modelMapper = new ModelMapper();

    public static <S, D> D map(S source, Class<D> destinationClass) {
        return modelMapper.map(source, destinationClass);
    }
}
