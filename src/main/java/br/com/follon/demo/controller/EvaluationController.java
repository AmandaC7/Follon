package br.com.follon.demo.controller;

import br.com.follon.demo.entity.EvaluationEntity;
import br.com.follon.demo.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/follon")
@RequiredArgsConstructor
public class EvaluationController {

    private final EvaluationService evaluationService;

    @GetMapping("/evaluations")
    public List<EvaluationEntity> listEvaluations() {
        return evaluationService.listEvaluations();
    }

    @GetMapping("/evaluations/submission/{submissionId}")
    public List<EvaluationEntity> listBySubmission(@PathVariable Long submissionId) {
        return evaluationService.listBySubmission(submissionId);
    }

    @PostMapping("/evaluations")
    public ResponseEntity<EvaluationEntity> createEvaluation(@RequestBody EvaluationRequest request) {
        return ResponseEntity.ok(evaluationService.createEvaluation(
                request.submissionId(),
                request.judgeId(),
                request.innovation(),
                request.execution(),
                request.design(),
                request.impact(),
                request.feedback()
        ));
    }

    public record EvaluationRequest(Long submissionId, Long judgeId, Integer innovation, Integer execution, Integer design,
                                   Integer impact, String feedback) {
    }
}
