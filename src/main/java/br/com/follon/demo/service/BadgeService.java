package br.com.follon.demo.service;

import br.com.follon.demo.entity.BadgeEntity;
import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.mapper.BadgeMapper;
import br.com.follon.demo.repository.BadgeRepository;
import br.com.follon.demo.repository.HackathonRepository;
import br.com.follon.demo.request.BadgeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BadgeService {

    private final BadgeRepository badgeRepository;
    private final HackathonRepository hackathonRepository;
    private final BadgeMapper badgeMapper;

    public BadgeEntity createBadge(BadgeRequest badgeRequest) {

        HackathonEntity hackathonEntity = hackathonRepository.findById(badgeRequest.getHackathonId())
                .orElseThrow(() -> new IllegalArgumentException("Hackathon not found"));

        BadgeEntity badgeEntity = badgeMapper.toEntity(badgeRequest);
        badgeEntity.setHackathon(hackathonEntity);

        return badgeRepository.save(badgeEntity);
    }

    public List<BadgeEntity> listBadges() {
        return badgeRepository.findAll();
    }

    public BadgeEntity badgeById(Long id) {
        return badgeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Badge not found"));
    }


}
