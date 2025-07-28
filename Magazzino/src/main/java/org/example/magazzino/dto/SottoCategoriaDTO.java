package org.example.magazzino.dto;


public class SottoCategoriaDTO {

    private int id;
    private String nome;

    private CategoriaDTO categoria_id ;


    public SottoCategoriaDTO(int id, String nome, CategoriaDTO categoria_id) {
        this.id = id;
        this.nome = nome;
        this.categoria_id = categoria_id;
    }

    public SottoCategoriaDTO() {
    }

    public SottoCategoriaDTO(int id) {
        this.id = id;
    }

    public CategoriaDTO getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(CategoriaDTO categoria_id) {
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
