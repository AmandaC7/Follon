package br.com.follon.demo.service;

import br.com.follon.demo.entity.EvaluationEntity;
import br.com.follon.demo.entity.SubmissionEntity;
import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.repository.EvaluationRepository;
import br.com.follon.demo.repository.SubmissionRepository;
import br.com.follon.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvaluationService {

    private final EvaluationRepository evaluationRepository;
    private final SubmissionRepository submissionRepository;
    private final UserRepository userRepository;

    public List<EvaluationEntity> listEvaluations() {
        return evaluationRepository.findAll();
    }

    public List<EvaluationEntity> listBySubmission(Long submissionId) {
        return evaluationRepository.findBySubmissionId(submissionId);
    }

    public EvaluationEntity createEvaluation(Long submissionId, Long judgeId,
                                            Integer innovation, Integer execution,
                                            Integer design, Integer impact, String feedback) {
        if (submissionId == null || judgeId == null) {
            throw new IllegalArgumentException("Submission id and judge id are required");
        }
        SubmissionEntity submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new IllegalArgumentException("Submission not found"));
        UserEntity judge = userRepository.findById(judgeId)
                .orElseThrow(() -> new IllegalArgumentException("Judge not found"));

        validateScore(innovation, "innovation");
        validateScore(execution, "execution");
        validateScore(design, "design");
        validateScore(impact, "impact");

        EvaluationEntity evaluation = new EvaluationEntity();
        evaluation.setSubmission(submission);
        evaluation.setJudge(judge);
        evaluation.setInnovation(innovation);
        evaluation.setExecution(execution);
        evaluation.setDesign(design);
        evaluation.setImpact(impact);
        evaluation.setFeedback(feedback);

        return evaluationRepository.save(evaluation);
    }

    public double averageScore(Long submissionId) {
        List<EvaluationEntity> evaluations = evaluationRepository.findBySubmissionId(submissionId);
        if (evaluations.isEmpty()) {
            return 0.0;
        }

        double total = evaluations.stream()
                .mapToDouble(e -> (e.getInnovation() == null ? 0 : e.getInnovation())
                        + (e.getExecution() == null ? 0 : e.getExecution())
                        + (e.getDesign() == null ? 0 : e.getDesign())
                        + (e.getImpact() == null ? 0 : e.getImpact()))
                .sum();

        return total / (evaluations.size() * 4.0);
    }

    private void validateScore(Integer score, String field) {
        if (score == null) {
            throw new IllegalArgumentException(field + " score is required");
        }
        if (score < 0 || score > 10) {
            throw new IllegalArgumentException(field + " score must be between 0 and 10");
        }
    }
}
