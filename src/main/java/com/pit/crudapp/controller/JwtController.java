package com.pit.crudapp.controller;

import com.pit.crudapp.jwt.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pit.crudapp.entity.User;

@RestController

public class JwtController {

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/getToken")
    public String authenticate(@RequestBody User user) {
        String token = null;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            token = jwtUtil.generateToken(user.getUsername());
        } else {
            return "user has Failed to Authenticate";
        }
        return token;
    }
}
