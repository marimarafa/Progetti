package org.example.magazzino.dto;

public class CategoriaDTO {
    private int id;
    private String nome;

    public CategoriaDTO(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public CategoriaDTO() {
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
