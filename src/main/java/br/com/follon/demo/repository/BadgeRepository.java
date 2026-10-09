package br.com.follon.demo.repository;

import br.com.follon.demo.entity.BadgeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<BadgeEntity, Long> {
}
