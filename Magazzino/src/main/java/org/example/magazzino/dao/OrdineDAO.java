package org.example.magazzino.dao;



import org.example.magazzino.entity.Ordine;
import org.example.magazzino.repository.OrdineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class OrdineDAO {

    @Autowired
    OrdineRepository repo;

    public Ordine insert( Ordine ordine) {
        Ordine ordine1= null;
        try {
            ordine1 = repo.save(ordine);
        } catch (Exception e) {
            System.err.println("Errore nell'inserimento dell' ordine: " + e.getMessage());
        }
        return ordine1;
    }

    public Ordine selectById(int id) {
        if(repo.existsById(id)){
            return repo.findById(id).get();
        } else{
            throw new NoSuchElementException("Ordine non trovato");
        }
    }

    public List<Ordine> selectAll() {
        List<Ordine> ordini = new ArrayList<Ordine>();
        try {
            ordini = repo.findAll();
        } catch (Exception e) {
            System.err.println("Errore nella ricerca dei ordini: " + e.getMessage()) ;
        }
        return ordini;
    }

    public Ordine update(Ordine ordine) {
        if(repo.existsById(ordine.getId())){
            return repo.save(ordine);
        } else {
            throw new NoSuchElementException("Ordine non trovato");
        }
    }

    public Ordine deleteById(int id) {
        if(repo.existsById(id)){
            Ordine o = repo.findById(id).get();
            repo.deleteById(id);
            return o;
        }else{
            throw new NoSuchElementException("Ordine non trovato");
        }
    }


    public List<Ordine> findByClienteId(int clienteId) {
        try {
            List<Ordine> ordini = repo.OrdiniCliente(clienteId);
            if(ordini.isEmpty()){
                throw new NoSuchElementException("Nessun ordine trovato per il cliente con id: " + clienteId);
            }
            return ordini;
        } catch (Exception e) {
            System.err.println("Errore nella ricerca dei ordini: " + e.getMessage());
            throw new NoSuchElementException("Cliente con id: " + clienteId + " non trovato");
        }
    }
}
