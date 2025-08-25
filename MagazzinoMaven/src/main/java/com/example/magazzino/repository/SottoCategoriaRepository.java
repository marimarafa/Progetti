package com.example.magazzino.repository;

import com.example.magazzino.entity.SottoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SottoCategoriaRepository extends JpaRepository<SottoCategoria, Integer> {
}
