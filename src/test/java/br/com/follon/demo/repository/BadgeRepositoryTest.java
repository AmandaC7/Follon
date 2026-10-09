package br.com.follon.demo.repository;

import br.com.follon.demo.entity.BadgeEntity;
import br.com.follon.demo.entity.HackathonEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DataJpaTest(properties = {
        "spring.datasource.url=jdbc:h2:mem:follon;MODE=PostgreSQL;DATABASE_TO_UPPER=false;DB_CLOSE_DELAY=-1",
        "spring.datasource.username=sa",
        "spring.datasource.password=",
        "spring.datasource.driver-class-name=org.h2.Driver",
        "spring.jpa.hibernate.ddl-auto=create-drop",
        "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect",
        "spring.sql.init.mode=never"
})
class BadgeRepositoryTest {
    @Autowired
    BadgeRepository badgeRepository;
    @Autowired
    HackathonRepository hackathonRepository;

    @Test
    void shouldSaveAndFetchPhotoAsBinary() {
        HackathonEntity hackathon = new HackathonEntity();
        hackathon.setTitle("Hackathon A");
        hackathon = hackathonRepository.save(hackathon);

        BadgeEntity badge = new BadgeEntity();
        badge.setHackathon(hackathon);
        badge.setFoto(new byte[]{1, 2, 3});
        badge = badgeRepository.save(badge);

        BadgeEntity saved = badgeRepository.findById(badge.getId()).orElseThrow();
        assertArrayEquals(new byte[]{1, 2, 3}, saved.getFoto());
    }
}
