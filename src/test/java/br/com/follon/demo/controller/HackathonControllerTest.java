package br.com.follon.demo.controller;

import br.com.follon.demo.entity.HackathonEntity;
import br.com.follon.demo.mapper.UserMapper;
import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.request.HackathonRequest;
import br.com.follon.demo.security.TokenService;
import br.com.follon.demo.service.HackathonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = HackathonController.class)
@AutoConfigureMockMvc(addFilters = false)
class HackathonControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    TokenService tokenService;
    @MockitoBean
    UserRepository userRepository;
    @MockitoBean
    UserMapper userMapper;
    @MockitoBean
    HackathonService hackathonService;

    @Test
    void shouldListHackathons() throws Exception {
        HackathonEntity hackathon = new HackathonEntity();
        hackathon.setId(1L);
        hackathon.setTitle("Hackathon 1");
        hackathon.setLocation("SP");
        hackathon.setPrize(new BigDecimal("1500.00"));

        when(hackathonService.listHackathons()).thenReturn(List.of(hackathon));

        mockMvc.perform(get("/follon/hackathons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Hackathon 1"));
    }

    @Test
    void shouldCreateHackathon() throws Exception {
        HackathonRequest request = new HackathonRequest();
        request.setTitle("Hackathon 2");
        request.setDescription("desc");
        request.setLocation("RJ");
        request.setFormat("Presencial");
        request.setStartDate(LocalDate.of(2026, 11, 10));
        request.setEndDate(LocalDate.of(2026, 11, 12));
        request.setRegistrationStart(LocalDate.of(2026, 10, 1));
        request.setRegistrationEnd(LocalDate.of(2026, 10, 30));
        request.setPrize(new BigDecimal("2000.00"));

        HackathonEntity saved = new HackathonEntity();
        saved.setId(2L);
        saved.setTitle("Hackathon 2");
        when(hackathonService.createHackathon(any(HackathonRequest.class))).thenReturn(saved);

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        mockMvc.perform(post("/follon/hackathons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2));
    }
}
