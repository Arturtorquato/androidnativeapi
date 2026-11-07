package com.example.androidnativeapi.service;

import com.example.androidnativeapi.dto.LoginRequest;
import com.example.androidnativeapi.dto.UsuarioCadastroRequest;
import com.example.androidnativeapi.dto.UsuarioResponse;
import com.example.androidnativeapi.entity.Usuario;
import com.example.androidnativeapi.repository.UsuarioRepository;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    
    public UsuarioResponse cadastrar(UsuarioCadastroRequest request) {

        if (request == null ||
                request.nome() == null ||
                request.nome().isBlank()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O nome é obrigatório"
            );
        }

        if (request.email() == null ||
                request.email().isBlank() ||
                !request.email().contains("@")) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Informe um e-mail válido"
            );
        }

        if (request.senha() == null ||
                request.senha().length() < 8) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A senha deve possuir pelo menos 8 caracteres"
            );
        }

        String email = request.email()
                .trim()
                .toLowerCase(Locale.ROOT);

        if (repository.existsByEmailIgnoreCase(email)) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este e-mail já está cadastrado"
            );
        }

        String senhaHash = passwordEncoder.encode(
                request.senha()
        );

        Usuario usuario = new Usuario(
                request.nome().trim(),
                email,
                senhaHash
        );

        Usuario salvo;

        try {
            salvo = repository.saveAndFlush(usuario);
        } catch (org.springframework.dao.DataIntegrityViolationException e) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este e-mail já está cadastrado"
            );
        }

        return converterParaResponse(salvo);
    }

    
    public UsuarioResponse login(LoginRequest request) {

        if (request == null ||
                request.email() == null ||
                request.email().isBlank() ||
                request.senha() == null ||
                request.senha().isEmpty()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Informe o e-mail e a senha"
            );
        }

        String email = request.email()
                .trim()
                .toLowerCase(Locale.ROOT);

        Usuario usuario = repository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "E-mail ou senha inválidos"
                ));

        boolean senhaCorreta = passwordEncoder.matches(
                request.senha(),
                usuario.getSenhaHash()
        );

        if (!senhaCorreta) {

            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "E-mail ou senha inválidos"
            );
        }

        return converterParaResponse(usuario);
    }

    
    private UsuarioResponse converterParaResponse(Usuario usuario) {

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail()
        );
    }
}