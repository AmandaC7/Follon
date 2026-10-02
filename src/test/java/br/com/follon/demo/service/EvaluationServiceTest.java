package br.com.follon.demo.service;

import br.com.follon.demo.entity.EvaluationEntity;
import br.com.follon.demo.entity.SubmissionEntity;
import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.repository.EvaluationRepository;
import br.com.follon.demo.repository.SubmissionRepository;
import br.com.follon.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EvaluationServiceTest {
    @Mock
    EvaluationRepository evaluationRepository;
    @Mock
    SubmissionRepository submissionRepository;
    @Mock
    UserRepository userRepository;
    @InjectMocks
    EvaluationService evaluationService;

    @Test
    void shouldCreateEvaluation() {
        SubmissionEntity submission = new SubmissionEntity();
        submission.setId(1L);
        UserEntity judge = new UserEntity();
        judge.setId(2L);
        when(submissionRepository.findById(1L)).thenReturn(Optional.of(submission));
        when(userRepository.findById(2L)).thenReturn(Optional.of(judge));
        when(evaluationRepository.save(any(EvaluationEntity.class))).thenAnswer(inv -> inv.getArgument(0));

        EvaluationEntity created = evaluationService.createEvaluation(1L, 2L, 9, 8, 7, 10, "Fantástico");

        assertEquals(9, created.getInnovation());
        assertEquals("Fantástico", created.getFeedback());
    }

    @Test
    void shouldComputeAverageScore() {
        EvaluationEntity e1 = new EvaluationEntity();
        e1.setInnovation(10); e1.setExecution(8); e1.setDesign(9); e1.setImpact(7);
        when(evaluationRepository.findBySubmissionId(3L)).thenReturn(List.of(e1));

        assertEquals(8.5, evaluationService.averageScore(3L), 0.01);
    }
}
