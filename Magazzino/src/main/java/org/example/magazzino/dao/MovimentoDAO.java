package org.example.magazzino.dao;


import org.example.magazzino.entity.Movimento;
import org.example.magazzino.repository.MovimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class MovimentoDAO {

    @Autowired
    MovimentoRepository repo;

    public Movimento insert(Movimento movimento) {
        try {
             return repo.save(movimento);
        } catch (Exception e) {
            throw new NoSuchElementException("Movimento non inserito");
        }
    }

    public Movimento selectById(int id) {
        if(repo.existsById(id)){
            return repo.findById(id).get();
        } else{
            throw new NoSuchElementException("Movimento non trovato");
        }
    }

    public List<Movimento> selectAll() {
        List<Movimento> movimenti = new ArrayList<Movimento>();
        try {
            movimenti = repo.findAll();
        } catch (Exception e) {
            System.err.println("Errore nella ricerca dei movimenti: " + e.getMessage()) ;
        }
        return movimenti;
    }

    public Movimento update(Movimento movimento) {
        if(repo.existsById(movimento.getId())){
            return repo.save(movimento);
        } else {
            throw new NoSuchElementException("Movimento non trovato");
        }
    }

    public Movimento deleteById(int id) {
        if(repo.existsById(id)){
            Movimento m = repo.findById(id).get();
            repo.deleteById(id);
            return m;
        }else{
            throw new NoSuchElementException("Movimento non trovato");
        }
    }

}
