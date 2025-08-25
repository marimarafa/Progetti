package com.example.magazzino.dao;


import com.example.magazzino.entity.SottoCategoria;
import com.example.magazzino.repository.SottoCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class SottoCategoriaDAO {

    @Autowired
    SottoCategoriaRepository repo;

    public SottoCategoria insert(SottoCategoria sottoCategoria) {
        try {
            repo.save(sottoCategoria);
        } catch (Exception e) {
            System.err.println("Errore nell'inserimento della sottoCategoria: " + e.getMessage());
        }
        return sottoCategoria;
    }

    public SottoCategoria selectById(int id) {
        if(repo.existsById(id)){
            return repo.findById(id).get();
        } else{
            throw new NoSuchElementException("SottoCategoria non trovata");
        }
    }

    public List<SottoCategoria> selectAll() {
        List<SottoCategoria> sCategorie = new ArrayList<SottoCategoria>();
        try {
            sCategorie = repo.findAll();
        } catch (Exception e) {
            System.err.println("Errore nella ricerca delle sotto categorie: " + e.getMessage()) ;
        }
        return sCategorie;
    }

    public SottoCategoria update(SottoCategoria sottoCategoria) {
        if(repo.existsById(sottoCategoria.getId())){
            return repo.save(sottoCategoria);
        } else {
            throw new NoSuchElementException("SottoCategoria non trovata");
        }
    }

    public SottoCategoria deleteById(int id) {
        if(repo.existsById(id)){
            SottoCategoria s = repo.findById(id).get();
            repo.deleteById(id);
            return s;
        }else{
            throw new NoSuchElementException("SottoCategoria non trovata");
        }
    }

}
