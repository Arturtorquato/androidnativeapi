package com.example.androidnativeapi.repository;

import com.example.androidnativeapi.entity.Registro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
}