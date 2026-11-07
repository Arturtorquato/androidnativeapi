package com.example.androidnativeapi.controller;

import com.example.androidnativeapi.entity.Registro;
import com.example.androidnativeapi.service.RegistroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/registros")
public class RegistroController {

    private final RegistroService service;

    public RegistroController(RegistroService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Registro>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Registro> buscarPorId(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(
                service.buscarPorId(id)
        );
    }

    @PostMapping
    public ResponseEntity<Registro> inserir(
            @RequestBody Registro registro
    ) {
        return ResponseEntity.ok(
                service.inserir(registro)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Registro> atualizar(
            @PathVariable Long id,
            @RequestBody Registro registro
    ) {
        return ResponseEntity.ok(
                service.atualizar(id, registro)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(
            @PathVariable Long id
    ) {
        service.excluir(id);

        return ResponseEntity.noContent().build();
    }
}