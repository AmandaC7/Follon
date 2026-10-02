package br.com.follon.demo.repository;

import br.com.follon.demo.entity.ParticipationEntity;
import br.com.follon.demo.entity.ParticipationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<ParticipationEntity, ParticipationId> {
    List<ParticipationEntity> findByHackathonId(Long hackathonId);
    List<ParticipationEntity> findByUserId(Long userId);
}
