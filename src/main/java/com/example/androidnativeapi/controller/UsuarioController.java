package com.example.androidnativeapi.controller;

import com.example.androidnativeapi.dto.UsuarioCadastroRequest;
import com.example.androidnativeapi.dto.UsuarioResponse;
import com.example.androidnativeapi.service.UsuarioService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> cadastrar(
            @RequestBody UsuarioCadastroRequest request
    ) {

        UsuarioResponse usuario = service.cadastrar(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuario);
    }
}