package org.example.magazzino.repository;

import org.example.magazzino.entity.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

}
