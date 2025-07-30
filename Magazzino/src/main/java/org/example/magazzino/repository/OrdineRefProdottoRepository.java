package org.example.magazzino.repository;

import org.example.magazzino.entity.OrdineRefProdotto;
import org.example.magazzino.entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdineRefProdottoRepository extends JpaRepository<OrdineRefProdotto, Integer> {

    @Modifying
    @Query(nativeQuery = true , value = """
                                        DELETE FROM ordinerefprodotto WHERE ordine_id = :ordineId  and prodotto_id = :prodottoId""")
    public int EliminaProdottoInOrdine(int  ordineId, int prodottoId);

}
