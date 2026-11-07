
package com.example.androidnativeapi.controller;

import com.example.androidnativeapi.service.RegistroTesteService;

import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Profile("teste-tcc")
@RequestMapping("/teste-tcc")
public class RegistroTesteController {

    private final RegistroTesteService service;

    public RegistroTesteController(RegistroTesteService service) {
        this.service = service;
    }

    @PostMapping("/gerar/{quantidade}")
    public ResponseEntity<Map<String, Object>> gerar(
            @PathVariable int quantidade
    ) {

        if (quantidade != 100 &&
                quantidade != 500 &&
                quantidade != 1000) {

            return ResponseEntity.badRequest().body(
                    Map.of(
                            "mensagem",
                            "Quantidade inválida. Utilize 100, 500 ou 1000."
                    )
            );
        }

        int total = service.gerarDados(quantidade);

        return ResponseEntity.ok(
                Map.of(
                        "mensagem",
                        "Dados de teste gerados com sucesso.",
                        "total",
                        total
                )
        );
    }
}
