package com.example.loginbackendJava.table.repositories;

import com.example.loginbackendJava.table.models.Tiempo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TiempoRepository extends JpaRepository<Tiempo, Long> {
}
