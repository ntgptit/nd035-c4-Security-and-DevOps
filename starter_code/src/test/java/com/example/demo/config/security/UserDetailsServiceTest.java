package com.example.demo.config.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.UserRepository;

@ContextConfiguration(classes = { UserDetailsServiceImpl.class })
@ExtendWith(SpringExtension.class)
class UserDetailsServiceTest {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
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
        UserDetails actualLoadUserByUsernameResult = this.userDetailsServiceImpl.loadUserByUsername("janedoe");
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("janedoe", actualLoadUserByUsernameResult.getUsername());
        assertEquals("iloveyou", actualLoadUserByUsernameResult.getPassword());
        verify(this.userRepository).findByUsername(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {

        String username = "nonexistentUser";
        when(this.userRepository.findByUsername(username)).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> {
            this.userDetailsServiceImpl.loadUserByUsername(username);
        });

    }
}
