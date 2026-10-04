package br.com.follon.demo.repository;

import br.com.follon.demo.entity.HackathonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:follon;MODE=PostgreSQL;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.sql.init.mode=never"
})
class HackathonRepositoryTest {
    @Autowired
    HackathonRepository hackathonRepository;

    @Test
    void shouldSaveAndFetchHackathon() {
        HackathonEntity hackathon = new HackathonEntity();
        hackathon.setTitle("Hackathon A");
        hackathon.setStartDate(LocalDate.of(2026, 10, 1));
        hackathon.setEndDate(LocalDate.of(2026, 10, 5));
        hackathon.setPrize(new BigDecimal("1000.00"));

        HackathonEntity saved = hackathonRepository.save(hackathon);

        assertNotNull(saved.getId());
        assertEquals("Hackathon A", hackathonRepository.findById(saved.getId()).orElseThrow().getTitle());
    }
}
