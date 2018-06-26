package com.karmanno.letmeconvert.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.karmanno.letmeconvert.payload.request.SignUpRequest;
import com.karmanno.letmeconvert.repository.UserRepository;
import org.apache.catalina.filters.CorsFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class AuthControllerTest {
    private MockMvc mockMvc;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    AuthController authController;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController)
                .addFilters(new CorsFilter())
                .build();
    }

    @Test
    public void testSignInOnGet() throws Exception {
        mockMvc.perform(get("/api/auth/signin")).andExpect(status().is(405));
    }

    @Test
    public void testSignUpOnGet() throws Exception {
        mockMvc.perform(get("/api/auth/signup")).andExpect(status().is(405));
    }

    @Test
    public void testSignUp() throws Exception {
        SignUpRequest signUpRequest = new SignUpRequest(
                "testuser1", "testuser1@test.test", "testuser1"
        );
        mockMvc.perform(post("/api/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(signUpRequest))
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
