package com.example.magazzino.dao;


import com.example.magazzino.entity.Cliente;
import com.example.magazzino.exception.EntityAlreadyExistsException;
import com.example.magazzino.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ClienteDAO {

    @Autowired
    ClienteRepository repo;

    public Cliente insert(Cliente cliente) {
        if (existsByCodiceFiscale(cliente.getCodiceFiscale())) {
            throw new EntityAlreadyExistsException("Cliente con codice fiscale " + cliente.getCodiceFiscale() + " già esistente");
        }
        return repo.save(cliente);
    }

    public boolean existsByCodiceFiscale(String codice_fiscale) {
        return repo.existsByCodiceFiscale(codice_fiscale);
    }



    public Cliente selectById(int id) {
        if(repo.existsById(id)){
            return repo.findById(id).get();
        } else{
            throw new NoSuchElementException("Cliente non trovato");
        }
        }


    public List<Cliente> selectAll() {
        List<Cliente> clienti = new ArrayList<Cliente>();
        try {
            clienti = repo.findAll();
        } catch (Exception e) {
            System.err.println("Errore nella ricerca dei clienti: " + e.getMessage()) ;
        }
        return clienti;
    }

    public Cliente update(Cliente cliente) {
        if(repo.existsById(cliente.getId())){
            return repo.save(cliente);
        } else {
            throw new NoSuchElementException("Cliente non trovato");
        }
    }

    public Cliente deleteById(int id) {
        if(repo.existsById(id)){
            Cliente c = repo.findById(id).get();
            repo.deleteById(id);
            return c;
        } else {
            throw new NoSuchElementException("Cliente non trovato");
        }
    }


}
