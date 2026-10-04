package br.com.follon.demo.service;

import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.entity.ParticipationEntity;
import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.repository.HackathonRepository;
import br.com.follon.demo.repository.ParticipationRepository;
import br.com.follon.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParticipationServiceTest {
    @Mock
    ParticipationRepository participationRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    HackathonRepository hackathonRepository;
    @InjectMocks
    ParticipationService participationService;

    @Test
    void shouldRejectDuplicateParticipation() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        HackathonEntity hackathon = new HackathonEntity();
        hackathon.setId(2L);
        ParticipationEntity duplicate = new ParticipationEntity();
        duplicate.setHackathon(hackathon);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(hackathonRepository.findById(2L)).thenReturn(Optional.of(hackathon));
        when(participationRepository.findByUserId(1L)).thenReturn(List.of(duplicate));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> participationService.createParticipation(1L, 2L));

        assertEquals("User is already participating in this hackathon", exception.getMessage());
    }
}
