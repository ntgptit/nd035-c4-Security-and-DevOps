package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@ContextConfiguration(classes = { CartController.class })
@ExtendWith(SpringExtension.class)
class CartControllerTest {
    @Autowired
    private CartController cartController;

    @MockBean
    private CartRepository cartRepository;

    @MockBean
    private ItemRepository itemRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link CartController#addTocart(ModifyCartRequest)}
     */
    @Test
    void testAddTocart() throws Exception {
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

        Cart cart3 = new Cart();
        cart3.setId(1L);
        cart3.setItems(new ArrayList<>());
        cart3.setTotal(BigDecimal.valueOf(1L));
        cart3.setUser(user2);
        when(this.cartRepository.save(Mockito.<Cart>any())).thenReturn(cart3);

        Item item = new Item();
        item.setDescription("The characteristics of someone or something");
        item.setId(1L);
        item.setName("Name");
        item.setPrice(BigDecimal.valueOf(1L));
        Optional<Item> ofResult = Optional.of(item);
        when(this.itemRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setCart(new Cart());
        user3.setId(1L);
        user3.setUsername("GiapNT");

        Cart cart4 = new Cart();
        cart4.setId(1L);
        cart4.setItems(new ArrayList<>());
        cart4.setTotal(BigDecimal.valueOf(1L));
        cart4.setUser(user3);

        User user4 = new User();
        user4.setCart(cart4);
        user4.setId(1L);
        user4.setUsername("GiapNT");

        Cart cart5 = new Cart();
        cart5.setId(1L);
        cart5.setItems(new ArrayList<>());
        cart5.setTotal(BigDecimal.valueOf(1L));
        cart5.setUser(user4);

        User user5 = new User();
        user5.setCart(cart5);
        user5.setId(1L);
        user5.setUsername("GiapNT");
        when(this.userRepository.findByUsername(Mockito.<String>any())).thenReturn(user5);

        ModifyCartRequest cartRequest = new ModifyCartRequest();
        cartRequest.setItemId(1L);
        cartRequest.setQuantity(1);
        cartRequest.setUsername("GiapNT");
        String content = (new ObjectMapper()).writeValueAsString(cartRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/cart/addToCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"items\":[{\"id\":1,\"name\":\"Name\",\"price\":1,\"description\":\"The characteristics of someone or something\"}],\"user\":{\"id\":1,\"username\":\"GiapNT\",\"password\":null},\"total\":2}"));
    }

