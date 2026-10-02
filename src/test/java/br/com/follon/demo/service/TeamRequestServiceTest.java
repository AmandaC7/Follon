package br.com.follon.demo.service;

import br.com.follon.demo.entity.TeamEntity;
import br.com.follon.demo.entity.TeamRequestEntity;
import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.repository.TeamRepository;
import br.com.follon.demo.repository.TeamRequestRepository;
import br.com.follon.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamRequestServiceTest {
    @Mock
    TeamRequestRepository teamRequestRepository;
    @Mock
    TeamRepository teamRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    TeamRequestService teamRequestService;

    @Test
    void shouldNormalizeStatus() {
        TeamEntity team = new TeamEntity();
        team.setId(1L);
        UserEntity user = new UserEntity();
        user.setId(2L);
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));
        when(teamRequestRepository.save(any(TeamRequestEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        TeamRequestEntity created = teamRequestService.createRequest(1L, 2L, "approved");

        assertEquals("APPROVED", created.getStatus());
    }
}
