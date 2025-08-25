package com.example.magazzino.repository;

import com.example.magazzino.entity.OrdineRefProdotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRefProdottoRepository extends JpaRepository<OrdineRefProdotto, Integer> {

    @Modifying
    @Query(nativeQuery = true , value = """
                                        DELETE  FROM ordinerefprodotto WHERE ORDINE_ID = :ordineId  and PRODOTTO_ID = :prodottoId""")
    public int EliminaProdottoInOrdine(int  ordineId, int prodottoId);


    @Query(nativeQuery = true , value = """
                                        SELECT * FROM ordinerefprodotto WHERE ordine_id = :ordineId  and prodotto_id = :prodottoId""")
    public OrdineRefProdotto SelectById(int  ordineId, int prodottoId);


}
