package br.com.follon.demo.controller;

import br.com.follon.demo.entity.SubmissionEntity;
import br.com.follon.demo.mapper.UserMapper;
import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.security.TokenService;
import br.com.follon.demo.service.SubmissionService;
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

@WebMvcTest(controllers = SubmissionController.class)
@AutoConfigureMockMvc(addFilters = false)
class SubmissionControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    TokenService tokenService;
    @MockitoBean
    UserRepository userRepository;
    @MockitoBean
    UserMapper userMapper;
    @MockitoBean
    SubmissionService submissionService;

    @Test
    void shouldCreateSubmission() throws Exception {
        SubmissionEntity entity = new SubmissionEntity();
        entity.setId(1L);
        entity.setProjectName("Project");
        when(submissionService.createSubmission(5L, "Project", "desc", "github", "demo")).thenReturn(entity);

        String body = "{\"teamId\":5,\"projectName\":\"Project\",\"description\":\"desc\",\"github\":\"github\",\"demo\":\"demo\"}";
        mockMvc.perform(post("/follon/submissions").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.projectName").value("Project"));
    }
}
