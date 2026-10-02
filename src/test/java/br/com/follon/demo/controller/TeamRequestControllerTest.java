package br.com.follon.demo.controller;

import br.com.follon.demo.entity.TeamRequestEntity;
import br.com.follon.demo.entity.TeamRequestId;
import br.com.follon.demo.mapper.UserMapper;
import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.security.TokenService;
import br.com.follon.demo.service.TeamRequestService;
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

@WebMvcTest(controllers = TeamRequestController.class)
@AutoConfigureMockMvc(addFilters = false)
class TeamRequestControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    TokenService tokenService;
    @MockitoBean
    UserRepository userRepository;
    @MockitoBean
    UserMapper userMapper;
    @MockitoBean
    TeamRequestService teamRequestService;

    @Test
    void shouldCreateTeamRequest() throws Exception {
        TeamRequestEntity entity = new TeamRequestEntity();
        entity.setId(new TeamRequestId(1L, 2L));
        entity.setStatus("APPROVED");
        when(teamRequestService.createRequest(1L, 2L, "APPROVED")).thenReturn(entity);

        String body = "{\"teamId\":1,\"userId\":2,\"status\":\"APPROVED\"}";
        mockMvc.perform(post("/follon/team-requests").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("APPROVED"));
    }
}
