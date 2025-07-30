package org.example.magazzino.dao;



import org.example.magazzino.entity.Categoria;
import org.example.magazzino.entity.Prodotto;
import org.example.magazzino.entity.SottoCategoria;
import org.example.magazzino.exception.EntityAlreadyExistsException;
import org.example.magazzino.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ProdottoDAO {

    @Autowired
    ProdottoRepository repo;

    public Prodotto insert(Prodotto prodotto) {
        if (repo.existsByNome(prodotto.getNome())) {
            throw new EntityAlreadyExistsException("Prodotto con nome '" + prodotto.getNome() + "' già esistente.");
        }
        return repo.save(prodotto);
    }


    public Prodotto selectById(int id) {
        if(repo.existsById(id)){
            return repo.findById(id).get();
        } else{
            throw new NoSuchElementException("Prodotto non trovato");
        }
    }

    public List<Prodotto> selectAll() {
        List<Prodotto> prodotti = new ArrayList<Prodotto>();
        try {
            prodotti = repo.findAll();
        } catch (Exception e) {
            System.err.println("Errore nella ricerca dei prodotti: " + e.getMessage()) ;
        }
        return prodotti;
    }

    public Prodotto update(Prodotto prodotto) {
        if(repo.existsById(prodotto.getId())){
            return repo.save(prodotto);
        } else {
            throw new NoSuchElementException("Prodotto non trovato");
        }
    }

    public Prodotto deleteById(int id) {
        if(repo.existsById(id)){
            Prodotto p = repo.findById(id).get();
            repo.deleteById(id);
            return p;
        } else {
            throw new NoSuchElementException("Prodotto non trovato");
        }
    }


    public List<Prodotto> prodottiPerCategoriaeSottoCategoria(int sottoCategoria,int categoria ) {
        try {
            return repo.prodottiPerCategoriaeSottoCategoria(sottoCategoria, categoria);
        } catch (Exception e) {
            throw new RuntimeException("Prodotto con la sotto categoria con id: " + sottoCategoria + " e categoria con id: " + categoria + " non trovato");
        }
    }

    public Prodotto prodottoPerNome(String nome) {
        try {
            return repo.prodottoPerNome(nome);
        } catch (Exception e) {
            throw new RuntimeException("Prodotto con nome: " + nome + " non trovato");
        }
    }

    public int quantitaProdotto(String nome) {
        try {
            return repo.QuantitaProdotto(nome);
        } catch (Exception e) {
            throw new RuntimeException("Prodotto con nome: " + nome + " non trovato");
        }
    }

    public boolean existsById(int id) {
        return repo.existsById(id);
    }
}
