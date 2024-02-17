package com.example.d2tech.dto.mapper;


import com.example.d2tech.config.MapperConfig;
import com.example.d2tech.dto.wine.WineCreateRequestDto;
import com.example.d2tech.dto.wine.WineWithoutReviewsAndPicturesDto;
import com.example.d2tech.model.Wine;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(config = MapperConfig.class, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WineMapper {
    WineWithoutReviewsAndPicturesDto toDto(Wine wine);
    Wine toEntityFromCreate(WineCreateRequestDto dto);
}
