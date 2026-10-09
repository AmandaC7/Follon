package br.com.follon.demo.service;

import br.com.follon.demo.entity.BadgeEntity;
import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.mapper.BadgeMapper;
import br.com.follon.demo.repository.BadgeRepository;
import br.com.follon.demo.repository.HackathonRepository;
import br.com.follon.demo.request.BadgeRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BadgeServiceTest {
    @Mock
    BadgeRepository badgeRepository;
    @Mock
    HackathonRepository hackathonRepository;
    @Mock
    BadgeMapper badgeMapper;
    @InjectMocks
    BadgeService badgeService;

    @Test
    void shouldCreateBadgeForExistingHackathon() {
        HackathonEntity hackathon = new HackathonEntity();
        hackathon.setId(5L);
        BadgeRequest request = new BadgeRequest();
        request.setHackathonId(5L);
        request.setPhoto(new byte[]{1, 2, 3});
        BadgeEntity mappedBadge = new BadgeEntity();
        BadgeEntity savedBadge = new BadgeEntity();

        when(hackathonRepository.findById(5L)).thenReturn(Optional.of(hackathon));
        when(badgeMapper.toEntity(request)).thenReturn(mappedBadge);
        when(badgeRepository.save(mappedBadge)).thenReturn(savedBadge);

        BadgeEntity result = badgeService.createBadge(request);

        assertSame(savedBadge, result);
        assertSame(hackathon, mappedBadge.getHackathon());
        verify(hackathonRepository).findById(5L);
        verify(badgeMapper).toEntity(request);
        verify(badgeRepository).save(mappedBadge);
    }

    @Test
    void shouldRejectBadgeWhenHackathonDoesNotExist() {
        BadgeRequest request = new BadgeRequest();
        request.setHackathonId(99L);
        when(hackathonRepository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> badgeService.createBadge(request));

        assertEquals("Hackathon not found", exception.getMessage());
        verify(badgeMapper, never()).toEntity(any());
        verify(badgeRepository, never()).save(any());
    }

    @Test
    void shouldListBadges() {
        List<BadgeEntity> badges = List.of(new BadgeEntity(), new BadgeEntity());
        when(badgeRepository.findAll()).thenReturn(badges);

        assertSame(badges, badgeService.listBadges());
        verify(badgeRepository).findAll();
    }

    @Test
    void shouldFindBadgeById() {
        BadgeEntity badge = new BadgeEntity();
        when(badgeRepository.findById(3L)).thenReturn(Optional.of(badge));

        assertSame(badge, badgeService.badgeById(3L));
        verify(badgeRepository).findById(3L);
    }

    @Test
    void shouldRejectUnknownBadgeId() {
        when(badgeRepository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> badgeService.badgeById(99L));

        assertEquals("Badge not found", exception.getMessage());
        verify(badgeRepository).findById(99L);
    }
}
