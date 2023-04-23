package com.example.demo.controllers;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.config.jwt.JwtTokenProvider;
import com.example.demo.model.requests.CreateUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = { JwtAuthenticationController.class })
@ExtendWith(SpringExtension.class)
class JwtAuthenticationControllerTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAuthenticationController jwtAuthenticationController;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    /**
     * Method under test: {@link JwtAuthenticationController#login(CreateUserRequest)}
     */
    @Test
    void testLogin() throws Exception {
        when(this.jwtTokenProvider.generateToken(Mockito.<Authentication>any())).thenReturn("ABC123");
        when(this.authenticationManager.authenticate(Mockito.<Authentication>any()))
                .thenReturn(new TestingAuthenticationToken("Principal", "Credentials"));

        CreateUserRequest createUserRequest = new CreateUserRequest("janedoe", "iloveyou", "janedoe");
//        createUserRequest.setConfirmPassword("iloveyou");
//        createUserRequest.setPassword("iloveyou");
//        createUserRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(createUserRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.jwtAuthenticationController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("{\"username\":\"janedoe\",\"token\":\"ABC123\"}"));
    }
}
