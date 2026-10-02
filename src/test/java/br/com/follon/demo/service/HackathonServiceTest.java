package br.com.follon.demo.service;

import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.repository.HackathonRepository;
import br.com.follon.demo.request.HackathonRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HackathonServiceTest {
    @Mock
    HackathonRepository hackathonRepository;
    @InjectMocks
    HackathonService hackathonService;

    @Test
    void shouldListHackathons() {
        HackathonEntity item = new HackathonEntity();
        item.setId(1L);
        item.setTitle("Title");
        when(hackathonRepository.findAll()).thenReturn(List.of(item));

        assertEquals(1, hackathonService.listHackathons().size());
    }

    @Test
    void shouldCreateValidHackathon() {
        HackathonRequest request = new HackathonRequest();
        request.setTitle(" Hackathon ");
        request.setDescription("desc");
        request.setLocation("SP");
        request.setFormat("Online");
        request.setStartDate(LocalDate.of(2026, 10, 1));
        request.setEndDate(LocalDate.of(2026, 10, 5));
        request.setRegistrationStart(LocalDate.of(2026, 9, 1));
        request.setRegistrationEnd(LocalDate.of(2026, 9, 30));
        request.setPrize(new BigDecimal("1000.00"));
        when(hackathonRepository.save(any(HackathonEntity.class))).thenAnswer(invocation -> invocation.getArgument(0));

        HackathonEntity saved = hackathonService.createHackathon(request);

        assertEquals("Hackathon", saved.getTitle());
    }

    @Test
    void shouldRejectHackathonWithInvalidDates() {
        HackathonRequest request = new HackathonRequest();
        request.setTitle("Hackathon");
        request.setStartDate(LocalDate.of(2026, 10, 10));
        request.setEndDate(LocalDate.of(2026, 10, 5));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> hackathonService.createHackathon(request));
        assertEquals("Start date cannot be after end date", ex.getMessage());
    }
}
