package org.example.magazzino.dao;



import org.example.magazzino.entity.Categoria;
import org.example.magazzino.exception.EntityAlreadyExistsException;
import org.example.magazzino.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class CategoriaDAO {

    @Autowired
    CategoriaRepository repo;

    public Categoria insert(Categoria categoria) {
        if (repo.existsByNome(categoria.getNome())) { // supponendo che esista questo metodo in repo
            throw new EntityAlreadyExistsException("Categoria con nome '" + categoria.getNome() + "' già esistente.");
        }
        return repo.save(categoria);
    }


    public Categoria selectById(int id) {
        if(repo.existsById(id)){
            return repo.findById(id).get();
        } else{
            throw new NoSuchElementException("Categoria non trovata");
        }
    }

    public List<Categoria> selectAll() {
        List<Categoria> categorie = new ArrayList<Categoria>();
        try {
            categorie = repo.findAll();
        } catch (Exception e) {
            System.err.println("Errore nella ricerca delle categorie: " + e.getMessage()) ;
        }
        return categorie;
    }

    public Categoria update(Categoria categoria) {
        if(repo.existsById(categoria.getId())){
            return repo.save(categoria);
        } else {
            throw new NoSuchElementException("Categoria non trovata");
        }
    }

    public Categoria deleteById(int id) {
        if(repo.existsById(id)){
            Categoria c = repo.findById(id).get();
            repo.deleteById(id);
            return c;
        } else {
            throw new NoSuchElementException("Categoria non trovata");
        }
    }



}
