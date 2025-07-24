package org.example.magazzino.repository;

import org.example.magazzino.entity.UnitaMisura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitaMisuraRepository extends JpaRepository<UnitaMisura, Integer> {
}
