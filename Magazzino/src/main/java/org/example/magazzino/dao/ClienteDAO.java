package org.example.magazzino.dao;


import org.example.magazzino.entity.Cliente;
import org.example.magazzino.repository.ClienteRepository;
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
        Cliente c1 = null;
        try {
              c1 = repo.save(cliente);
        } catch (Exception e) {
            System.err.println("Errore nell'inserimento del cliente: " + e.getMessage());
        }
        return c1;
    }

    public Cliente selectById(int id) {
            return repo.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Cliente non trovato con ID: " + id));
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
            repo.deleteById(id);
            return repo.findById(id).get();
        }else{
            throw new NoSuchElementException("Cliente non trovato");
        }
    }


}
