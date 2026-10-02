package br.com.follon.demo.repository;

import br.com.follon.demo.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:follon;MODE=PostgreSQL;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.sql.init.mode=never"
})
class EvaluationRepositoryTest {
    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
    SubmissionRepository submissionRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HackathonRepository hackathonRepository;

    @Test
    void shouldFindBySubmissionIdAndJudgeId() {
        HackathonEntity hackathon = new HackathonEntity();
        hackathon.setTitle("Hackathon Z");
        hackathon.setStartDate(LocalDate.of(2026, 6, 1));
        hackathon.setEndDate(LocalDate.of(2026, 6, 4));
        hackathonRepository.save(hackathon);

        TeamEntity team = new TeamEntity();
        team.setName("Gamma Team");
        team.setHackathon(hackathon);
        team.setDescription("desc");
        teamRepository.save(team);

        UserEntity judge = new UserEntity();
        judge.setName("Judge");
        judge.setEmail("judge@email.com");
        judge.setPassword("secret");
        userRepository.save(judge);

        SubmissionEntity submission = new SubmissionEntity();
        submission.setProjectName("Project Z");
        submission.setTeam(team);
        submission.setDescription("desc");
        submissionRepository.save(submission);

        EvaluationEntity evaluation = new EvaluationEntity();
        evaluation.setSubmission(submission);
        evaluation.setJudge(judge);
        evaluation.setInnovation(9);
        evaluation.setExecution(8);
        evaluation.setDesign(10);
        evaluation.setImpact(7);
        evaluationRepository.save(evaluation);

        assertEquals(1, evaluationRepository.findBySubmissionId(submission.getId()).size());
        assertEquals(1, evaluationRepository.findByJudgeId(judge.getId()).size());
    }
}
