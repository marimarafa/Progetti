package org.example.magazzino.repository;

import org.example.magazzino.entity.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

    @Query(nativeQuery = true , value = """
                                        SELECT * FROM ordine WHERE CLIENTE_ID = :ClienteId""")
    public List<Ordine> OrdiniCliente(int ClienteId);

}
