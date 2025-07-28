package org.example.magazzino.entity;

import jakarta.persistence.*;

@Entity(name = "categoria")
public class Categoria {
    @Id
    private int id;
    private String nome;


    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Categoria() {
    }

    public Categoria(int id) {
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
