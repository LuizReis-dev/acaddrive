package com.luizreis.acaddrive.controllers;

import com.luizreis.acaddrive.dto.auth.AuthenticationRequestDTO;
import com.luizreis.acaddrive.dto.auth.AuthenticationResponseDTO;
import com.luizreis.acaddrive.dto.auth.RegisterRequestDTO;
import com.luizreis.acaddrive.services.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService service;

    public AuthController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO request){
        return ResponseEntity.ok(service.register(request));
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody AuthenticationRequestDTO request){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
