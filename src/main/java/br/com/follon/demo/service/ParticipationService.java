package br.com.follon.demo.service;

import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.entity.ParticipationEntity;
import br.com.follon.demo.entity.ParticipationId;
import br.com.follon.demo.entity.UserEntity;
import br.com.follon.demo.repository.HackathonRepository;
import br.com.follon.demo.repository.ParticipationRepository;
import br.com.follon.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipationService {

    private final ParticipationRepository participationRepository;
    private final UserRepository userRepository;
    private final HackathonRepository hackathonRepository;

    public List<ParticipationEntity> listByHackathon(Long hackathonId) {
        return participationRepository.findByHackathonId(hackathonId);
    }

    public List<ParticipationEntity> listByUser(Long userId) {
        return participationRepository.findByUserId(userId);
    }

    public ParticipationEntity createParticipation(Long userId, Long hackathonId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        HackathonEntity hackathon = hackathonRepository.findById(hackathonId)
                .orElseThrow(() -> new IllegalArgumentException("Hackathon not found"));

        boolean alreadyParticipates = participationRepository.findByUserId(userId).stream()
                .anyMatch(participation -> participation.getHackathon() != null
                        && participation.getHackathon().getId().equals(hackathonId));
        if (alreadyParticipates) {
            throw new IllegalArgumentException("User is already participating in this hackathon");
        }

        ParticipationEntity participation = new ParticipationEntity();
        participation.setId(new ParticipationId(user.getId(), hackathon.getId()));
        participation.setUser(user);
        participation.setHackathon(hackathon);

        return participationRepository.save(participation);
    }
}
