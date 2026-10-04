package br.com.follon.demo.mapper;

import br.com.follon.demo.entity.FieldOfInterestEntity;
import br.com.follon.demo.request.FieldOfInterestRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FieldOfInterestMapper {

    FieldOfInterestEntity toEntity(FieldOfInterestRequest fieldRequest);

}
