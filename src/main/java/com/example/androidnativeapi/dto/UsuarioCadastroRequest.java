package com.example.androidnativeapi.dto;

public record UsuarioCadastroRequest(
        String nome,
        String email,
        String senha
) {
}