package org.example.magazzino.repository;

import org.example.magazzino.entity.Ordine;
import org.example.magazzino.entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Integer> {

    @Query(nativeQuery = true , value = """
                                        SELECT * FROM ordine WHERE Cliente_id = :Cliente_id""")
    public List<Ordine> OrdiniCliente(int Cliente_id);

}
