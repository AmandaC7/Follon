package br.com.follon.demo.service;

import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.entity.TeamEntity;
import br.com.follon.demo.repository.HackathonRepository;
import br.com.follon.demo.repository.TeamRepository;
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
class TeamServiceTest {
    @Mock
    TeamRepository teamRepository;
    @Mock
    HackathonRepository hackathonRepository;
    @InjectMocks
    TeamService teamService;

    @Test
    void shouldCreateTeam() {
        HackathonEntity hackathon = new HackathonEntity();
        hackathon.setId(5L);
        when(hackathonRepository.findById(5L)).thenReturn(Optional.of(hackathon));
        when(teamRepository.existsByNameIgnoreCase("Alpha")).thenReturn(false);
        when(teamRepository.save(any(TeamEntity.class))).thenAnswer(inv -> {
            TeamEntity team = inv.getArgument(0);
            team.setId(10L);
            return team;
        });

        TeamEntity created = teamService.createTeam("Alpha", "best team", 5L);

        assertEquals("Alpha", created.getName());
    }
}
