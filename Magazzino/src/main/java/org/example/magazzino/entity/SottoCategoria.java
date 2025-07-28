package org.example.magazzino.entity;

import jakarta.persistence.*;

@Entity(name = "sottocategoria")
public class SottoCategoria {
    @Id
    private int id;
    private String nome;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "categoria_id",referencedColumnName = "id")
    private Categoria categoria_id ;


    public SottoCategoria(int id, String nome,Categoria categoria_id) {
        this.id = id;
        this.nome = nome;
        this.categoria_id = categoria_id;
    }

    public SottoCategoria() {
    }

    public SottoCategoria(int id) {
        this.id = id;
    }

    public Categoria getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Categoria categoria_id) {
        this.categoria_id = categoria_id;
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
