package br.com.follon.demo.repository;

import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.entity.ParticipationEntity;
import br.com.follon.demo.entity.ParticipationId;
import br.com.follon.demo.entity.UserEntity;
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
class ParticipationRepositoryTest {
    @Autowired
    ParticipationRepository participationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    HackathonRepository hackathonRepository;

    @Test
    void shouldFindParticipationByUserAndHackathon() {
        UserEntity user = new UserEntity();
        user.setName("Maria");
        user.setEmail("maria@email.com");
        user.setPassword("secret");
        userRepository.save(user);

        HackathonEntity hackathon = new HackathonEntity();
        hackathon.setTitle("Hackathon 1");
        hackathon.setStartDate(LocalDate.of(2026, 1, 1));
        hackathon.setEndDate(LocalDate.of(2026, 1, 7));
        hackathonRepository.save(hackathon);

        ParticipationEntity participation = new ParticipationEntity();
        participation.setId(new ParticipationId(user.getId(), hackathon.getId()));
        participation.setUser(user);
        participation.setHackathon(hackathon);
        participationRepository.save(participation);

        assertEquals(1, participationRepository.findByUserId(user.getId()).size());
        assertEquals(1, participationRepository.findByHackathonId(hackathon.getId()).size());
    }
}
