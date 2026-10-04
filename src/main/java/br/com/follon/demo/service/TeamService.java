package br.com.follon.demo.service;

import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.entity.TeamEntity;
import br.com.follon.demo.repository.HackathonRepository;
import br.com.follon.demo.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final HackathonRepository hackathonRepository;

    public List<TeamEntity> listTeams(Long hackathonId) {
        if (hackathonId != null) {
            return teamRepository.findByHackathonId(hackathonId);
        }
        return teamRepository.findAll();
    }

    public TeamEntity createTeam(String name, String description, Long hackathonId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Team name is required");
        }
        if (hackathonId == null) {
            throw new IllegalArgumentException("Hackathon id is required");
        }
        if (teamRepository.existsByNameIgnoreCase(name.trim())) {
            throw new IllegalArgumentException("Team name already exists");
        }

        HackathonEntity hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon not found"));

        TeamEntity team = new TeamEntity();
        team.setName(name.trim());
        team.setDescription(description);
        team.setHackathon(hackathon);

        try {
            return teamRepository.save(team);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Team name already exists");
        }
    }
}
