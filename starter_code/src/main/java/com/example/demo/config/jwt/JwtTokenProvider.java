package com.example.demo.config.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.example.demo.config.security.CustomUserDetails;
import com.example.demo.config.security.SecurityConstants;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

    @Autowired
    UserRepository userRepository;

    public String generateToken(Authentication authentication) {

//        CustomUserDetails userPrincipal = (CustomUserDetails) authentication.getPrincipal();
        CustomUserDetails userPrincipal = CustomUserDetails.class.cast(authentication.getPrincipal());

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + SecurityConstants.EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date()).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256,
                        SecurityConstants.SECRET)
                .compact();
    }

//    public String getUserIdFromJWT(String token) {
//        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();
//
//        return claims.getSubject();
//    }

    public String getSubject(String token) {
        Claims claims = Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public TokenStatus validateToken(String authToken) {

        try {

            if (authToken == null || "".equals(authToken)) {
                return TokenStatus.INVALID;
            }

            Jwts.parser().setSigningKey(SecurityConstants.SECRET).parseClaimsJws(authToken);
            String username = this.getSubject(authToken);

            User user = this.userRepository.findByUsername(username);

            if (user == null) {
                return TokenStatus.INVALID;
            }

            return TokenStatus.OK;

        } catch (SignatureException ex) {
            return TokenStatus.INVALID;
        } catch (MalformedJwtException ex) {
            return TokenStatus.MALFORMED_JWT;
        } catch (ExpiredJwtException ex) {
            return TokenStatus.EXPIRED_JWT;
        } catch (UnsupportedJwtException ex) {
            return TokenStatus.UNSUPPORTED_JWT;
        } catch (IllegalArgumentException ex) {
            return TokenStatus.ILLEGAL_ARGUMENT;
        }
    }
}
