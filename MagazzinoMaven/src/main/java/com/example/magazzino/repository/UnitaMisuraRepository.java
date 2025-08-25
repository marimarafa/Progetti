package com.example.magazzino.repository;

import com.example.magazzino.entity.UnitaMisura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitaMisuraRepository extends JpaRepository<UnitaMisura, Integer> {
}
