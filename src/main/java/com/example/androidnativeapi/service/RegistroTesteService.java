
package com.example.androidnativeapi.service;

import com.example.androidnativeapi.entity.Registro;
import com.example.androidnativeapi.repository.RegistroRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class RegistroTesteService {

    private final RegistroRepository repository;

    public RegistroTesteService(RegistroRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public int gerarDados(int quantidade) {

        
        if (quantidade != 100 &&
                quantidade != 500 &&
                quantidade != 1000) {

            throw new IllegalArgumentException(
                    "Quantidade inválida. Utilize 100, 500 ou 1000."
            );
        }

        
        repository.deleteAllInBatch();

        List<Registro> registros = new ArrayList<>();

        String[] categorias = {
                "Eletrônicos",
                "Periféricos",
                "Móveis",
                "Outro"
        };

        for (int i = 1; i <= quantidade; i++) {

            String titulo = String.format(
                    Locale.ROOT,
                    "Registro %04d",
                    i
            );

            String descricao =
                    "Registro gerado automaticamente para os testes do TCC.";

            String categoria =
                    categorias[(i - 1) % categorias.length];

            double valor = 10.0 + (i % 100) * 2.5;

            int quantidadeItens = 1 + (i % 10);

            Registro registro = new Registro(
                    titulo,
                    descricao,
                    categoria,
                    valor,
                    quantidadeItens
            );

            registros.add(registro);
        }

        repository.saveAll(registros);
        repository.flush();

        return registros.size();
    }
}
