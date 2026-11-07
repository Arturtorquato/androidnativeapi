package com.example.androidnativeapi.controller;

import com.example.androidnativeapi.dto.LoginRequest;
import com.example.androidnativeapi.dto.UsuarioResponse;
import com.example.androidnativeapi.service.UsuarioService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService service;

    public AuthController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> login(
            @RequestBody LoginRequest request
    ) {

        UsuarioResponse usuario = service.login(request);

        return ResponseEntity.ok(usuario);
    }
}