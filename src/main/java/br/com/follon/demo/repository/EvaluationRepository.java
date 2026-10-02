package br.com.follon.demo.repository;

import br.com.follon.demo.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EvaluationRepository extends JpaRepository<EvaluationEntity, Long> {
    List<EvaluationEntity> findBySubmissionId(Long submissionId);
    List<EvaluationEntity> findByJudgeId(Long judgeId);
}
