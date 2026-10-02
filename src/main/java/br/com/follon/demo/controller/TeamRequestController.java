package br.com.follon.demo.controller;

import br.com.follon.demo.entity.TeamRequestEntity;
import br.com.follon.demo.service.TeamRequestService;
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
public class TeamRequestController {

    private final TeamRequestService teamRequestService;

    @GetMapping("/team-requests/team/{teamId}")
    public List<TeamRequestEntity> listByTeam(@PathVariable Long teamId) {
        return teamRequestService.listByTeam(teamId);
    }

    @GetMapping("/team-requests/user/{userId}")
    public List<TeamRequestEntity> listByUser(@PathVariable Long userId) {
        return teamRequestService.listByUser(userId);
    }

    @PostMapping("/team-requests")
    public ResponseEntity<TeamRequestEntity> createRequest(@RequestBody TeamRequestPayload request) {
        return ResponseEntity.ok(teamRequestService.createRequest(request.teamId(), request.userId(), request.status()));
    }

    public record TeamRequestPayload(Long teamId, Long userId, String status) {
    }
}
