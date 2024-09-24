package com.weather.controller;


import lombok.extern.slf4j.Slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.weather.request.AuthenticationRequest;
import com.weather.response.AuthenticationResponse;
import com.weather.service.CustomUserDetailsService;
import com.weather.utility.JwtUtil;

@Slf4j
@RestController
@RequestMapping
public class AuthController {

	final Logger log  = LoggerFactory.getLogger(AuthController.class.getName());
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/authenticate")
    public AuthenticationResponse createToken(@RequestBody AuthenticationRequest request) {
        log.info("createToken(-)");
        // Authenticate the user
        userDetailsService.loadUserByUsername(request.getUsername());

        // Generate the token
        String jwtToken = jwtUtil.generateToken(request.getUsername());

        return new AuthenticationResponse(jwtToken);
    }
}

