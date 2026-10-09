package br.com.follon.demo.controller;

import br.com.follon.demo.entity.BadgeEntity;
import br.com.follon.demo.mapper.UserMapper;
import br.com.follon.demo.repository.UserRepository;
import br.com.follon.demo.security.TokenService;
import br.com.follon.demo.service.BadgeService;
import br.com.follon.demo.request.BadgeRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BadgeController.class)
@AutoConfigureMockMvc(addFilters = false)
class BadgeControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockitoBean
    TokenService tokenService;
    @MockitoBean
    UserRepository userRepository;
    @MockitoBean
    UserMapper userMapper;
    @MockitoBean
    BadgeService badgeService;

    @Test
    void shouldDeserializeBadgePhotoAsBase64() throws Exception {
        when(badgeService.createBadge(any(BadgeRequest.class))).thenReturn(new BadgeEntity());

        mockMvc.perform(post("/follon/badges")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"hackathonId\":1,\"photo\":\"AQID\"}"))
                .andExpect(status().isOk());

        var requestCaptor = org.mockito.ArgumentCaptor.forClass(BadgeRequest.class);
        verify(badgeService).createBadge(requestCaptor.capture());
        assertEquals(1L, requestCaptor.getValue().getHackathonId());
        assertArrayEquals(new byte[]{1, 2, 3}, requestCaptor.getValue().getPhoto());
    }
}
