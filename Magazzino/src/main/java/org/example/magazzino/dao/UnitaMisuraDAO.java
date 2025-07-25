package org.example.magazzino.dao;



import org.example.magazzino.entity.UnitaMisura;
import org.example.magazzino.repository.UnitaMisuraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class UnitaMisuraDAO {

    @Autowired
    UnitaMisuraRepository repo;

    public UnitaMisura insert(UnitaMisura unitaMisura) {
        UnitaMisura unitaMisura1 = null;
        try {
            unitaMisura1 = repo.save(unitaMisura);
        } catch (Exception e) {
            System.err.println("Errore nell'inserimento dell' unitaMisura: " + e.getMessage());
        }
        return unitaMisura1;
    }

    public UnitaMisura selectById(int id) {
        if(repo.existsById(id)){
            return repo.findById(id).get();
        } else{
            throw new NoSuchElementException("UnitaMisura non trovata");
        }
    }

    public List<UnitaMisura> selectAll() {
        List<UnitaMisura> unitaMisure = new ArrayList<UnitaMisura>();
        try {
            unitaMisure = repo.findAll();
        } catch (Exception e) {
            System.err.println("Errore nella ricerca delle unita misure: " + e.getMessage()) ;
        }
        return unitaMisure;
    }

    public UnitaMisura update(UnitaMisura categoria) {
        if(repo.existsById(categoria.getId())){
            return repo.save(categoria);
        } else {
            throw new NoSuchElementException("UnitaMisura non trovata");
        }
    }

    public UnitaMisura deleteById(int id) {
        if(repo.existsById(id)){
            UnitaMisura u = repo.findById(id).get();
            repo.deleteById(id);
            return u;
        }else{
            throw new NoSuchElementException("UnitaMisura non trovata");
        }
    }


}
