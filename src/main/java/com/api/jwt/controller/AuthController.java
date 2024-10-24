package com.api.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.jwt.model.Usuario;
import com.api.jwt.security.JwtUtil;
import com.api.jwt.service.UserService;

@RestController
public class AuthController {
	@Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

//    @Autowired
//    private UserService userService;

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody Usuario user) throws Exception {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password", e);
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}
