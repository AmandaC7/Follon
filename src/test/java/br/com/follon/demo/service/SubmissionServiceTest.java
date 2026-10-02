package br.com.follon.demo.service;

import br.com.follon.demo.entity.SubmissionEntity;
import br.com.follon.demo.entity.TeamEntity;
import br.com.follon.demo.repository.SubmissionRepository;
import br.com.follon.demo.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SubmissionServiceTest {
    @Mock
    SubmissionRepository submissionRepository;
    @Mock
    TeamRepository teamRepository;
    @InjectMocks
    SubmissionService submissionService;

    @Test
    void shouldCreateSubmission() {
        TeamEntity team = new TeamEntity();
        team.setId(4L);
        when(teamRepository.findById(4L)).thenReturn(Optional.of(team));
        when(submissionRepository.save(any(SubmissionEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        SubmissionEntity saved = submissionService.createSubmission(4L, "App", "desc", "github", "demo");

        assertEquals("App", saved.getProjectName());
    }

    @Test
    void shouldRejectSubmissionWithoutProjectName() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> submissionService.createSubmission(1L, " ", "desc", "g", "d"));
        assertEquals("Project name is required", ex.getMessage());
    }
}
