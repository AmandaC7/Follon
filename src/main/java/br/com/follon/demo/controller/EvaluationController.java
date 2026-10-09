package br.com.follon.demo.controller;

import br.com.follon.demo.entity.EvaluationEntity;
import br.com.follon.demo.request.EvaluationRequest;
import br.com.follon.demo.service.EvaluationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        EvaluationEntity evaluation = evaluationService.createEvaluation(
                request.getSubmissionId(),
                request.getJudgeId(),
                request.getInnovation(),
                request.getExecution(),
                request.getDesign(),
                request.getImpact(),
                request.getFeedback()
        );

        return ResponseEntity.ok(evaluation);
    }

}
