package br.com.follon.demo.mapper;

import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.request.HackathonRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HackatonMapper {

    HackathonEntity toEntity(HackathonRequest hackathonRequest);

}
