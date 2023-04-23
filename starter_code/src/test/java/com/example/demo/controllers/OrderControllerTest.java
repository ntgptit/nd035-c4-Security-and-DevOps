package com.example.demo.controllers;

import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;

@ContextConfiguration(classes = { OrderController.class })
@ExtendWith(SpringExtension.class)
class OrderControllerTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link OrderController#getOrdersForUser(String)}
     */
    @Test
    void testGetOrdersForUser() throws Exception {
        when(this.orderRepository.findByUser(Mockito.<User>any())).thenReturn(new ArrayList<>());

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
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/order/history/{username}",
                "GiapNT");
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderController#getOrdersForUser(String)}
     */
    @Test
    void testGetOrdersForUser2() throws Exception {
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

        UserOrder userOrder = new UserOrder();
        userOrder.setId(1L);
        userOrder.setItems(new ArrayList<>());
        userOrder.setTotal(BigDecimal.valueOf(1L));
        userOrder.setUser(user2);

        ArrayList<UserOrder> userOrderList = new ArrayList<>();
        userOrderList.add(userOrder);
        when(this.orderRepository.findByUser(Mockito.<User>any())).thenReturn(userOrderList);

        User user3 = new User();
        user3.setCart(new Cart());
        user3.setId(1L);
        user3.setUsername("GiapNT");

        Cart cart2 = new Cart();
        cart2.setId(1L);
        cart2.setItems(new ArrayList<>());
        cart2.setTotal(BigDecimal.valueOf(1L));
        cart2.setUser(user3);

        User user4 = new User();
        user4.setCart(cart2);
        user4.setId(1L);
        user4.setUsername("GiapNT");

        Cart cart3 = new Cart();
        cart3.setId(1L);
        cart3.setItems(new ArrayList<>());
        cart3.setTotal(BigDecimal.valueOf(1L));
        cart3.setUser(user4);

        User user5 = new User();
        user5.setCart(cart3);
        user5.setId(1L);
        user5.setUsername("GiapNT");
        when(this.userRepository.findByUsername(Mockito.<String>any())).thenReturn(user5);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/order/history/{username}",
                "GiapNT");
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("[{\"id\":1,\"items\":[],\"user\":{\"id\":1,\"username\":\"GiapNT\",\"password\":null},\"total\":1}]"));
    }

    /**
     * Method under test: {@link OrderController#getOrdersForUser(String)}
     */
    @Test
    void testGetOrdersForUser3() throws Exception {
        when(this.userRepository.findByUsername(Mockito.<String>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/order/history/{username}", "Name");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link OrderController#submit(String)}
     */
    @Test
    void testSubmit() throws Exception {
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

        UserOrder userOrder = new UserOrder();
        userOrder.setId(1L);
        userOrder.setItems(new ArrayList<>());
        userOrder.setTotal(BigDecimal.valueOf(1L));
        userOrder.setUser(user2);
        when(this.orderRepository.save(Mockito.<UserOrder>any())).thenReturn(userOrder);

        User user3 = new User();
        user3.setCart(new Cart());
        user3.setId(1L);
        user3.setUsername("GiapNT");

        Cart cart3 = new Cart();
        cart3.setId(1L);
        cart3.setItems(new ArrayList<>());
        cart3.setTotal(BigDecimal.valueOf(1L));
        cart3.setUser(user3);

        User user4 = new User();
        user4.setCart(cart3);
        user4.setId(1L);
        user4.setUsername("GiapNT");

        Cart cart4 = new Cart();
        cart4.setId(1L);
        cart4.setItems(new ArrayList<>());
        cart4.setTotal(BigDecimal.valueOf(1L));
        cart4.setUser(user4);

        User user5 = new User();
        user5.setCart(cart4);
        user5.setId(1L);
        user5.setUsername("GiapNT");
        when(this.userRepository.findByUsername(Mockito.<String>any())).thenReturn(user5);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/order/submit/{username}",
                "GiapNT");
        MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":null,\"items\":[],\"user\":{\"id\":1,\"username\":\"GiapNT\",\"password\":null},\"total\":1}"));
    }

    /**
     * Method under test: {@link OrderController#submit(String)}
     */
    @Test
    void testSubmit2() throws Exception {
        when(this.userRepository.findByUsername(Mockito.<String>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/order/submit/{username}",
                "GiapNT");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.orderController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
