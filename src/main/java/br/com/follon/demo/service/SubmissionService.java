package br.com.follon.demo.service;

import br.com.follon.demo.entity.SubmissionEntity;
import br.com.follon.demo.entity.TeamEntity;
import br.com.follon.demo.repository.SubmissionRepository;
import br.com.follon.demo.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final TeamRepository teamRepository;

    public List<SubmissionEntity> listSubmissions() {
        return submissionRepository.findAll();
    }

    public List<SubmissionEntity> listByTeam(Long teamId) {
        return submissionRepository.findByTeamId(teamId);
    }

    public SubmissionEntity createSubmission(Long teamId, String projectName, String description, String github, String demo) {
        if (projectName == null || projectName.isBlank()) {
            throw new IllegalArgumentException("Project name is required");
        }

        TeamEntity team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team not found"));

        SubmissionEntity submission = new SubmissionEntity();
        submission.setTeam(team);
        submission.setProjectName(projectName);
        submission.setDescription(description);
        submission.setGithub(github);
        submission.setDemo(demo);

        return submissionRepository.save(submission);
    }
}
