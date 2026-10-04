package br.com.follon.demo.repository;

import br.com.follon.demo.entity.HackathonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HackathonRepository extends JpaRepository<HackathonEntity, Long> {
}
