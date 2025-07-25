package org.example.magazzino.repository;

import org.example.magazzino.entity.Categoria;
import org.example.magazzino.entity.Prodotto;
import org.example.magazzino.entity.SottoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {

    @Query(nativeQuery = true , value = """
                                        SELECT p.*
                                        FROM prodotto p
                                        JOIN sottocategoria s ON p.sottoCategoria_id = s.id
                                        JOIN categoria c ON s.categoria_id = c.id
                                        WHERE s.id = :sottoCategoriaId AND c.id = :categoriaId
                                    """)
    public List<Prodotto> prodottiPerCategoriaeSottoCategoria(int sottoCategoria , int categoria);

    @Query(nativeQuery = true , value = """
                                        SELECT * FROM prodotto WHERE nome = :nome """)
    public Prodotto prodottoPerNome(String nome);


    @Query(nativeQuery = true , value = """
                                        SELECT quantita FROM prodotto WHERE nome = :nome """)
    public int QuantitaProdotto(String nome);




    boolean existsByNome(String nome);
}
