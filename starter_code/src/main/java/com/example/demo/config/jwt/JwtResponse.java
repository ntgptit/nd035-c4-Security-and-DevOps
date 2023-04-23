package com.example.demo.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author GiapNT
 *
 */

@Data
@AllArgsConstructor
public class JwtResponse {
    private String username;
    private String token;
}
