package com.example.magazzino.repository;

import com.example.magazzino.entity.Prodotto;
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
                                        WHERE s.id = :sottoCategoria_id AND c.id = :categoria_id
                                    """)
    public List<Prodotto> prodottiPerCategoriaeSottoCategoria(  int sottoCategoria ,
                                                               int categoria);

    @Query(nativeQuery = true , value = """
                                        SELECT * FROM prodotto WHERE nome = :nome """)
    public Prodotto prodottoPerNome( String nome);


    @Query(nativeQuery = true , value = """
                                        SELECT quantita FROM prodotto WHERE nome = :nome """)
    public int SetDisponibilta(String nome);




    boolean existsByNome(String nome);
}
