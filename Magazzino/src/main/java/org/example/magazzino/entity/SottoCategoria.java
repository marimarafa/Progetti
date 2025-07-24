package org.example.magazzino.entity;

import jakarta.persistence.*;

@Entity(name = "sottocategoria")
public class SottoCategoria {
    @Id
    @GeneratedValue
    private int id;
    private String nome;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoria_id",referencedColumnName = "id")
    private Categoria superCategoria ;


    public SottoCategoria(int id, String nome,Categoria superCategoria) {
        this.id = id;
        this.nome = nome;
        this.superCategoria = superCategoria;
    }

    public SottoCategoria() {
    }

    public Categoria getSuperCategoria() {
        return superCategoria;
    }

    public void setSuperCategoria(Categoria superCategoria) {
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
