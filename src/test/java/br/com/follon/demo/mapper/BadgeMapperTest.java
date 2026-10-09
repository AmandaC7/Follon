package br.com.follon.demo.mapper;

import br.com.follon.demo.request.BadgeRequest;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class BadgeMapperTest {
    private final BadgeMapper badgeMapper = Mappers.getMapper(BadgeMapper.class);

    @Test
    void shouldMapPhotoToBadgeEntityFoto() {
        BadgeRequest request = new BadgeRequest();
        request.setPhoto(new byte[]{1, 2, 3});

        assertArrayEquals(new byte[]{1, 2, 3}, badgeMapper.toEntity(request).getFoto());
    }
}
