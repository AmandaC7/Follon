package br.com.follon.demo.mapper;

import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.request.FieldOfInterestRequest;
import br.com.follon.demo.request.RegisterRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "fieldOfInterest", expression = "java(mapFieldOfInterest(userRequest.getFieldOfInterest()))")
    UserEntity toEntity(RegisterRequest userRequest);

    default String[] mapFieldOfInterest(List<FieldOfInterestRequest> fieldOfInterest) {
        if (fieldOfInterest == null) {
            return new String[0];
        }

        return fieldOfInterest.stream()
                .map(FieldOfInterestRequest::getName)
                .toArray(String[]::new);
    }

}
