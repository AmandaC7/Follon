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
class TeamRequestRepositoryTest {
    @Autowired
    TeamRequestRepository teamRequestRepository;
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    HackathonRepository hackathonRepository;
    @Autowired
    UserRepository userRepository;

    @Test
    void shouldFindByTeamIdAndUserId() {
        HackathonEntity hackathon = new HackathonEntity();
        hackathon.setTitle("Hackathon Y");
        hackathon.setStartDate(LocalDate.of(2026, 3, 1));
        hackathon.setEndDate(LocalDate.of(2026, 3, 4));
        hackathonRepository.save(hackathon);

        TeamEntity team = new TeamEntity();
        team.setName("Beta Team");
        team.setDescription("desc");
        team.setHackathon(hackathon);
        teamRepository.save(team);

        UserEntity user = new UserEntity();
        user.setName("Bob");
        user.setEmail("bob@email.com");
        user.setPassword("secret");
        userRepository.save(user);

        TeamRequestEntity request = new TeamRequestEntity();
        request.setId(new TeamRequestId(team.getId(), user.getId()));
        request.setTeam(team);
        request.setUser(user);
        request.setStatus("PENDING");
        teamRequestRepository.save(request);

        assertEquals(1, teamRequestRepository.findByTeamId(team.getId()).size());
        assertEquals(1, teamRequestRepository.findByUserId(user.getId()).size());
    }
}
