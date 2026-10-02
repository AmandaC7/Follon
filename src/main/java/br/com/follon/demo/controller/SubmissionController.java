package br.com.follon.demo.controller;

import br.com.follon.demo.entity.SubmissionEntity;
import br.com.follon.demo.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/follon")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmissionService submissionService;

    @GetMapping("/submissions")
    public List<SubmissionEntity> listSubmissions() {
        return submissionService.listSubmissions();
    }

    @GetMapping("/submissions/team/{teamId}")
    public List<SubmissionEntity> listByTeam(@PathVariable Long teamId) {
        return submissionService.listByTeam(teamId);
    }

    @PostMapping("/submissions")
    public ResponseEntity<SubmissionEntity> createSubmission(@RequestBody SubmissionRequest request) {
        return ResponseEntity.ok(submissionService.createSubmission(
                request.teamId(),
                request.projectName(),
                request.description(),
                request.github(),
                request.demo()
        ));
    }

    public record SubmissionRequest(Long teamId, String projectName, String description, String github, String demo) {
    }
}
