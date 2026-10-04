package br.com.follon.demo.repository;

import br.com.follon.demo.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

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
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    void shouldFindByEmail() {
        UserEntity user = new UserEntity();
        user.setName("Ana");
        user.setEmail("ana@email.com");
        user.setPassword("secret");
        userRepository.save(user);

        assertTrue(userRepository.findByEmail("ana@email.com").isPresent());
    }
}
