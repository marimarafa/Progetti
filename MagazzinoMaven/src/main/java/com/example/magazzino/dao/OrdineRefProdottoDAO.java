package com.example.magazzino.dao;

import com.example.magazzino.entity.OrdineRefProdotto;
import com.example.magazzino.repository.OrdineRefProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class OrdineRefProdottoDAO {

    @Autowired
    private OrdineRefProdottoRepository repo;

    public OrdineRefProdotto insert(OrdineRefProdotto ref) {
        OrdineRefProdotto orp= null;
        try {
            orp = repo.save(ref);
        } catch (Exception e) {
            System.err.println("Errore nell'inserimento dell' ordine e del prodotto: " + e.getMessage());
        }
        return orp;
    }

    public List<OrdineRefProdotto> selectAll() {
        return repo.findAll();
    }

    public OrdineRefProdotto update(OrdineRefProdotto ref) {
        if (!repo.existsById(ref.getId().getOrdine())){
            throw new NoSuchElementException("Associazione ordine-prodotto non trovata");
        }
        return repo.save(ref);
    }

    public boolean EliminaProdottoInOrdine(int ordineId, int prodottoId){
        try {
            return repo.EliminaProdottoInOrdine(ordineId, prodottoId) > 0;
        } catch (Exception e) {
            throw new RuntimeException("Errore nell'eliminazione del prodotto: " + e.getMessage(), e);
        }
    }

}
