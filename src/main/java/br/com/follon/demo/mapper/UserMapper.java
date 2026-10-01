package br.com.follon.demo.mapper;

import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.request.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "fieldOfInterest", expression = "java(mapFieldOfInterest(userRequest.getFieldOfInterest()))")
    UserEntity toEntity(RegisterRequest userRequest);

}
