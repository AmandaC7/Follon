package br.com.follon.demo.controller;

import br.com.follon.demo.entity.ParticipationEntity;
import br.com.follon.demo.entity.ParticipationId;
import br.com.follon.demo.mapper.UserMapper;
import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.security.TokenService;
import br.com.follon.demo.service.ParticipationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ParticipationController.class)
@AutoConfigureMockMvc(addFilters = false)
class ParticipationControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    TokenService tokenService;
    @MockitoBean
    UserRepository userRepository;
    @MockitoBean
    UserMapper userMapper;
    @MockitoBean
    ParticipationService participationService;

    @Test
    void shouldCreateParticipation() throws Exception {
        ParticipationEntity entity = new ParticipationEntity();
        entity.setId(new ParticipationId(1L, 2L));
        when(participationService.createParticipation(1L, 2L)).thenReturn(entity);

        String body = "{\"userId\":1,\"hackathonId\":2}";
        mockMvc.perform(post("/follon/participations").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id.userId").value(1));
    }
}
