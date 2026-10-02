package br.com.follon.demo.service;

import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.repository.HackathonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HackathonService {

    private final HackathonRepository hackathonRepository;

    public List<HackathonEntity> listHackathons() {
        return hackathonRepository.findAll();
    }

    public Optional<HackathonEntity> getHackathon(Long id) {
        return hackathonRepository.findById(id);
    }

    public HackathonEntity createHackathon(HackathonEntity hackathon) {
        validateHackathon(hackathon);
        return hackathonRepository.save(hackathon);
    }

    private void validateHackathon(HackathonEntity hackathon) {
        if (hackathon == null) {
            throw new IllegalArgumentException("Hackathon is required");
        }
        if (hackathon.getTitle() == null || hackathon.getTitle().isBlank()) {
            throw new IllegalArgumentException("Title is required");
        }
        if (hackathon.getStartDate() != null && hackathon.getEndDate() != null
                && hackathon.getStartDate().isAfter(hackathon.getEndDate())) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
        if (hackathon.getRegistrationStart() != null && hackathon.getRegistrationEnd() != null
                && hackathon.getRegistrationStart().isAfter(hackathon.getRegistrationEnd())) {
            throw new IllegalArgumentException("Registration start cannot be after registration end");
        }
    }
}
