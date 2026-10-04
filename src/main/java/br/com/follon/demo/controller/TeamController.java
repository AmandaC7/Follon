package br.com.follon.demo.controller;

import br.com.follon.demo.entity.TeamEntity;
import br.com.follon.demo.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/follon")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping("/teams")
    public List<TeamEntity> listTeams(@RequestParam(required = false) Long hackathonId) {
        return teamService.listTeams(hackathonId);
    }

    @PostMapping("/teams")
    public ResponseEntity<TeamEntity> createTeam(@RequestBody TeamRequest request) {
        return ResponseEntity.ok(teamService.createTeam(request.name(), request.description(), request.hackathonId()));
    }

    public record TeamRequest(String name, String description, Long hackathonId) {
    }
}
