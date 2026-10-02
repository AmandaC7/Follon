package br.com.follon.demo.repository;

import br.com.follon.demo.entity.TeamRequestEntity;
import br.com.follon.demo.entity.TeamRequestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRequestRepository extends JpaRepository<TeamRequestEntity, TeamRequestId> {
    List<TeamRequestEntity> findByTeamId(Long teamId);
    List<TeamRequestEntity> findByUserId(Long userId);
}
