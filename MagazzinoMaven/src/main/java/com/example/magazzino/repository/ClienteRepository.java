package com.example.magazzino.repository;

import com.example.magazzino.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    boolean existsByCodiceFiscale(String codice_fiscale);
}
