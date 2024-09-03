package com.example.sensor.controller;

import com.example.sensor.dto.AuthenticatorDto;
import com.example.sensor.dto.LoginResponseDto;
import com.example.sensor.entity.User;
import com.example.sensor.service.TokenService;
import com.example.sensor.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Authenticator;

@RestController
@RequestMapping(path = "auth")
public class AuthenticatorController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService service;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AuthenticatorDto dto){

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(dto.getUsername(),dto.getPassword());

        Authentication auth = this.authenticationManager.authenticate(usernamePassword);

        String token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));

    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid AuthenticatorDto data){
        if(this.service.loadUserByUsername(data.getUsername()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
        User newUser = new User(data.getUsername(), encryptedPassword, data.getRole());

        this.service.save(newUser);

        return ResponseEntity.ok().build();
    }
}
