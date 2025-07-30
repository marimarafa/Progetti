package org.example.magazzino.entity;

import jakarta.persistence.*;

@Entity(name = "sottocategoria")
public class SottoCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "NOME")
    private String nome;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CATEGORIA_ID",referencedColumnName = "ID")
    private Categoria categoriaId ;


    public SottoCategoria(int id, String nome,Categoria categoriaId) {
        this.id = id;
        this.nome = nome;
        this.categoriaId = categoriaId;
    }

    public SottoCategoria() {
    }

    public SottoCategoria(int id) {
        this.id = id;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
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