    /**
     * Method under test: {@link CartController#addTocart(ModifyCartRequest)}
     */
    @Test
    void testAddTocart2() throws Exception {
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

        Cart cart3 = new Cart();
        cart3.setId(1L);
        cart3.setItems(new ArrayList<>());
        cart3.setTotal(BigDecimal.valueOf(1L));
        cart3.setUser(user2);
        when(this.cartRepository.save(Mockito.<Cart>any())).thenReturn(cart3);
        when(this.itemRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        User user3 = new User();
        user3.setCart(new Cart());
        user3.setId(1L);
        user3.setUsername("GiapNT");

        Cart cart4 = new Cart();
        cart4.setId(1L);
        cart4.setItems(new ArrayList<>());
        cart4.setTotal(BigDecimal.valueOf(1L));
        cart4.setUser(user3);

        User user4 = new User();
        user4.setCart(cart4);
        user4.setId(1L);
        user4.setUsername("GiapNT");

        Cart cart5 = new Cart();
        cart5.setId(1L);
        cart5.setItems(new ArrayList<>());
        cart5.setTotal(BigDecimal.valueOf(1L));
        cart5.setUser(user4);

        User user5 = new User();
        user5.setCart(cart5);
        user5.setId(1L);
        user5.setUsername("GiapNT");
        when(this.userRepository.findByUsername(Mockito.<String>any())).thenReturn(user5);

        ModifyCartRequest cartRequest = new ModifyCartRequest();
        cartRequest.setItemId(1L);
        cartRequest.setQuantity(1);
        cartRequest.setUsername("GiapNT");
        String content = (new ObjectMapper()).writeValueAsString(cartRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/cart/addToCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CartController#addTocart(ModifyCartRequest)}
     */
    @Test
    void testAddTocart3() throws Exception {
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

        Cart cart3 = new Cart();
        cart3.setId(1L);
        cart3.setItems(new ArrayList<>());
        cart3.setTotal(BigDecimal.valueOf(1L));
        cart3.setUser(user2);
        when(this.cartRepository.save(Mockito.<Cart>any())).thenReturn(cart3);

        Item item = new Item();
        item.setDescription("The characteristics of someone or something");
        item.setId(1L);
        item.setName("Name");
        item.setPrice(BigDecimal.valueOf(1L));
        Optional<Item> ofResult = Optional.of(item);
        when(this.itemRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setCart(new Cart());
        user3.setId(1L);
        user3.setUsername("GiapNT");

        Cart cart4 = new Cart();
        cart4.setId(1L);
        cart4.setItems(new ArrayList<>());
        cart4.setTotal(BigDecimal.valueOf(1L));
        cart4.setUser(user3);

        User user4 = new User();
        user4.setCart(cart4);
        user4.setId(1L);
        user4.setUsername("GiapNT");

        Cart cart5 = new Cart();
        cart5.setId(1L);
        cart5.setItems(new ArrayList<>());
        cart5.setTotal(null);
        cart5.setUser(user4);

        User user5 = new User();
        user5.setCart(cart5);
        user5.setId(1L);
        user5.setUsername("GiapNT");
        when(this.userRepository.findByUsername(Mockito.<String>any())).thenReturn(user5);

        ModifyCartRequest cartRequest = new ModifyCartRequest();
        cartRequest.setItemId(1L);
        cartRequest.setQuantity(1);
        cartRequest.setUsername("GiapNT");
        String content = (new ObjectMapper()).writeValueAsString(cartRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/cart/addToCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"items\":[{\"id\":1,\"name\":\"Name\",\"price\":1,\"description\":\"The characteristics of someone or something\"}],\"user\":{\"id\":1,\"username\":\"GiapNT\",\"password\":null},\"total\":1}"));
    }

    /**
     * Method under test: {@link CartController#addTocart(ModifyCartRequest)}
     */
    @Test
    void testAddTocart4() throws Exception {

        ModifyCartRequest cart = new ModifyCartRequest();

        cart.setItemId(1L);
        cart.setQuantity(10);
        cart.setUsername("usernameTest");

        ResponseEntity<Cart> addToCartActual = this.cartController.addTocart(cart);

        assertEquals(HttpStatus.NOT_FOUND, addToCartActual.getStatusCode());

    }

    /**
     * Method under test: {@link CartController#removeFromcart(ModifyCartRequest)}
     */
    @Test
    void testRemoveFromcart() throws Exception {
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

        Cart cart3 = new Cart();
        cart3.setId(1L);
        cart3.setItems(new ArrayList<>());
        cart3.setTotal(BigDecimal.valueOf(1L));
        cart3.setUser(user2);
        when(this.cartRepository.save(Mockito.<Cart>any())).thenReturn(cart3);

        Item item = new Item();
        item.setDescription("The characteristics of someone or something");
        item.setId(1L);
        item.setName("Name");
        item.setPrice(BigDecimal.valueOf(1L));
        Optional<Item> ofResult = Optional.of(item);
        when(this.itemRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setCart(new Cart());
        user3.setId(1L);
        user3.setUsername("GiapNT");

        Cart cart4 = new Cart();
        cart4.setId(1L);
        cart4.setItems(new ArrayList<>());
        cart4.setTotal(BigDecimal.valueOf(1L));
        cart4.setUser(user3);

        User user4 = new User();
        user4.setCart(cart4);
        user4.setId(1L);
        user4.setUsername("GiapNT");

        Cart cart5 = new Cart();
        cart5.setId(1L);
        cart5.setItems(new ArrayList<>());
        cart5.setTotal(BigDecimal.valueOf(1L));
        cart5.setUser(user4);

        User user5 = new User();
        user5.setCart(cart5);
        user5.setId(1L);
        user5.setUsername("GiapNT");
        when(this.userRepository.findByUsername(Mockito.<String>any())).thenReturn(user5);

        ModifyCartRequest cartRequest = new ModifyCartRequest();
        cartRequest.setItemId(1L);
        cartRequest.setQuantity(1);
        cartRequest.setUsername("GiapNT");
        String content = (new ObjectMapper()).writeValueAsString(cartRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/cart/removeFromCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"items\":[],\"user\":{\"id\":1,\"username\":\"GiapNT\",\"password\":null},\"total\":0}"));
    }

    /**
     * Method under test: {@link CartController#removeFromcart(ModifyCartRequest)}
     */
    @Test
    void testRemoveFromcart2() throws Exception {
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

        Cart cart3 = new Cart();
        cart3.setId(1L);
        cart3.setItems(new ArrayList<>());
        cart3.setTotal(BigDecimal.valueOf(1L));
        cart3.setUser(user2);
        when(this.cartRepository.save(Mockito.<Cart>any())).thenReturn(cart3);
        when(this.itemRepository.findById(Mockito.<Long>any())).thenReturn(Optional.empty());

        User user3 = new User();
        user3.setCart(new Cart());
        user3.setId(1L);
        user3.setUsername("GiapNT");

        Cart cart4 = new Cart();
        cart4.setId(1L);
        cart4.setItems(new ArrayList<>());
        cart4.setTotal(BigDecimal.valueOf(1L));
        cart4.setUser(user3);

        User user4 = new User();
        user4.setCart(cart4);
        user4.setId(1L);
        user4.setUsername("GiapNT");

        Cart cart5 = new Cart();
        cart5.setId(1L);
        cart5.setItems(new ArrayList<>());
        cart5.setTotal(BigDecimal.valueOf(1L));
        cart5.setUser(user4);

        User user5 = new User();
        user5.setCart(cart5);
        user5.setId(1L);
        user5.setUsername("GiapNT");
        when(this.userRepository.findByUsername(Mockito.<String>any())).thenReturn(user5);

        ModifyCartRequest cartRequest = new ModifyCartRequest();
        cartRequest.setItemId(1L);
        cartRequest.setQuantity(1);
        cartRequest.setUsername("GiapNT");
        String content = (new ObjectMapper()).writeValueAsString(cartRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/cart/removeFromCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link CartController#removeFromcart(ModifyCartRequest)}
     */
    @Test
    void testRemoveFromcart3() throws Exception {
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

        Cart cart3 = new Cart();
        cart3.setId(1L);
        cart3.setItems(new ArrayList<>());
        cart3.setTotal(BigDecimal.valueOf(1L));
        cart3.setUser(user2);
        when(this.cartRepository.save(Mockito.<Cart>any())).thenReturn(cart3);

        Item item = new Item();
        item.setDescription("The characteristics of someone or something");
        item.setId(1L);
        item.setName("Name");
        item.setPrice(BigDecimal.valueOf(1L));
        Optional<Item> ofResult = Optional.of(item);
        when(this.itemRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        User user3 = new User();
        user3.setCart(new Cart());
        user3.setId(1L);
        user3.setUsername("GiapNT");

        Cart cart4 = new Cart();
        cart4.setId(1L);
        cart4.setItems(new ArrayList<>());
        cart4.setTotal(BigDecimal.valueOf(1L));
        cart4.setUser(user3);

        User user4 = new User();
        user4.setCart(cart4);
        user4.setId(1L);
        user4.setUsername("GiapNT");

        Cart cart5 = new Cart();
        cart5.setId(1L);
        cart5.setItems(new ArrayList<>());
        cart5.setTotal(null);
        cart5.setUser(user4);

        User user5 = new User();
        user5.setCart(cart5);
        user5.setId(1L);
        user5.setUsername("GiapNT");
        when(this.userRepository.findByUsername(Mockito.<String>any())).thenReturn(user5);

        ModifyCartRequest cartRequest = new ModifyCartRequest();
        cartRequest.setItemId(1L);
        cartRequest.setQuantity(1);
        cartRequest.setUsername("GiapNT");
        String content = (new ObjectMapper()).writeValueAsString(cartRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/cart/removeFromCart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.cartController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":1,\"items\":[],\"user\":{\"id\":1,\"username\":\"GiapNT\",\"password\":null},\"total\":-1}"));
    }

    /**
     * Method under test: {@link CartController#removeFromcart(ModifyCartRequest)}
     */
    @Test
    void testRemoveFromcart4() throws Exception {

        ModifyCartRequest cart = new ModifyCartRequest();

        cart.setItemId(1L);
        cart.setQuantity(10);
        cart.setUsername("usernameTest");

        ResponseEntity<Cart> addToCartActual = this.cartController.removeFromcart(cart);

        assertEquals(HttpStatus.NOT_FOUND, addToCartActual.getStatusCode());

    }

}
