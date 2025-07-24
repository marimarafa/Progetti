package org.example.magazzino.dto;

import jakarta.persistence.*;
import org.example.magazzino.entity.Categoria;


public class SottoCategoriaDTO {

    private int id;
    private String nome;

    private CategoriaDTO superCategoria ;


    public SottoCategoriaDTO(int id, String nome, CategoriaDTO superCategoria) {
        this.id = id;
        this.nome = nome;
        this.superCategoria = superCategoria;
    }

    public SottoCategoriaDTO() {
    }

    public CategoriaDTO getSuperCategoria() {
        return superCategoria;
    }

    public void setSuperCategoria(CategoriaDTO superCategoria) {
        this.superCategoria = superCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
