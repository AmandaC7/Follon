package br.com.follon.demo.service;

import br.com.follon.demo.entity.TeamEntity;
import br.com.follon.demo.entity.TeamRequestEntity;
import br.com.follon.demo.entity.TeamRequestId;
import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.repository.TeamRepository;
import br.com.follon.demo.repository.TeamRequestRepository;
import br.com.follon.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamRequestService {

    private final TeamRequestRepository teamRequestRepository;
    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    public List<TeamRequestEntity> listByTeam(Long teamId) {
        return teamRequestRepository.findByTeamId(teamId);
    }

    public List<TeamRequestEntity> listByUser(Long userId) {
        return teamRequestRepository.findByUserId(userId);
    }

    public TeamRequestEntity createRequest(Long teamId, Long userId, String status) {
        TeamEntity team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        TeamRequestEntity teamRequest = new TeamRequestEntity();
        teamRequest.setId(new TeamRequestId(team.getId(), user.getId()));
        teamRequest.setTeam(team);
        teamRequest.setUser(user);
        teamRequest.setStatus(normalizeStatus(status));

        return teamRequestRepository.save(teamRequest);
    }

    public TeamRequestEntity updateStatus(Long teamId, Long userId, String status) {
        TeamRequestEntity teamRequest = teamRequestRepository.findById(new TeamRequestId(teamId, userId))
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));

        teamRequest.setStatus(normalizeStatus(status));
        return teamRequestRepository.save(teamRequest);
    }

    private String normalizeStatus(String status) {
        if (status == null || status.isBlank()) {
            return "PENDING";
        }
        String normalized = status.trim().toUpperCase();
        if (!normalized.equals("PENDING") && !normalized.equals("APPROVED") && !normalized.equals("REJECTED")) {
            throw new IllegalArgumentException("Status must be PENDING, APPROVED or REJECTED");
        }
        return normalized;
    }
}
