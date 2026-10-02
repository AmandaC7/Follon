package br.com.follon.demo.controller;

import br.com.follon.demo.entity.ParticipationEntity;
import br.com.follon.demo.service.ParticipationService;
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
public class ParticipationController {

    private final ParticipationService participationService;

    @GetMapping("/participations/hackathon/{hackathonId}")
    public List<ParticipationEntity> listByHackathon(@PathVariable Long hackathonId) {
        return participationService.listByHackathon(hackathonId);
    }

    @GetMapping("/participations/user/{userId}")
    public List<ParticipationEntity> listByUser(@PathVariable Long userId) {
        return participationService.listByUser(userId);
    }

    @PostMapping("/participations")
    public ResponseEntity<ParticipationEntity> createParticipation(@RequestBody ParticipationRequest request) {
        return ResponseEntity.ok(participationService.createParticipation(request.userId(), request.hackathonId()));
    }

    public record ParticipationRequest(Long userId, Long hackathonId) {
    }
}
