package br.com.follon.demo.repository;

import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.entity.TeamEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:follon;MODE=PostgreSQL;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.sql.init.mode=never"
})
class TeamRepositoryTest {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    HackathonRepository hackathonRepository;

    @Test
    void shouldFindTeamsByHackathonAndCheckExistsByName() {
        HackathonEntity hackathon = new HackathonEntity();
        hackathon.setTitle("Hackathon X");
        hackathon.setStartDate(LocalDate.of(2026, 2, 1));
        hackathon.setEndDate(LocalDate.of(2026, 2, 3));
        hackathonRepository.save(hackathon);

        TeamEntity team = new TeamEntity();
        team.setName("Alpha Squad");
        team.setDescription("desc");
        team.setHackathon(hackathon);
        teamRepository.save(team);

        assertTrue(teamRepository.existsByNameIgnoreCase("alpha squad"));
        assertEquals(1, teamRepository.findByHackathonId(hackathon.getId()).size());
    }
}
