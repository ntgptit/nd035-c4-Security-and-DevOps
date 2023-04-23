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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;

@ContextConfiguration(classes = { ItemController.class })
@ExtendWith(SpringExtension.class)
class ItemControllerTest {
    @Autowired
    private ItemController itemController;

    @MockBean
    private ItemRepository itemRepository;

    /**
     * Method under test: {@link ItemController#getItemById(Long)}
     */
    @Test
    void testGetItemById() throws Exception {
        Item item = new Item();
        item.setDescription("The characteristics of someone or something");
        item.setId(1L);
        item.setName("Name");
        item.setPrice(BigDecimal.valueOf(1L));
        Optional<Item> ofResult = Optional.of(item);
        when(this.itemRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/item/{id}", 1L);
        MockMvcBuilders.standaloneSetup(this.itemController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"name\":\"Name\",\"price\":1,\"description\":\"The characteristics of someone or something\"}"));
    }

    /**
     * Method under test: {@link ItemController#getItems()}
     */
    @Test
    void testGetItems() throws Exception {
        when(this.itemRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/item");
        MockMvcBuilders.standaloneSetup(this.itemController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ItemController#getItems()}
     */
    @Test
    void testGetItems2() throws Exception {
        when(this.itemRepository.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/item");
        requestBuilder.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(this.itemController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ItemController#getItemsByName(String)}
     */
    @Test
    void testGetItemsByName() throws Exception {
        when(this.itemRepository.findByName(Mockito.<String>any())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/item/name/{name}", "Name");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.itemController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    /**
     * Method under test: {@link ItemController#getItemsByName(String)}
     */
    @Test
    void testGetItemsByName2() throws Exception {
        Item item = new Item();
        item.setDescription("The characteristics of someone or something");
        item.setId(1L);
        item.setName("?");
        item.setPrice(BigDecimal.valueOf(1L));

        ArrayList<Item> itemList = new ArrayList<>();
        itemList.add(item);
        when(this.itemRepository.findByName(Mockito.<String>any())).thenReturn(itemList);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/item/name/{name}", "Name");
        MockMvcBuilders.standaloneSetup(this.itemController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json;charset=UTF-8"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "[{\"id\":1,\"name\":\"?\",\"price\":1,\"description\":\"The characteristics of someone or something\"}]"));
    }

    /**
     * Method under test: {@link ItemController#getItemsByName(String)}
     */
    @Test
    void testGetItemsByName3() throws Exception {
        when(this.itemRepository.findByName(Mockito.<String>any())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/item/name/{name}", "Name");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(this.itemController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
