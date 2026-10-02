package br.com.follon.demo.repository;

import br.com.follon.demo.entity.FieldOfInterestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FieldOfInterestRepository extends JpaRepository<FieldOfInterestEntity, Long> {

    Optional<FieldOfInterestEntity> findByName(String name);
    List<FieldOfInterestEntity> findAllByNameIn(List<String> names);
}
