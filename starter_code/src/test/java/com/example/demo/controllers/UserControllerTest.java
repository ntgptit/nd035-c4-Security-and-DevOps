package com.example.demo.controllers;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = { UserController.class,
        BCryptPasswordEncoder.class })
@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @MockBean
    private CartRepository cartRepository;

    @Autowired
    private UserController userController;

    @MockBean
    private UserRepository userRepository;

    @MockBean(name = "PasswordEncoder")
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Method under test: {@link UserController#createUser(CreateUserRequest)}
     */
    @Test
    void testCreateUser() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/user/create")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new CreateUserRequest("GiapNT", "ahihi123456",
                        "ahihi123456")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(200));
    }

    /**
     * Method under test: {@link UserController#findById(Long)}
     */
    @Test
    void testFindById() throws Exception {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setItems(new ArrayList<>());
        cart.setTotal(null);
        cart.setUser(new User());

        User user = new User();
        user.setCart(cart);
        user.setId(1L);
        user.setUsername("GiapNT");

        Cart cart2 = new Cart();
        cart2.setId(1L);
        cart2.setItems(new ArrayList<>());
        cart2.setTotal(BigDecimal.valueOf(1L));
        cart2.setUser(user);

        User user2 = new User();
        user2.setCart(cart2);
        user2.setId(1L);
        user2.setUsername("GiapNT");
        Optional<User> ofResult = Optional.of(user2);
        when(this.userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/id/{id}", 1L);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(
                        "{\"id\":1,\"username\":\"GiapNT\",\"password\":null}"));
    }

    /**
     * Method under test: {@link UserController#findById(Long)}
     */
    @Test
    void testFindById2() throws Exception {
        Cart cart = new Cart();
        cart.setId(1L);
        cart.setItems(new ArrayList<>());
        cart.setTotal(null);
        cart.setUser(new User());

        User user = new User();
        user.setCart(cart);
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");

        Cart cart2 = new Cart();
        cart2.setId(1L);
        cart2.setItems(new ArrayList<>());
        cart2.setTotal(BigDecimal.valueOf(1L));
        cart2.setUser(user);

        User user2 = new User();
        user2.setCart(cart2);
        user2.setId(1L);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");
        Optional<User> ofResult = Optional.of(user2);
        when(this.userRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/id/{id}", 1L);
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(
                        MockMvcResultMatchers.content().string(
                                "{\"id\":1,\"username\":\"janedoe\",\"password\":\"iloveyou\"}"));
    }

    /**
     * Method under test: {@link UserController#createUser(CreateUserRequest)}
     *
     * @throws Exception
     */
    @Test
    void testCreateUser2() throws Exception {

        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/user/create")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new CreateUserRequest("GiapNT", null,
                        "ahihi123456")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));

    }

    /**
     * Method under test: {@link UserController#createUser(CreateUserRequest)}
     *
     * @throws Exception
     */
    @Test
    void testCreateUser3() throws Exception {

        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/user/create")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new CreateUserRequest("GiapNT", "123456",
                        "ahihi123456")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link UserController#createUser(CreateUserRequest)}
     *
     * @throws Exception
     */
    @Test
    void testCreateUser4() throws Exception {

        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/api/user/create")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new CreateUserRequest("GiapNT", "123456789",
                        "ahihi123456")));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.userController).build().perform(
                requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

//    /**
//     * Method under test: {@link UserController#createUser(CreateUserRequest)}
//     */
//    @Test
//    void testCreateUser4() {
//
//        UserController userController = new UserController();
//        CreateUserRequest createUserRequest = mock(CreateUserRequest.class);
//        when(createUserRequest.getUsername()).thenReturn("janedoe");
//        userController.createUser(createUserRequest);
//    }

    /**
     * Method under test: {@link UserController#findByUserName(String)}
     */
    @Test
    void testFindByUserName() throws Exception {
        User user = new User();
        user.setCart(new Cart());
        user.setId(1L);
        user.setUsername("GiapNT");

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setItems(new ArrayList<>());
        cart.setTotal(BigDecimal.valueOf(1L));
        cart.setUser(user);

        User user2 = new User();
        user2.setCart(cart);
        user2.setId(1L);
        user2.setUsername("GiapNT");

        Cart cart2 = new Cart();
        cart2.setId(1L);
        cart2.setItems(new ArrayList<>());
        cart2.setTotal(BigDecimal.valueOf(1L));
        cart2.setUser(user2);

        User user3 = new User();
        user3.setCart(cart2);
        user3.setId(1L);
        user3.setUsername("GiapNT");
        when(this.userRepository.findByUsername(Mockito.<String>any())).thenReturn(user3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/{username}", "GiapNT");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string(
                        "{\"id\":1,\"username\":\"GiapNT\",\"password\":null}"));
    }

    /**
     * Method under test: {@link UserController#findByUserName(String)}
     */
    @Test
    void testFindByUserName2() throws Exception {
//        User user = new User();
//        user.setCart(new Cart());
//        user.setId(1L);
//        user.setUsername("GiapNT");
//
//        Cart cart = new Cart();
//        cart.setId(1L);
//        cart.setItems(new ArrayList<>());
//        cart.setTotal(BigDecimal.valueOf(1L));
//        cart.setUser(user);
//
//        User user2 = new User();
//        user2.setCart(cart);
//        user2.setId(1L);
//        user2.setUsername("GiapNT");
//
//        Cart cart2 = new Cart();
//        cart2.setId(1L);
//        cart2.setItems(new ArrayList<>());
//        cart2.setTotal(BigDecimal.valueOf(1L));
//        cart2.setUser(user2);
//
//        User user3 = new User();
//        user3.setCart(cart2);
//        user3.setId(1L);
//        user3.setUsername("GiapNT");
        when(this.userRepository.findByUsername(Mockito.<String>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/{username}", "GiapNT");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link UserController#findByUserName(String)}
     */
    @Test
    void testFindByUserName3() throws Exception {
        User user = new User();
        user.setCart(new Cart());
        user.setId(1L);
        user.setPassword("iloveyou");
        user.setUsername("janedoe");

        Cart cart = new Cart();
        cart.setId(1L);
        cart.setItems(new ArrayList<>());
        cart.setTotal(BigDecimal.valueOf(1L));
        cart.setUser(user);

        User user2 = new User();
        user2.setCart(cart);
        user2.setId(1L);
        user2.setPassword("iloveyou");
        user2.setUsername("janedoe");

        Cart cart2 = new Cart();
        cart2.setId(1L);
        cart2.setItems(new ArrayList<>());
        cart2.setTotal(BigDecimal.valueOf(1L));
        cart2.setUser(user2);

        User user3 = new User();
        user3.setCart(cart2);
        user3.setId(1L);
        user3.setPassword("iloveyou");
        user3.setUsername("janedoe");
        when(this.userRepository.findByUsername(Mockito.<String>any())).thenReturn(user3);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/{username}", "janedoe");
        MockMvcBuilders.standaloneSetup(this.userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(
                        MockMvcResultMatchers.content().string(
                                "{\"id\":1,\"username\":\"janedoe\",\"password\":\"iloveyou\"}"));
    }
}
