package br.com.follon.demo.controller;

import br.com.follon.demo.entity.TeamEntity;
import br.com.follon.demo.mapper.UserMapper;
import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.security.TokenService;
import br.com.follon.demo.service.TeamService;
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

@WebMvcTest(controllers = TeamController.class)
@AutoConfigureMockMvc(addFilters = false)
class TeamControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    TokenService tokenService;
    @MockitoBean
    UserRepository userRepository;
    @MockitoBean
    UserMapper userMapper;
    @MockitoBean
    TeamService teamService;

    @Test
    void shouldCreateTeam() throws Exception {
        TeamEntity entity = new TeamEntity();
        entity.setId(7L);
        entity.setName("Team 7");
        when(teamService.createTeam("Team 7", "desc", 8L)).thenReturn(entity);

        String body = "{\"name\":\"Team 7\",\"description\":\"desc\",\"hackathonId\":8}";
        mockMvc.perform(post("/follon/teams").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Team 7"));
    }
}
