package br.com.follon.demo.controller;

import br.com.follon.demo.mapper.UserMapper;
import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.security.TokenService;
import br.com.follon.demo.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RegisterFieldOfInterestController.class)
@AutoConfigureMockMvc(addFilters = false)
class RegisterFieldOfInterestControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    TokenService tokenService;
    @MockitoBean
    UserRepository userRepository;
    @MockitoBean
    UserMapper userMapper;
    @MockitoBean
    RegisterService registerService;

    @Test
    void shouldRegisterFieldOfInterest() throws Exception {
        String body = "[{\"name\":\"Java\"}]";
        mockMvc.perform(post("/follon/register-field-of-interest").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Field of interest registered successfully"));
    }
}
