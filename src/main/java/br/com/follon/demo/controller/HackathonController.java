package br.com.follon.demo.controller;

import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.service.HackathonService;
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
public class HackathonController {

    private final HackathonService hackathonService;

    @GetMapping("/hackathons")
    public List<HackathonEntity> listHackathons() {
        return hackathonService.listHackathons();
    }

    @GetMapping("/hackathons/{id}")
    public ResponseEntity<HackathonEntity> getHackathon(@PathVariable Long id) {
        return hackathonService.getHackathon(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/hackathons")
    public ResponseEntity<HackathonEntity> createHackathon(@RequestBody HackathonEntity hackathon) {
        return ResponseEntity.ok(hackathonService.createHackathon(hackathon));
    }
}
