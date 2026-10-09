package br.com.follon.demo.controller;

import br.com.follon.demo.entity.BadgeEntity;
import br.com.follon.demo.request.BadgeRequest;
import br.com.follon.demo.service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/follon")
@RequiredArgsConstructor
public class BadgeController {

    private final BadgeService badgeService;

    @GetMapping("/badges")
    public List<BadgeEntity> listBadges() {
        return badgeService.listBadges();
    }

    @GetMapping("/badges/{id}")
    public BadgeEntity getBadge(@PathVariable Long id) {
        return badgeService.badgeById(id);
    }

    @PostMapping("/badges")
    public ResponseEntity<BadgeEntity> createBadge(@RequestBody BadgeRequest badge) {
        return ResponseEntity.ok(badgeService.createBadge(badge));
    }


}
