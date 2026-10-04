package br.com.follon.demo.controller;

import br.com.follon.demo.entity.EvaluationEntity;
import br.com.follon.demo.mapper.UserMapper;
import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.security.TokenService;
import br.com.follon.demo.service.EvaluationService;
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

@WebMvcTest(controllers = EvaluationController.class)
@AutoConfigureMockMvc(addFilters = false)
class EvaluationControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    TokenService tokenService;
    @MockitoBean
    UserRepository userRepository;
    @MockitoBean
    UserMapper userMapper;
    @MockitoBean
    EvaluationService evaluationService;

    @Test
    void shouldCreateEvaluation() throws Exception {
        EvaluationEntity evaluation = new EvaluationEntity();
        evaluation.setId(1L);
        evaluation.setInnovation(9);
        evaluation.setExecution(8);
        evaluation.setDesign(7);
        evaluation.setImpact(10);
        when(evaluationService.createEvaluation(1L, 2L, 9, 8, 7, 10, "ok")).thenReturn(evaluation);

        String body = "{\"submissionId\":1,\"judgeId\":2,\"innovation\":9,\"execution\":8,\"design\":7,\"impact\":10,\"feedback\":\"ok\"}";
        mockMvc.perform(post("/follon/evaluations").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}
