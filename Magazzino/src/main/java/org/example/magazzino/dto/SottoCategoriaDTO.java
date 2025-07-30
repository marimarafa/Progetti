package org.example.magazzino.dto;


public class SottoCategoriaDTO {

    private int id;
    private String nome;

    private CategoriaDTO categoriaId ;


    public SottoCategoriaDTO(int id, String nome, CategoriaDTO categoriaId) {
        this.id = id;
        this.nome = nome;
        this.categoriaId = categoriaId;
    }

    public SottoCategoriaDTO() {
    }

    public SottoCategoriaDTO(int id) {
        this.id = id;
    }

    public CategoriaDTO getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(CategoriaDTO categoriaId) {
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
