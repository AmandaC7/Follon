package br.com.follon.demo.mapper;

import br.com.follon.demo.entity.BadgeEntity;
import br.com.follon.demo.request.BadgeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BadgeMapper {

    @Mapping(target = "foto", source = "photo")
    BadgeEntity toEntity(BadgeRequest badgeRequest);
}
