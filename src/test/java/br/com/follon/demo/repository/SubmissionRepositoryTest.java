package br.com.follon.demo.repository;

import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.entity.SubmissionEntity;
import br.com.follon.demo.entity.TeamEntity;
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
class SubmissionRepositoryTest {
    @Autowired
    SubmissionRepository submissionRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    HackathonRepository hackathonRepository;

    @Test
    void shouldFindSubmissionsByTeam() {
        HackathonEntity hackathon = new HackathonEntity();
        hackathon.setTitle("Hackathon 2");
        hackathon.setStartDate(LocalDate.of(2027, 1, 1));
        hackathon.setEndDate(LocalDate.of(2027, 1, 5));
        hackathonRepository.save(hackathon);

        TeamEntity team = new TeamEntity();
        team.setName("Team A");
        team.setDescription("desc");
        team.setHackathon(hackathon);
        teamRepository.save(team);

        SubmissionEntity submission = new SubmissionEntity();
        submission.setTeam(team);
        submission.setProjectName("Project A");
        submission.setDescription("desc");
        submissionRepository.save(submission);

        assertEquals(1, submissionRepository.findByTeamId(team.getId()).size());
    }
}
