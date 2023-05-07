package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.BeanIds;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.jwt.JwtResponse;
import com.example.demo.config.jwt.JwtTokenProvider;
import com.example.demo.config.security.UserDetailsServiceImpl;
import com.example.demo.model.requests.CreateUserRequest;

/**
 * @author GiapNT
 *
 */
@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {

	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	@Qualifier(BeanIds.AUTHENTICATION_MANAGER)
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	@Qualifier("PasswordEncoder")
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody CreateUserRequest loginRequest) {

		UserDetails user = userDetailsServiceImpl.loadUserByUsername(loginRequest.getUsername());

		if (!passwordEncoder.matches(user.getPassword(), loginRequest.getPassword())) {
			throw new BadCredentialsException("Password invalid");
		}

		Authentication authentication = this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = this.jwtTokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JwtResponse(loginRequest.getUsername(), token));
	}

}
