package com.example.androidnativeapi.service;

import com.example.androidnativeapi.entity.Registro;
import com.example.androidnativeapi.repository.RegistroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroService {

    private final RegistroRepository repository;

    public RegistroService(RegistroRepository repository) {
        this.repository = repository;
    }

    public List<Registro> listar() {
        return repository.findAll();
    }

    public Registro buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Registro não encontrado")
                );
    }

    public Registro inserir(Registro registro) {
        registro.setId(null);
        return repository.save(registro);
    }

    public Registro atualizar(Long id, Registro registro) {

        Registro existente = buscarPorId(id);

        existente.setTitulo(registro.getTitulo());
        existente.setDescricao(registro.getDescricao());
        existente.setCategoria(registro.getCategoria());
        existente.setValor(registro.getValor());
        existente.setQuantidade(registro.getQuantidade());

        return repository.save(existente);
    }

    public void excluir(Long id) {
        Registro registro = buscarPorId(id);
        repository.delete(registro);
    }
}