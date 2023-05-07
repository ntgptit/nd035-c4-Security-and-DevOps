package com.example.demo.config.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.demo.model.persistence.repositories.UserRepository;

@ContextConfiguration(classes = { JwtTokenProvider.class })
@ExtendWith(SpringExtension.class)
class JwtTokenProviderTest {
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private AuthenticationManager authenticationManager;

	/**
	 * Method under test: {@link JwtTokenProvider#validateToken(String)}
	 */
	@Test
	void testValidateToken() {
//        assertEquals(TokenStatus.INVALID, this.jwtTokenProvider.validateToken(""));
//        assertEquals(TokenStatus.INVALID, this.jwtTokenProvider.validateToken(null));
//        assertEquals(TokenStatus.MALFORMED_JWT,
//                this.jwtTokenProvider.validateToken("com.example.demo.config.security.CustomUserDetails"));
//        assertEquals(TokenStatus.INVALID, this.jwtTokenProvider.validateToken(
//                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaGloaSIsImlhdCI6MTY4MjI1NDM2MywiZXhwIjoxNjgzMTE4MzYzfQ.TroJdZLJToIUfrgNNW6DPteEbXOwoOT4yuTdglCe6nw"));
//        assertEquals(TokenStatus.INVALID, this.jwtTokenProvider.validateToken(
//                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaGloaSIsImlhdCI6MTY4MjI1NDM2MywiZXhwIjoxNjgzMTE4MzYzfQ.TroJdZLJToIUfrgNNW6DPteEbXOwoOT4yuTdglCe6n"));
//        when(this.userRepository.findByUsername(any())).thenReturn(new User());
//
//        assertEquals(TokenStatus.OK, this.jwtTokenProvider.validateToken(
//                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhaGloaSIsImlhdCI6MTY4MjI1NDM2MywiZXhwIjoxNjgzMTE4MzYzfQ.TroJdZLJToIUfrgNNW6DPteEbXOwoOT4yuTdglCe6nw"));
	}

//    /**
//     * Method under test: {@link JwtTokenProvider#generateToken(authentication)}
//     */
//    @Test
//    void testGenerateToken() {
//
//        Authentication authentication = (Authentication) new CustomUserDetails("", "");
////        when(this.authenticationManager.authenticate(any())).thenReturn((Authentication) new CustomUserDetails("", ""));
//
//        this.jwtTokenProvider.generateToken(authentication);
//    }
}
